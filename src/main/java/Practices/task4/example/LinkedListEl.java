package Practices.task4.example;

import lombok.Getter;
import lombok.Setter;

public class LinkedListEl<T> {

    @Getter @Setter
    private T value;
    @Getter @Setter
    private LinkedListEl<T> rightEl;
    @Getter @Setter
    private LinkedListEl<T> leftEl;

    public LinkedListEl(T value) { this.value = value;}

}
