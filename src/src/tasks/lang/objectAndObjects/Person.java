package src.tasks.lang.objectAndObjects;

import java.util.Objects;

class Person {

    private final String name;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person main = (Person) o;
        return age == main.age && Objects.equals(name, main.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    private final int age;

    public static void main(String[] args) {

        Person ob1 = new Person("Кекс", 6);
        Person ob2 = new Person("Кекс", 6);
        Person ob3 = new Person("Митя", 28);
        Person ob4 = ob3;
        Object ob5 = new Object();

        System.out.println(Objects.equals(ob3,ob4));
        System.out.println(Objects.hashCode(ob1));
        System.out.println(Objects.hashCode(ob2));
        System.out.println(Objects.hashCode(ob3));
        System.out.println(Objects.hash(ob1,ob2));
        ob4 = null;
        System.out.println(Objects.nonNull(ob4));
        System.out.println(Objects.checkIndex(1,3));
        System.out.println(Objects.checkFromToIndex(2,6,7));

    }

}
