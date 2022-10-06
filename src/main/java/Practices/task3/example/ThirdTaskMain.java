package Practices.task3.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ThirdTaskMain {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/main/java/Practices/task3/example/thirdTask.txt");
        Scanner scanner = new Scanner(file);
        int size = scanner.nextInt();
        int[] parseArray = new int[size];
        for (int i = 0; i < parseArray.length; i++) {
            parseArray[i] = scanner.nextInt();
        }
        scanner.close();
        double arrayAverage = Arrays.stream(parseArray).average().orElse(0);
        System.out.println("Array average is: " + String.format("%.3f", arrayAverage));
    }
}
