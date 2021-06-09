package index.thread.volatilelock;

/**
 * volatile关键字采用的就是MESI缓存一致性协议
 * MESI缓存一致性协议：
 * 多个CPU从主内存读取同一个数据到各自的高速缓存，当其中某个CPU修改了缓存中的数据，该数据会马上同步回主内存，
 * 其他CPU通过 总线嗅探机制 可以感知到数据的变化从而将自己缓存中的数据失效。
 *
 * 作用就是：可以强制从公共内存中中读取变量的值，而不是从工作内存（副本）中读取。
 */
public class Test2 {

    public static void main(String[] args) {
        PrintString p = new PrintString();

        // 开启子线程来执行p.printContinue(false)方法
        new Thread(() -> {
            p.printString();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在main线程中修改打印标志");
        p.printContinue(false);
        // 查看运行结果
    }

    static class PrintString {
//        private boolean cb = true;
        private volatile boolean cb = true;

        public PrintString printContinue(boolean cb) {
            this.cb = cb;
            return this;
        }

        public void printString() {
            System.out.println(Thread.currentThread().getName() + "开始..");
            while (cb) {
                /*System.out.println("sub thread ...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
            System.out.println(Thread.currentThread().getName() + "结束..");
        }
    }
}
