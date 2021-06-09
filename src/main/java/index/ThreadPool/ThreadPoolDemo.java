package index.ThreadPool;

import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {

//        public ThreadPoolExecutor(int corePoolSize, 核心线程数
//                                int maximumPoolSize, 非核心线程数
//                                long keepAliveTime, 时间
//                                TimeUnit unit, 时间单位
//                                BlockingQueue<Runnable> workQueue, // 队列
//                                ThreadFactory threadFactory, // 线程工厂
//                                RejectedExecutionHandler handler) // 拒绝策略

//        Executors是线程池的一个工具类
        ExecutorService executorService1 = Executors.newCachedThreadPool(); // 快,有多少个任务就会创建多少个线程
        ExecutorService executorService2 = Executors.newFixedThreadPool(10); // 慢
        ExecutorService executorService3 = Executors.newSingleThreadExecutor(); // 最慢
        for (int i = 0; i < 100; i++) {
//            executorService1.execute(new MyTask(i));
//            executorService2.execute(new MyTask(i));
            executorService1.execute(new MyTask(i));
        }

    }
}

class MyTask implements Runnable {

    int i;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---" + i);
        try {
            Thread.sleep(1000L); // 业务逻辑,如果注释掉,就会出现线程复用,
            // 因为线程执行的时间很快,前面创建的线程处理完就闲下来了,不需要多创建线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
