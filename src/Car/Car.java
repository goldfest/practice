package Car;

import java.util.Objects;

//доп.задание
enum CarType {
    SEDAN, SUV, ELECTRIC, HATCHBACK, TRUCK, MINIVAN, COUPE, UNIVERSAL
}
//по заданию 2.3:
class Car implements Comparable<Car> {
    private String VIN;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;
    private double price;
    //доп
    private CarType type;

    public Car(String VIN, String model, String manufacturer, int year, int mileage, double price, CarType type) {
        this.VIN = VIN;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
        //доп
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(VIN, car.VIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(VIN);
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year); // от новых к старым
    }

    @Override
    public String toString() {
        return String.format("Car{VIN='%s', model='%s', manufacturer='%s', year=%d, mileage=%d, price=%.2f}",
                VIN, model, manufacturer, year, mileage, price);
    }

    public String getVIN() { return VIN; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }
    //доп
    public CarType getType() { return type; }
}