package lec4;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

//аннотация для JSON полей
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

class Person {
    @JsonField(name = "person_name")
    private String name;

    @JsonField(name = "person_age")
    private int age;

    @JsonField(name = "email_address")
    private String email;

    //не будет сериализовано
    private String password;

    public Person(String name, int age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}

//JSON сериализатор
class JSON {
    public static String toJson(Object obj) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            List<String> jsonFields = new ArrayList<>();

            for (Field field : fields) {
                if (field.isAnnotationPresent(JsonField.class)) {
                    field.setAccessible(true);

                    JsonField annotation = field.getAnnotation(JsonField.class);
                    String fieldName = annotation.name();
                    Object value = field.get(obj);

                    String jsonValue;
                    if (value instanceof String) {
                        jsonValue = "\"" + value + "\"";
                    } else {
                        jsonValue = String.valueOf(value);
                    }

                    jsonFields.add("\"" + fieldName + "\": " + jsonValue);
                }
            }

            json.append(String.join(", ", jsonFields));

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Ошибка доступа к полям объекта", e);
        }

        json.append("}");
        return json.toString();
    }
}

public class JSONExample {
    public static void main(String[] args) {
        Person person = new Person("Иван Иванов", 30, "vano@mail.ru", "12345");
        String json = JSON.toJson(person);
        System.out.println("JSON: " + json);
    }
}