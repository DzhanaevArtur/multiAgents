package Practices.task2.self;

public class SelfTask1 {

    private final int number;

    public SelfTask1(int number) {
        this.number = number;
    }

    public boolean isPalindrome() {
        char[] input = String.valueOf(this.number).toCharArray();
        int count = 0;
        for (int i = 0; i < input.length / 2; i++) {
            for (int j = i + input.length / 2; j < input.length - 1; j++) {
                if (input[i] == input[j]) {
                    count++;
                }
            }
        }
        return count == input.length / 2;
    }

    public int sumOfNumbers() {
        int first = this.number;
        int count = 0;
        while (first > 0) {
            count += first % 10;
            first /= 10;
        }
        return count;
    }
}
