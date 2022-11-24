package Practices.task3.self;

import java.util.Scanner;

public class CalculationMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        scanner.close();

        System.out.println("Sum is: " + new Addition().operation(a, b));
        System.out.println("Diff is: " + new Subtraction().operation(a, b));
    }
}
