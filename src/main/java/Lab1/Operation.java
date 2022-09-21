package Lab1;

public interface Operation {

    default int execute(int a, int b) { return 0; }
}
