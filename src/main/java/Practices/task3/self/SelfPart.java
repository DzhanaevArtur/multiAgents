package Practices.task3.self;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class SelfPart {

    public static void main(String[] args) throws IOException {

        File file = new File("src/main/java/Practices/task3/self/thirdTaskSelfPart.txt");
        Scanner scanner = new Scanner(file);
        int target = Integer.parseInt(scanner.nextLine().trim());
        int size = Integer.parseInt(scanner.nextLine().trim());
        int[] array = new int[size];
        int iter = 0;
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] newStr = s.split(", ");
            int firstInteger = Integer.parseInt(newStr[0]);
            int secondInteger = Integer.parseInt(newStr[1]);
            array[iter] = firstInteger;
            array[iter + 1] = secondInteger;
            iter += 2;
        }
        scanner.close();
        System.out.println("Array from text file: " + Arrays.toString(array));

        FileWriter fileWriter = new FileWriter("src/main/java/Practices/task3/self/outputForThird.txt");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] + array[j] == target) {
                    fileWriter.write(array[j] + ", " + array[i] + "\n");
                }
            }
        }
        fileWriter.close();
    }
}
