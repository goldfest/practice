package lec4;

@FunctionalInterface
interface Printable {
    void print();
}

public class Lambda1 {
    public static void main(String[] args) {
        Printable printable = () -> System.out.println("Hello from Printable!");
        printable.print();
    }
}