package index.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final Random random = new Random();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        final List<Integer> list= new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            executorService.execute(() ->{
                list.add(random.nextInt());
            });
        }
        executorService.shutdown();
        System.out.println(executorService.awaitTermination(1, TimeUnit.DAYS));

        System.out.println("耗时：" + (System.currentTimeMillis() - start));
        System.out.println("大小：" + list.size());
    }
}
