package Practices.task1.self;

import java.util.Arrays;

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
    }
}
