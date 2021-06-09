package index.mapper;

import java.util.Objects;

/**
 * 需求：对比两个对象是否相等。对于下面的 User 对象，只需姓名和id相等则认为是同一个对象。
 */
public class Student {

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public Student(int age, String name, String id) {
        this.age = age;
        this.name = name;
        this.id = id;
    }

    private int age;
    private String name;
    private String id;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(getName(), student.getName()) && Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }
}
