package Practices.task3.self;

public class Addition extends Calculation {
    @Override
    String operation(int n1, int n2) {

        int a = Integer.parseInt(String.valueOf(n1), 2);
        int b = Integer.parseInt(String.valueOf(n2), 2);
        return Integer.toBinaryString(a + b);
    }
}
