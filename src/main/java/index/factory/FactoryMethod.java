package index.factory;

/**
 * 工厂方法模式
 */
public class FactoryMethod {

    public static void main(String[] args) {
        Animal cat = new CatFactory().createAnimal();
        Animal dog = new DogFactory().createAnimal();

        cat.say();
        dog.say();
    }
}

// 动物
interface Animal{
    void say(); // 动物叫
}

class Cat implements Animal{
    @Override
    public void say() {
        System.out.println("猫叫...");
    }
}

class Dog implements Animal{
    @Override
    public void say() {
        System.out.println("狗叫...");
    }
}

// 动物工厂
interface AnimalFactory{
    Animal createAnimal(); // 创建动物
}

// 猫工厂
class CatFactory implements AnimalFactory{
    @Override
    public Animal createAnimal() {
        return new Cat();
    }
}

// 狗工厂
class DogFactory implements AnimalFactory{
    @Override
    public Animal createAnimal() {
        return new Dog();
    }
}