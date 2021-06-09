package index.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final Random random = new Random();
        final List<Integer> list= new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            // 创建新的线程需要cpu去调用，所以比较耗时
            Thread thread = new Thread(() ->{
               list.add(random.nextInt());
            });
            thread.start();
            thread.join();
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("大小：" + list.size());
    }
}
