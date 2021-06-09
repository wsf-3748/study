package index.thread.intrinsiclock;

/**
 * synchronization同步实例方法
 * 把整个方法体作为同步代码块
 * 默认的锁对象是this对象
 */
public class Test5 {

    public static void main(String[] args) {
        Test5 test1 = new Test5();

        new Thread(() -> {
            test1.mm(); // 使用的锁对象this就是test1对象
        }).start();
        new Thread(() -> {
            test1.mm2(); // 使用的锁对象this就是test2对象
        }).start();
    }

    public void mm() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
            }
        }
    }
    public synchronized void mm2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }
    }
}
