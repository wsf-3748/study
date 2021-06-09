package index.thread.threadsafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程原子性问题
 */
public class Test1 {

    public static void main(String[] args) {
        // 启动两个线程不断的调用getNum()方法
        MyInt myInt = new MyInt();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "-->" +
                            myInt.getNum());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    /*static class MyInt {
        int num;
        public int getNum() {
            num += 1; // 先读取num的值，num的值加1，最后把加完的值赋值给num，三个操作
            return num;
        }
    }*/
    // 在java中提供了一个线程安全的AtomicInteger类，保证操作的原子性
    static class MyInt {
        AtomicInteger num = new AtomicInteger();
        public int getNum() {
            return num.getAndIncrement(); // 原子性自增操作
        }
    }
}
