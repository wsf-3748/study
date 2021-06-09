package index.thread.intrinsiclock;

/**
 * 死锁
 *  在多线程程序中，同步时可能需要使用多个锁，如果获得锁的顺序不一致，可能会导致死锁
 *  解决：
 *      当需要多个锁的时候，所有线程获得锁的顺序保持一致即可
 */
public class Test9 {

    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        t1.setName("a");
        t1.start();

        SubThread t2 = new SubThread();
        t2.setName("b");
        t2.start();
    }

    static class SubThread extends Thread {
        private static final Object LOCK1 = new Object();
        private static final Object LOCK2 = new Object();

        @Override
        public void run() {
            if ("a".equals(Thread.currentThread().getName())) {
                synchronized (LOCK1) {
                    System.out.println("a线程获得lock1锁，还需要获得lock2锁");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (LOCK2) {
                        System.out.println("a线程已经获得lock1锁，又获得lock2锁");
                    }
                }
            }
            if ("b".equals(Thread.currentThread().getName())) {
                /*synchronized (LOCK1) {
                    System.out.println("b线程获得lock2锁，还需要获得lock1锁");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (LOCK2) {
                        System.out.println("b线程已经获得lock2锁，又获得lock1锁");
                    }
                }*/
                synchronized (LOCK2) {
                    System.out.println("b线程获得lock2锁，还需要获得lock1锁");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (LOCK1) {
                        System.out.println("b线程已经获得lock2锁，又获得lock1锁");
                    }
                }
            }
        }
    }
}
