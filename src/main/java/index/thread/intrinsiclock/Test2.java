package index.thread.intrinsiclock;

/**
 * synchronization同步代码块
 * 如果线程的锁不同，不能实现同步，想要同步必须使用同一个对象锁
 */
public class Test2 {

    public static void main(String[] args) {
        Test2 test1 = new Test2();
        Test2 test2 = new Test2();

        new Thread(() -> {
            test1.mm(); // 使用的锁对象this就是test1对象
        }).start();
        new Thread(() -> {
            test2.mm(); // 使用的锁对象this就是test2对象
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
