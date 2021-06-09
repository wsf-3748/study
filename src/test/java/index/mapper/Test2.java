package index.mapper;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test2 {

    static final Unsafe unsafe = getUnsafe();
    static final boolean is64bit = true; // auto detect if possible.

    public static void main(String[] args) {
        final Random random = new Random();
        final List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt());
        }
        System.out.println(list.toString());
        printAddresses("list前", list);
        System.out.println(list.getClass().getName() + "@" + Integer.toHexString(list.hashCode()));
        list.add(1997);
        System.out.println(list.toString());
        printAddresses("list后", list);
        System.out.println(list.getClass().getName() + "@" + Integer.toHexString(list.hashCode()));

        String x = "abc";
        printAddresses("x前", x);
        x = "bca";
        printAddresses("x后", x);

        StringBuffer sb = new StringBuffer();
        sb.append("ab");
        printAddresses("sb后", sb);
        sb.append("c");
        printAddresses("sb后", sb);

        String str = new String("abcd");
        printAddresses("str前", str);
        str = "dsfsdf";
        printAddresses("str后", str);
    }

    /*
     * 查看内存中的地址
     */
    public static void printAddresses(String label, Object... objects) {
        System.out.print(label + ":         0x");
        long last = 0;
        int offset = unsafe.arrayBaseOffset(objects.getClass());
        int scale = unsafe.arrayIndexScale(objects.getClass());
        switch (scale) {
            case 4:
                long factor = is64bit ? 8 : 1;
                final long i1 = (unsafe.getInt(objects, offset) & 0xFFFFFFFFL) * factor;
                System.out.print(Long.toHexString(i1));
                last = i1;
                for (int i = 1; i < objects.length; i++) {
                    final long i2 = (unsafe.getInt(objects, offset + i * 4) & 0xFFFFFFFFL) * factor;
                    if (i2 > last)
                        System.out.print(", +" + Long.toHexString(i2 - last));
                    else
                        System.out.print(", -" + Long.toHexString(last - i2));
                    last = i2;
                }
                break;
            case 8:
                throw new AssertionError("Not supported");
        }
        System.out.println();
    }

    private static Unsafe getUnsafe() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }
}
