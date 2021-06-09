package index.factory;

import java.util.ArrayList;
import java.util.List;

public class ObserverPattern {

    public static void main(String[] args) {
        Debit zhansan = new ZhangSan();
        zhansan.borrow(new WangWu()); // 王五借钱给张三
        zhansan.borrow(new LiSi()); // 李四借钱给张三
        // 过了一段时间
        zhansan.notifyCredits(); // 张三有钱了，一个一个去还
    }
}

interface Debit{
    void borrow(Credit credit);
    void notifyCredits();
}

class ZhangSan implements Debit {
    private final List<Credit> allCredits = new ArrayList<>(); // 所有借钱给张三的人

    @Override
    public void borrow(Credit credit) {
        allCredits.add(credit);
    }

    @Override
    public void notifyCredits() {
        // lambda表达式，表示调用takeMoney()方法
        allCredits.forEach(Credit::takeMoney);
    }
}

interface Credit{
    void takeMoney();
}

class WangWu implements Credit{
    @Override
    public void takeMoney() {
        System.out.println("我是王五，我收到钱了");
    }
}

class LiSi implements Credit{
    @Override
    public void takeMoney() {
        System.out.println("我是李四，我收到钱了");
    }
}