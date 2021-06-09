package index.factory;

/**
 * 抽象工厂模式
 */
public class FactoryPattern {

    public static void main(String[] args) {
        Factory iPoneFactory = new iPoneFactory();
        Phone iPhone = iPoneFactory.createPhone();
        iPhone.print();

        Factory xiaomiFactory = new XiaomiFactory();
        Phone xiaomi = xiaomiFactory.createPhone();
        xiaomi.print();
    }
}

interface Phone{
    void print();
}

class iPhone implements Phone{
    public void print(){
        System.out.println("create iPhone");
    }
}

class Xiaomi implements Phone{
    public void print(){
        System.out.println("create Xiaomi");
    }
}

interface Factory {
    Phone createPhone();
}

class iPoneFactory implements Factory {
    @Override
    public Phone createPhone() {
        return new iPhone();
    }
}

class XiaomiFactory implements Factory{
    @Override
    public Phone createPhone() {
        return new Xiaomi();
    }
}