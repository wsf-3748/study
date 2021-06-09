package index.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioSocketDemo {

    private Selector selector; // 通道选择器

    public static void main(String[] args) throws IOException {
        NioSocketDemo nioSocketDemo = new NioSocketDemo();
        nioSocketDemo.initServer(8888);
        nioSocketDemo.listenSelector();
    }

    public void initServer(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false); // 设置为非阻塞
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        this.selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 监听连接事件

        System.out.println("服务已启动...");
    }

    // 监听选择器
    public void listenSelector() throws IOException {
        // 轮询监听
        while (true) {
            // 等待客户连接
            // select 模型，多路复用
            this.selector.select();
            Iterator<SelectionKey> iterator = this.selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                // c处理请求
                handler(selectionKey);
            }
        }
    }

    /**
     * 处理客户请求
     * @param selectionKey
     */
    private void handler(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            // 处理客户连接事件
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false); // 设置为非阻塞
            // 接收客户端发送的信息时，需要给通道设置读的权限
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            // 处理客户读取事件
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int readData = socketChannel.read(byteBuffer);
            if (readData > 0) {
                String info = new String(byteBuffer.array(), StandardCharsets.UTF_8).trim();
                System.out.println("服务端接收到的数据：" + info);
            } else {
                System.out.println("客户端关闭了...");
                selectionKey.cancel();
            }
        }
    }
}
