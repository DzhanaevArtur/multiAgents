package Practices.FirstTask;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FirstMain {

    public static void main(String[] args) {

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("First task");
        int[] array = {10, 1, 5, 7, 8, 4, 3, 46, 22};
        double sum = Arrays.stream(array).mapToDouble(x -> Math.pow(x, 2) / array.length).sum();
        System.out.printf("Average array elements squares: %.3f\n", sum);
        System.out.println("----------------------------------------------------------------------------------------");

        System.out.println("Second task");
        System.out.println("Array before choose sort: " + Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int buff = array[i];
                    array[i] = array[j];
                    array[j] = buff;
                }
            }
        }
        System.out.println("Array after choose sort: " + Arrays.toString(array));
        System.out.println("----------------------------------------------------------------------------------------");

        System.out.println("Self task #1");
        int[] artArray = {43, 51, 321, 78, 89, 67, 14, 33, 99, 400};
        System.out.println("Array before bubble sort: " + Arrays.toString(artArray));
        while (true) {
            int count = 0;
            for (int i = 0; i < artArray.length; i++) {
                for (int j = i + 1; j < artArray.length; j++) {
                    if (artArray[j] < artArray[i]) {
                        int buff = artArray[i];
                        artArray[i] = artArray[j];
                        artArray[j] = buff;
                    } else if (artArray[j] > artArray[i]) {
                        count++;
                    }
                }
            }
            if (count >= artArray.length) {
                break;
            }
        }
        double median;
        if (artArray.length % 2 != 0) {
            median = artArray[artArray.length / 2];
        } else {
            median = 1.0 * (artArray[(artArray.length / 2) - 1] + artArray[artArray.length / 2]) / 2;
        }
        System.out.println("Array after bubble sort: " + Arrays.toString(artArray));
        System.out.println("Median value of array is: " + median);
        System.out.println("----------------------------------------------------------------------------------------");

        System.out.println("Self task #2");
        int selfTask2 = 12345678;
        System.out.println("Integer before reverse: " + selfTask2);
        StringBuilder selfTask2SB = new StringBuilder();
        List<Integer> selfTask2LinkedList = new LinkedList<>();
//        selfTask2LinkedList.stream().distinct(); TODO
        while (selfTask2 > 0) {
            selfTask2SB.append(selfTask2 % 10);
            selfTask2 /= 10;
        }
        System.out.println("Integer after reverse: " + selfTask2SB);
        System.out.println("----------------------------------------------------------------------------------------");
    }
}
