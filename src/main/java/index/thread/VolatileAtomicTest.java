package index.thread;

/**
 * volatile 与 synchronized的结合使用
 */
public class VolatileAtomicTest {

    public static volatile int num = 0;

    /*
     * 如果没有synchronized锁，当多个线程同时进行num加1操作时，由于volatile缓存一致性协议，监听到数据的变化从而将自己缓存中的数据失效。
     * 所以在高并发情况下，有很多线程进行num加1操作后，收到同步消息后会num的值会失效，相当于加1操作白做了所以结果会小于等于10000
     */
//    public static void increase() {
//        num ++;
//    }
    public static synchronized void increase() {
        num ++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    increase();
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join(); // 等待所有的线程执行完后回到主线程，才继续执行下面的代码（确保前面的10个线程全部执行完）
        }
        System.out.println("num = " + num); // 1000*10=10000
    }
}
