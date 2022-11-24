package Practices.task3.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ThirdTaskMain {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/main/java/Practices/task3/example/thirdTask.txt"));
        int[] parseArray = new int[scanner.nextInt()];
        for (int i = 0; i < parseArray.length; i++) parseArray[i] = scanner.nextInt();
        scanner.close();
        System.out.println("Average = " + String.format("%.3f", Arrays.stream(parseArray).average().orElse(0)));
    }
}
