package index.factory;

/**
 * 简单工厂模式
 */
public class SimpleFactory {

    public static Product createProduct(String type) {
        if ("A".equals(type)) {
            return new ProductA();
        } else {
            return new ProductB();
        }
    }

    public static void main(String[] args) {
        Product a = SimpleFactory.createProduct("A");
        Product b = SimpleFactory.createProduct("B");

        a.print();
        b.print();
    }
}

abstract class Product{
    public abstract void print();
}

class ProductA extends Product{

    @Override
    public void print() {
        System.out.println("产品A");
    }
}

class ProductB extends Product{

    @Override
    public void print() {
        System.out.println("产品B");
    }
}