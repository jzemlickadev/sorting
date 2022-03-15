package sorting;

import java.util.Arrays;
import java.util.Random;

public class CountingSortSample {

    /**
     * Sort array of number
     *
     * ArrayToSort 7,8,1,2,6
     * Min 0
     * Max 8
     *
     * Prepare helperArray
     * Number (from min to max)             0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8
     * Occurrence (index represents number) 0 | 1 | 1 | 0 | 0 | 0 | 1 | 1 | 1
     *  a[7]=1 means there is exactly 1 7 in the array
     *
     *  Sum numbers one by one from the first element of the array Occurrence
     *
     *  Helper array
     *   0 | 1 | 2 | 2 | 2 | 2 | 3 | 4 | 5
     *
     *  Go reverse - ArrayToSort
     *  7 | 8 | 1 | 2 | 6
     *
     *  1st iteration - checking last element of the ArrayToSort -> 6
     *  check the 5th (6 - 1) position of the helper array -> 2
     *  Place number six to the 2nd position of the array.
     *  Decrement value (-1) at the helper array 2-> 1 ....
     *
     *
     *
     * @param args
     */

    public static void main(String[] args) {

        int arraySize = 5;
        int maxElementValue = arraySize;
        int[] arrayToSort = new int[arraySize];


        Random random = new Random();
        for (int a=0; a < arrayToSort.length; a++) {
            arrayToSort[a] = random.nextInt(maxElementValue);
        }


        int[] sorted = sort(arrayToSort, maxElementValue);
        System.out.println("Input array:" + Arrays.toString(arrayToSort));
        System.out.println("Sorted     :" + Arrays.toString(sorted));
    }

    public static int[] getValueOccurrenceArray(int[] arrayToSort, int maxElementValue) {
        int[] helpArray = new int[maxElementValue + 1];//Default value is 0 no need to change it

        for (int i : arrayToSort) { // Count occurrence of number
            helpArray[i]++;
        }

        for (int i = 1; i < helpArray.length; i++) { // Sum neighbour number one by one from the first element -> a[1]=a[0]+a[1] and a[2]=[a1]+a[2] etc
            helpArray[i] = helpArray[i] + helpArray[i - 1];
        }

        return helpArray;
    }

    public static int[] sort(int[] arrayToSort, int maxElementValue) {
        int[] helperArray = getValueOccurrenceArray(arrayToSort, maxElementValue);

        int[] sorted = new int[arrayToSort.length];
        for (int i = arrayToSort.length - 1; i >= 0; i--) {
            int numberToSort = arrayToSort[i];
            sorted[helperArray[numberToSort] - 1] = numberToSort;
            helperArray[numberToSort]--;
        }

        return sorted;

    }


}
