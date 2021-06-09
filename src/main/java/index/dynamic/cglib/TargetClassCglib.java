package index.dynamic.cglib;

/**
 * 创建目标对象
 */
public class TargetClassCglib {

    public void add() {
        System.err.println("I am add");
    }

    public void del() {
        System.err.println("I am del");
        System.err.println("del end --------------------");
    }

    public void update() {
        System.err.println("I am update");
    }
}
