package index.thread;

import java.util.concurrent.*;

public class CallThreadTest implements Callable<Integer> {

    public static final Object object = new Object();
    private int i;

    public CallThreadTest(int i) {
        this.i = i;
    }

    @Override
    public Integer call() {
        synchronized (object) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程名：" + Thread.currentThread().getName());
            System.out.println("call " + i);
        }

        i += 1;
        return i;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        public ThreadPoolExecutor(int corePoolSize, 核心线程数
//                                int maximumPoolSize, 非核心线程数
//                                long keepAliveTime, 时间
//                                TimeUnit unit, 时间单位
//                                BlockingQueue<Runnable> workQueue, // 队列
//                                ThreadFactory threadFactory, // 线程工厂
//                                RejectedExecutionHandler handler) // 拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10,
                1000, TimeUnit.MINUTES, new LinkedBlockingQueue<>());
//        FutureTask<Integer> futureTask = new FutureTask<>(callThreadTest);

        try {
            Class cls = Class.forName("");
            Class cls2 = threadPoolExecutor.getClass();
            Class cls3 = CallThreadTest.class;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("循环变量i的值" + i);

            CallThreadTest callThreadTest = new CallThreadTest(i);
            FutureTask<Integer> futureTask2 = (FutureTask<Integer>) threadPoolExecutor.submit(callThreadTest);
            try {
                Integer back = futureTask2.get();
                System.out.println(back);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPoolExecutor.shutdown();

    }
}
