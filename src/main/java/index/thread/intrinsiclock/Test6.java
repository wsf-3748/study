package index.thread.intrinsiclock;

/**
 * synchronization同步实例方法
 * 把整个方法体作为同步代码块
 * 默认的锁对象是当前类的运行时类对象
 */
public class Test6 {

    public static void main(String[] args) {
        Test6 test1 = new Test6();

        new Thread(() -> {
            test1.mm(); // 使用的锁对象是Test6.class
        }).start();
        new Thread(() -> {
            Test6.mm2(); // 使用的锁对象是Test6.class
        }).start();
    }

    public void mm() {
        synchronized (Test6.class) { // 使用当前类的运行时类对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
            }
        }
    }
    // 默认的锁对象是当前类的运行时类对象
    public synchronized static void mm2() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "--->" + i);
        }
    }
}
