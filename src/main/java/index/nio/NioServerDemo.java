package index.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;

public class NioServerDemo {

    private Selector selector; // 通道选择器

    public static void main(String[] args) throws IOException {
        NioServerDemo serverDemo = new NioServerDemo();
        serverDemo.initServer(10000);
        serverDemo.listenServer();
    }

    public void initServer(int port) throws IOException {
        //ServerSocketChannel是一个可以监听新进来的TCP连接的通道,就像标准IO中的ServerSocket一样
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); // 设置为非阻塞
        //绑定连接
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        //选择器
        this.selector = Selector.open();
        //将通道注册到选择器上，并制定监听事件：服务端首先要监听客户端的接受状态
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务已启动...");
    }

    public void listenServer() throws IOException {
        //阻塞式，没有就行就阻塞在这
        while (selector.select() > 0) {
            System.out.println("selector.select()轮询");

            //获取已经就绪的监听事件
            Iterator<SelectionKey> selectorIterator = selector.selectedKeys().iterator();

            while (selectorIterator.hasNext()) {
                // 获取准备就绪的事件
                SelectionKey key = selectorIterator.next();
                handler(key);
            }
            /**
             * selector不会自己删除selectedKeys()集合中的selectionKey，
             * 如果不人工remove()，
             * 将导致下次select()的时候selectedKeys()中仍有上次轮询留下来的信息，这样必然会出现错误
             * 应此当某个消息被处理后我们需要从该集合里去掉
             */
            selectorIterator.remove();
        }
    }

    /**
     * 处理客户请求
     * @param key
     */
    private void handler(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            //获取服务端通道
            ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
            //接受成功连接到服务器的channel，则获取客户端连接
            SocketChannel socketChannel = serverChannel.accept();
            socketChannel.configureBlocking(false);

            //将该客户端通道注册到选择器上，监控客户端socketChannel的“读就绪”事件
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (key.isValid() && key.isReadable()) {
            SocketChannel readChannel = null;
            FileChannel outputChannel = null;
            try {
                System.out.println("开始读！");

                //获取当前选择器上“读就绪”状态的通道
                readChannel = (SocketChannel) key.channel();
                readChannel.configureBlocking(false);

                //创建文件输出管道：FileChannel不能使用非阻塞模式
                //从服务端接收文件，并将文件写到本地（写方式，如果文件不存在就创建）
                outputChannel = FileChannel.open(Paths.get("E:\\小视频.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

                //创建缓冲区，进行读写操作
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                while ((readChannel.read(readBuffer)) > 0) {
                    readBuffer.flip();
                    outputChannel.write(readBuffer);
                    readBuffer.clear();
                }
                System.out.println("读完了，加入写兴趣!");

                key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);

                if ((readChannel.read(readBuffer)) == -1) {

                    System.out.println("客户端断开连接！");

                    key.cancel();
                    try {
                        readChannel.close();
                        outputChannel.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            } catch (IOException e) {

                System.out.println("异常关闭！");

                //当客户端关闭channel时，服务端再往通道缓冲区中写或读数据，都会报IOException
                //解决方法是：在服务端这里捕获掉这个异常，并且关闭掉服务端这边的Channel通道
                key.cancel();
                try {
                    readChannel.close();
                    outputChannel.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (key.isValid() && key.isWritable()) {
            try {

                System.out.println("开始写！");

                SocketChannel channel = (SocketChannel) key.channel();
                channel.write(ByteBuffer.wrap("收到！".getBytes()));

                System.out.println("写完了，取消写兴趣！");

                key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
