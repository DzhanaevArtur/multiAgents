package Practices.task4.example;

import java.util.Arrays;

public class ForthMain {

    public static void main(String[] args) {

        MyLinkedList<int[]> listArt = new MyLinkedList<>();
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 3, 4, 5, 1};
        int[] arr3 = {3, 4, 5, 1, 2};
        int[] arr4 = {4, 5, 1, 2, 3};
        int[] arr5 = {5, 1, 2, 3, 4};
        listArt.add(arr1);
//        listArt.add(arr2);
//        listArt.add(arr3);
//        listArt.add(arr4);
//        listArt.add(arr5);

        listArt
                .stream()
                .map(Arrays::toString)
                .limit(listArt.size())
                .forEach(System.out::println);
    }
}
