package lec4;

import java.util.function.Predicate;

public class Lambda3 {
    public static void main(String[] args) {
        //проверяем, что строка начинается с "J" или "N" и заканчивается на "A"
        Predicate<String> stringChecker = s -> {
            if (s == null || s.length() < 2) return false;
            char firstChar = s.charAt(0);
            char lastChar = s.charAt(s.length() - 1);
            return (firstChar == 'J' || firstChar == 'N') && lastChar == 'A';
        };

        String[] testStrings = {"JAVA", "NOVA", "PYTHON", "JAVASCRIPT", "NA", "J", null, ""};

        for (String str : testStrings) {
            System.out.println("'" + str + "' matches pattern: " + stringChecker.test(str));
        }
    }
}