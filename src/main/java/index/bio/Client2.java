package index.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 */
public class Client2 {

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            // 创建连接
//            socket = new Socket("127.0.0.1", 8080);
            socket = new Socket("127.0.0.1", 7777);

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            //java.io.InputStreamReader继承了Reader类
            while (true) {
                System.out.print("输入数据：");

                String msg = input.readLine();
                if ("exit".equals(msg)) {
                    break;
                }
                // 输出流，发送数据
                pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println(msg);// 控制台输入的内容

                // 输入流，接收数据
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 阻塞：未收到数据就等在这里
                String response = br.readLine();
                System.out.println("回应说：" + response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (pw != null) {
                pw.close();
                pw = null;
            }
        }
    }
}
