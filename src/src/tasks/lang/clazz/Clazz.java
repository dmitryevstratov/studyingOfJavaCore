package src.tasks.lang.clazz;

import java.lang.reflect.Field;

public class Clazz {

    public static void main(String[] args) {

        Class cl1 = Boolean.class;

        for (Field declaredField : cl1.getDeclaredFields()) {
            System.out.println(declaredField);
        }

    }

}
