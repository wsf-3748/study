package index.mapper;

public class Test3 {

    public static void main(String[] args) {
        Parent p = new Child(); // 先执行父类构造器，再执行子类构造器
        p.t1(); // 重写了t1方法，执行子类的t1方法
        Parent.t2(); // 静态方法，调用Parent类中的t2方法，父类引用指向子类对象时，只会调用父类的静态方法。
        p.t3(); // 没有重写t3方法，执行父类的t3方法
//        p.t5(); // 私有方法，不能使用实例来调用

        System.out.println("--------------");
        Child c = new Child(); // 先执行父类构造器，再执行子类构造器
        c.t1(); // 重写了t1方法，执行子类的t1方法
        Child.t2(); // 隐藏了t2方法，静态方法，调用Child类中的t2方法。
                    // 该子类实际上只是将父类中的同名静态方法进行了隐藏，而非重写。它们的行为也并不具有多态性--重写和重载
        c.t3(); // 没有重写t3方法，执行父类的t3方法
        c.t4(); // 执行自己类的方法
//        c.t5(); // 私有方法，不能使用实例来调用

        float f = 3.14f;
        double d = 1031201234163.015;
        short s = 32767;
    }
}

class Parent {

    public Parent() {
        System.out.println("i'm parent");
    }

    public void t1() {
        System.out.println("Parent.t1()");
    }

    public static void t2() {
        System.out.println("Parent.t2()");
    }

    public void t3() {
        System.out.println("Parent.t3()");
    }

    private void t5() {
        System.out.println("Parent.t5()");
    }
}

class Child extends Parent {

    public Child() {
        System.out.println("i'm child");
    }

    public void t1() {
        System.out.println("Child.t1()");
    }

    // 该子类实际上只是将父类中的同名静态方法进行了隐藏，而非重写。
    public static void t2() {
        System.out.println("Child.t2()");
    }

    public void t4() {
        System.out.println("Child.t4()");
    }

    private void t5() {
        System.out.println("Child.t5()");
    }
}
