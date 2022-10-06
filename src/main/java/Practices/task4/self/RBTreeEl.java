package Practices.task4.self;

import lombok.Getter;
import lombok.Setter;

public class RBTreeEl<T> {

    @Getter @Setter
    private T value;

    @Getter @Setter
    private RBTreeEl<T> leftValue;

    @Getter @Setter
    private RBTreeEl<T> rightValue;

    public RBTreeEl(T value) { this.value = value; }
}
