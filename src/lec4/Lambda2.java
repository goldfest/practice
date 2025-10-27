package lec4;

import java.util.function.Predicate;

public class Lambda2 {
    public static void main(String[] args) {
        Predicate<String> isNotNull = s -> s != null;

        Predicate<String> isNotEmpty = s -> !s.isEmpty();

        Predicate<String> isNotNullAndNotEmpty = isNotNull.and(isNotEmpty);

        String test1 = null;
        String test2 = "";
        String test3 = "Hello";

        System.out.println("test1 is not null and not empty: " + isNotNullAndNotEmpty.test(test1));
        System.out.println("test2 is not null and not empty: " + isNotNullAndNotEmpty.test(test2));
        System.out.println("test3 is not null and not empty: " + isNotNullAndNotEmpty.test(test3));
    }
}