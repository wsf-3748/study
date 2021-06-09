package index.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * NIO同步非阻塞客户端
 */
public class NioCilentDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        //创建socket连接通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 10000));
        //切换成非阻塞模式
        socketChannel.configureBlocking(false);

        /**
         * 发送数据到服务器
         */
        System.out.println("开始发送数据！");

        //开辟缓存区
        ByteBuffer clientBuffer = ByteBuffer.allocate(1024);
        String inputStr = "你好，明天！";
        clientBuffer.put((new Date().toString() + "---" + inputStr + "\r\n").getBytes());
        clientBuffer.flip();
        socketChannel.write(clientBuffer);
        clientBuffer.clear();

        Thread.sleep(1000);

        /**
         * 从服务端接收数据
         */
        System.out.println("从服务端接收数据!");

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        StringBuffer buf = new StringBuffer();
        int count = 0;
        while ((count = socketChannel.read(buffer)) > 0) {
            byte[] array = buffer.array();
            buffer.clear();
            buf.append(new String(array, 0, count));
        }
        if (buf.length() > 0) {
            System.out.println(buf.toString());
        }

        /**
         * 关闭客户端管道
         */
        System.out.println("关闭客户端！");
        socketChannel.close();
    }
}
