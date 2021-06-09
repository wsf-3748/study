package index.thread.volatilelock;

public class Test1 {

    public static void main(String[] args) {
        PrintString p = new PrintString();
        // 不开线程来执行p.printString()方法
        p.printString();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在main线程中修改打印标志");
        p.printContinue(false);
        // 查看运行结果，根本不会停止
    }

    static class PrintString {
        private boolean cb = true;

        public PrintString printContinue(boolean cb) {
            this.cb = cb;
            return this;
        }

        public void printString() {
            while (cb) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
