package index.thread.threadsafe;

public class Test2 {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        new Thread(myTask).start();

        try {
            Thread.sleep(1000);
            myTask.cancel();
            /*
            可能出现以下情况：
                在main线程中调用myTask.cancel()方法，把myTask对象的toCancel变量修改为true
                可能存在子线程看不到main线程对toCancel做的修改，在子线程中toCancel变量一直为false
             导致子线程看不到main线程对toCancel变量更新的原因，可能：
                1）JIT即时编译器可能会对run方法中while循环进行优化为：
                    if (!roCancel) {
                        while (true) {
                            if (doSomething()) {
                            }
                    }
                 2）可能与计算机的存储系统有关，假设分别有两个cpu内核运行main线程与子线程，
                 运行子线程的cpu内核无法立即读取运行main线程的cpu内核中的数据

            }
             */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyTask implements Runnable {

        private boolean toCancel = false;
        @Override
        public void run() {
            while (!toCancel) {
                if (doSomething()) {
//                    break;
                }
            }
            if (toCancel) {
                System.out.println("任务被取消");
            } else {
                System.out.println("任务正常结束");
            }
        }

        private boolean doSomething() {
            System.out.println("执行某个任务");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        public void cancel() {
            toCancel = true;
            System.out.println("收到 取消线程的消息");
        }
    }
}
