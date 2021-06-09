package index.thread.intrinsiclock;

/**
 * synchronization同步代码块
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();

        new Thread(() -> {
            test1.mm(); // 使用的锁对象this就是test1对象
        }).start();
        new Thread(() -> {
            test1.mm(); // 使用的锁对象this就是test1对象
        }).start();
    }

    public void mm() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
            }
        }
    }
}
