package index.nio;

import index.bio.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class TraditionalSocketDemo {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool(); // 线程池
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(7777);
            System.out.println("服务端启动...");
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
//                BlockingQueue<Runnable> blockingQueue = new SynchronousQueue<Runnable>();
//                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,10,2000, TimeUnit.DAYS, blockingQueue);
//                threadPoolExecutor.execute(new Handler(socket));
                executorService.execute(new Handler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
