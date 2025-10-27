package lec4;

import java.util.function.Consumer;

class HeavyBox {
    private double weight;

    public HeavyBox(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}

public class Lambda4 {
    public static void main(String[] args) {
        //отгрузка
        Consumer<HeavyBox> shipBox = box ->
                System.out.println("Отгрузили ящик с весом " + box.getWeight());

        //отправка
        Consumer<HeavyBox> sendBox = box ->
                System.out.println("Отправляем ящик с весом " + box.getWeight());

        Consumer<HeavyBox> processBox = shipBox.andThen(sendBox);

        HeavyBox box = new HeavyBox(25.5);
        processBox.accept(box);
    }
}