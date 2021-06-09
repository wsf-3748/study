package index.mapper;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public final class Test4 {

    public static void main(String[] args) {

//        System.out.println(reverse1("abc123"));
//        System.out.println(reverse2("abc123"));
//        System.out.println(reverse3("abc123"));
//        System.out.println(reverse4("abc123"));

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in ));
//        //java.io.InputStreamReader继承了Reader类
//        String read = null;
//        System.out.print("输入数据：");
//        while (true) {
//            try {
//                read = br.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            System.out.println("输入数据："+read);
//            if ("exit".equals(read)) {
//                break;
//            }
//        }

        List list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3bc");
        list.add(48);
        System.out.println(list.toString());

        List<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        System.out.println(linkedList.get(2));



        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        for (String s : hashSet) {
            System.out.println(s);
        }
        Map<String, String> map = new HashMap<>();
        map.put(null, null);
        map.put("bob", "books");
        map.put("c", "concurrent");
        map.put("a", "a lock");
        for (String key : map.keySet()) {
            System.out.println("key="+key);
        }

        for (String value : map.values()) {
            System.out.println("value="+value);
        }

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        
        Set<Map.Entry<String, String>> set = map.entrySet();
        for (Map.Entry<String, String> entry : set) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        Map<String, String> books = new TreeMap<>();
        books.put("bob", "books");
        books.put("c", "concurrent");
        books.put("a", "a lock");
        for (String s : books.keySet()) {
            System.out.println(s + "-" + books.get(s));
        }
    }


    public static String reverse1(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    public static String reverse2(String str) {
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse = reverse + chars[i];
        }
        return reverse;
    }

    public static String reverse3(String str) {
        String reverse = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            reverse = str.charAt(i) + reverse;
        }
        return reverse;
    }

    public static String reverse4(String s) {
        int length = s.length();
        if(length <= 1){
            return s;
        }
        String left = s.substring(0, length / 2);
        String right = s.substring(length / 2, length);
        return reverse4(right) + reverse4(left);
    }
}