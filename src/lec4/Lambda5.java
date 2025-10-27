package lec4;

import java.util.function.Function;

public class Lambda5 {
    public static void main(String[] args) {
        //Function для определения типа числа
        Function<Integer, String> numberType = num -> {
            if (num > 0) return "Положительное число";
            else if (num < 0) return "Отрицательное число";
            else return "Ноль";
        };

        int[] numbers = {5, -3, 0, 10, -7};

        for (int num : numbers) {
            System.out.println(num + ": " + numberType.apply(num));
        }
    }
}