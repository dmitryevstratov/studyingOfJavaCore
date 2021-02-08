package src.tasks.sorts;

import java.util.Arrays;

public class BubbleSort {

    public void sortArr(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[j] > array[i + 1]) {
                    int tmp = array[i + 1];
                    array[i + 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] arrInt = {9, 8, 7, 5, 4, 0, -3};
        bubbleSort.sortArr(arrInt);
        System.out.println(Arrays.toString(arrInt));
    }

}
