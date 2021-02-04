package src.tasks.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

class Main {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        list.add(2);
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(92);
        list.add(22);
        list.add(132);
        list.add(12);

        Iterator<Integer> iterator1 = list.iterator(); //returned obj of private class Itr
        while (iterator1.hasNext()){
            if(iterator1.next() == 22){
                iterator1.remove();
            }
        }
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}
