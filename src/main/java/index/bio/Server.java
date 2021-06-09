package index.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            // 创建服务端
            serverSocket = new ServerSocket(8080);
            Socket socket;
            while (true) {
                // 从socket的队列中获取socket的连接
                // 相当于一个消费者
                // 当前的线程阻塞在accept方法上面，该方法一直阻塞，除非获取到socket连接返回
                socket = serverSocket.accept();

                System.out.println("有人连接上...");
                // 获得到socket连接之后，分配线程任务进行处理
                new Thread(new Handler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
        }
    }
}
