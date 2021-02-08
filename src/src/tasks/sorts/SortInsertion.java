package src.tasks.sorts;

import java.util.Arrays;

public class SortInsertion {

    public void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j>=0 && current < array[j]){
                array[j+1] = array[j];
                j--;
            }
            array[j+1] = current;
        }
    }

    public static void main(String[] args) {

        SortInsertion sortInsertion = new SortInsertion();
        int[] arrInt = {6, 3, 2, 5, 4, 9, -3};
        sortInsertion.insertionSort(arrInt);
        System.out.println(Arrays.toString(arrInt));

    }

}
