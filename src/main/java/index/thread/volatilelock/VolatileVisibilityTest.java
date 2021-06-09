package index.thread.volatilelock;

/**
 * JMM数据原子操作
 * read读取：从主内存读取数据
 * load载入：从主内存读取到的数据写入工作内存中
 * use使用：从工作内存读取数据计算
 * assign赋值：将计算好的值重新赋值到工作内存中
 * store存储：将工作内存数据写入主内存
 * write写入：将store过去的变量值赋值给主内存中的变量
 * lock锁定：将主内存变量加锁，标识为线程独占状态
 * unlock解锁：将主内存变量解锁，解锁后其他线程可以锁定该变量
 *
 * volatile关键字采用的就是MESI缓存一致性协议
 * MESI缓存一致性协议：
 * 多个CPU从主内存读取同一个数据到各自的高速缓存，当其中某个CPU修改了缓存中的数据，
 * 该数据会马上同步回主内存，其他CPU通过 总线嗅探机制 可以感知到数据的变化从而将自己缓存中的数据失效。
 */
public class VolatileVisibilityTest {
    // 不加volatile关键字，程序会进入死循环，因为第二个线程修改了二号线程工作内存中的副本initFlag的值为true，没有同步到主内存里，
    // 此时一号线程读取的还是一号线程工作内存中的副本initFlag的值false，所以程序处于死循环。
    // volatile关键字采用了MESI缓存一致性协议
    private static volatile boolean initFlag = false;
//    private static boolean initFlag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("waiting...");
                while (!initFlag) { // 共享内存副本，工作内存
                }
                System.out.println("===============end=============");
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            System.out.println("正在修改initFlag的值");
            initFlag = true; // 共享内存副本，工作内存
            System.out.println("修改initFlag的值为true");
        }).start();
    }
}
