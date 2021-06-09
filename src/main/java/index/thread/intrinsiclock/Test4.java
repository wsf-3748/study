package index.thread.intrinsiclock;

/**
 * synchronization同步代码块
 * 使用常量作为锁对象
 */
public class Test4 {

    public static void main(String[] args) {
        Test4 test1 = new Test4();
        Test4 test2 = new Test4();

        new Thread(() -> {
            test1.mm(); // 使用的锁对象this就是test1对象
        }).start();
        new Thread(() -> {
            test2.mm(); // 使用的锁对象this就是test2对象
        }).start();
        new Thread(() -> {
            sm(); // 使用的锁对象this就是test2对象
        }).start();
    }

    public static final Object OBJ = new Object(); // 使用常量作为锁对象

    public void mm() {
        synchronized (OBJ) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
            }
        }
    }

    public static void sm() {
        synchronized (OBJ) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
            }
        }
    }
}
