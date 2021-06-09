package index.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 处理线程
 */
public class Handler implements Runnable {

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            System.out.println(Thread.currentThread().getName() + "线程");
            // 输入流，接收数据
            br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            //接收数据
            String receive = null;

            // 输出流，发送数据
            pw = new PrintWriter(this.socket.getOutputStream(), true);

            // 回应结果
            String currentTime = null;

            while (true) {
                // 输入流：读取数据的部分是阻塞的
                // 由于使用的是阻塞IO，那么read方法一直处于阻塞状态，要等到数据传送完成才结束（返回-1）
                // 1，内核程序从网卡中把数据读取到内核空间中，是一直阻塞的。
                // 2，用户程序把内核空间的数据复制到用户空间的过程，是阻塞的。
                // 这两个过程中，对应的程序部分就是read方法的阻塞
                receive = br.readLine();
                if (receive == null) {
                    break;
                }
                System.out.println(receive);

                // 输出流，输出数据
                currentTime = Thread.currentThread().getName() + "已收到";
                pw.println(currentTime);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (pw != null) {
                pw.close();
                pw = null;
            }
        }
    }
}
