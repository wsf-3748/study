package index.thread.cas;

/**
 * 使用CAS机制实现线程安全的计算器
 * 会存在ABA问题，即共享变量经历了A->B->A
 */
public class CASTest {

    public static void main(String[] args) {
        CASCounter casCounter = new CASCounter();

        for (int i = 0; i < 10000; i++) {
            new Thread(() -> System.out.println(casCounter.incrementAndGet())).start();
        }
    }
}

class CASCounter{
    volatile private long value;

    public long getValue() {
        return value;
    }

    // 定义CAS方法
    private boolean compareAndSwap(long expectedValue, long newValue) {
        // 如果当前value的值与期望的expectedValue值一样，就把当前value字段替换为newValue字段
        synchronized (this) {
            if (value == expectedValue) {
                value = newValue;
                return true;
            } else {
                return false;
            }
        }
    }

    // 定义自增方法
    public long incrementAndGet() {
        long oldValue;
        long newValue;
        do {
            oldValue = value;
            newValue = oldValue + 1;
        } while (!compareAndSwap(oldValue, newValue));
        return newValue;
    }
}
