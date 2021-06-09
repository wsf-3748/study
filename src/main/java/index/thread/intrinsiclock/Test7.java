package index.thread.intrinsiclock;

/**
 * 脏读
 * 读取属性时出现了一些意外，读取的是中间值，而不是修改之后的值
 * 出现脏读的原因是 对共享数据的修改 与对共享数据的读取不同步
 * 解决方法：
 *      不管修改还是读取，都要同步代码块
 */
public class Test7 {

    public static void main(String[] args) {
        PublicValue publicValue = new PublicValue();
        SubThread t1 = new SubThread(publicValue);
        t1.start();

        // 为了确保设置成功
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 在main线程中读取用户和密码
        publicValue.getValue();
    }

    static class SubThread extends Thread {
        private PublicValue publicValue;

        public SubThread(PublicValue publicValue) {
            this.publicValue = publicValue;
        }

        @Override
        public void run() {
            publicValue.setValue("dafei", "666");
        }
    }

    static class PublicValue {
        private String name = "wsf";
        private String pwd = "pwd";

        public synchronized void getValue() {
            System.out.println(Thread.currentThread().getName() + "getValue--> name=" + name
            + "，ped=" + pwd);
        }

        public synchronized void setValue(String name, String pwd) {
            this.name = name;
            try {
                Thread.sleep(1000); // 模拟操作需要的时间
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.pwd = pwd;
            System.out.println(Thread.currentThread().getName() + "setValue--> name=" + name
                    + "，ped=" + pwd);
        }
    }
}
