package Practices.task3.self;

import java.util.Scanner;

public class CalculationMain {

    public static void main(String[] args) {

        Addition addition = new Addition();
        Subtraction subtraction = new Subtraction();
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();
        String add = addition.operation(a, b);
        String sub = subtraction.operation(a, b);
        System.out.println("Sum is: " + add);
        System.out.println("Diff is: " + sub);
    }
}
