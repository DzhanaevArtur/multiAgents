package Practices.task1.example;

import java.util.Arrays;

public class FirstSelf {

    public static void main(String[] args) {

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
        int selfTask2 = 12345678, resultReversedNum = 0;
        System.out.println("Integer before reverse: " + selfTask2);
        while (selfTask2 != 0) {
            resultReversedNum = resultReversedNum * 10 + selfTask2 % 10;
            selfTask2 /= 10;
        }
        System.out.println("Integer after reverse: " + resultReversedNum);
    }
}
