package index.thread.intrinsiclock;

/**
 * 产生异常会自动释放锁
 */
public class Test8 {

    public static void main(String[] args) {
        Test8 test1 = new Test8();

        new Thread(() -> {
            test1.mm(); // 使用的锁对象是Test6.class
        }).start();
        new Thread(() -> {
            Test8.mm2(); // 使用的锁对象是Test6.class
        }).start();
    }

    public void mm() {
        synchronized (Test8.class) { // 使用当前类的运行时类对象
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "--->" + i);
                if (i == 50) {
                    System.out.println(Integer.parseInt("cba"));
                }
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
