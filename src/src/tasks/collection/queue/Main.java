package src.tasks.collection.queue;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
    }

    private static void extracted(Queue<Integer> queue) {
        System.out.println("----------------");
        Iterator<Integer> iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("----------------");
    }

}
