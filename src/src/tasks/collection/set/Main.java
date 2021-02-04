package src.tasks.collection.set;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();

    }

    private static void extracted(Set<Integer> set) {
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + ",");
        }
        System.out.println();
    }

}
