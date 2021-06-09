package index.mapper;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    public Test1() {

    }

    public static void main(String[] args) {
//        Integer[] in = new Integer[]{1,3,5,9,6,8,7,2,4,10};
//        System.out.println(binarySearch(in, 6));

//        System.out.println(Integer.toBinaryString(5));
//        System.out.println(Integer.toBinaryString(-5));

//        Map<String, String> map = new HashMap<>();
//        map.put("1", "qwe");
//        System.out.println(map.get("1"));
//        System.out.println("sd".equals("a"));

        int x = 10;
        int y = 10;
        String str1 = new String("abc");
        String str2 = new String("abc");
        System.out.println(x == y); // true
        System.out.println(str1 == str2); // false
        System.out.println(str1.equals(str2)); // true

        Integer i1 = new Integer(10);
        Integer i2 = new Integer(10);
        System.out.println(i1 == i2); // false
        System.out.println(i1.equals(i2)); // true

        char[] ch = new char[]{'a','b','c'};
        String str = new String(ch);
        System.out.println(str);
        System.out.println(hashCode1(ch, 0));
        System.out.println(ch[1]);

        System.out.println(Math.round(-1.5)); // -1
        System.out.println(Math.round(-1.3)); // -1
        System.out.println(Math.round(1.5)); // 2
        System.out.println(Math.round(1.3)); // 1

    }

    public static int hashCode1(char[] value, int hash) {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }


    public static int binarySearch(Integer[] srcArray, int des) {
        int low = 0;
        int high = srcArray.length - 1;

        /*
         * 1. <<，有符号左移位，将运算数的二进制整体左移指定位数，低位用0补齐。运算结果就是x = (x * 2^n)。
         * 2. >>，有符号右移位，将运算数的二进制整体右移指定位数，整数高位用0补齐，负数高位用1补齐（保持负数符号不变）。
         * 右移，如果运算数是负的偶数，那么它的运算结果就是 x = -(|x| / 2^n)
         *      如果运算数是负的奇数，那么它的运算结果就是 x = -(|x| / 2^n) - 1
         *      如果运算数是正数，那么它的运算结果就是 x = x / 2^n
         * 3. >>>，无符号右移位，不管正数还是负数，高位都用0补齐（忽略符号位）
         * 4. AND (位与&) OR ( 位或| ) XOR ( 位异或^ )
         *    1 & 1 = 1， 1 | 1 = 1， 1 ^ 1 = 0
         *    1 & 0 = 0， 1 | 0 = 1， 1 ^ 0 = 1
         *    0 & 1 = 0， 0 | 1 = 1， 0 ^ 1 = 1
         *    0 & 0 = 0， 0 | 0 = 0， 0 ^ 0 = 0
         */
        while ((low <= high) && (low <= srcArray.length - 1) && (high <= srcArray.length - 1)) {
            // >>右移，表示运算数是偶数，那么它的运算结果就是 x = -(|x| / 2^n)
            // 如果运算数是负的奇数，那么它的运算结果就是 x = -(|x| / 2^n) - 1
            int middle = low + ((high - low) >> 1);
            if (des == srcArray[middle]) {
                return middle;
            } else if (des < srcArray[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }
}
