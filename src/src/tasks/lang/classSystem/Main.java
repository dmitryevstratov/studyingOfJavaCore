package src.tasks.lang.classSystem;

public class Main {

    public static void main(String[] args) {

        int[] ints = {2,4,56,7,1,9};
        int[] ints2 = new int[ints.length];
        System.err.println("Error");
        System.arraycopy(ints, 1, ints2,2,3);
        for (int i : ints2) {
            System.out.println(i);
        }
        System.out.println(System.currentTimeMillis());
        System.out.println(System.identityHashCode(ints));
        System.out.println(ints.hashCode());


        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors());
        System.out.println(runtime.freeMemory());
        System.out.println(runtime.totalMemory());
    }

}
