package lec4;

import java.lang.annotation.*;
import java.lang.reflect.*;

//кастомная аннотация @DeprecatedEx
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface DeprecatedEx {
    String message();
}

//класс с устаревшими элементами
@DeprecatedEx(message = "Используйте класс NewImprovedClass")
class OldClass {

    @DeprecatedEx(message = "Используйте метод newMethod()")
    public void oldMethod() {}

    @DeprecatedEx(message = "Используйте метод anotherNewMethod()")
    public void anotherOldMethod() {}

    public void regularMethod() {}
}

//обработчик
class DeprecationHandler {
    public static void checkDeprecation(Class<?> clazz) {
        //проверка класса
        if (clazz.isAnnotationPresent(DeprecatedEx.class)) {
            DeprecatedEx annotation = clazz.getAnnotation(DeprecatedEx.class);
            System.out.println("! класс '" + clazz.getSimpleName() +
                    "' устарел – альтернатива: '" + annotation.message() + "'");
        }

        //проверка методов
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(DeprecatedEx.class)) {
                DeprecatedEx annotation = method.getAnnotation(DeprecatedEx.class);
                System.out.println("! метод '" + method.getName() +
                        "' устарел – альтернатива: '" + annotation.message() + "'");
            }
        }
    }
}

public class Deprecated {
    public static void main(String[] args) {
        DeprecationHandler.checkDeprecation(OldClass.class);
    }
}