import java.util.*;
import java.util.stream.Collectors;

//по заданию 2.3:
class Car implements Comparable<Car> {
    private String VIN;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;
    private double price;

    public Car(String VIN, String model, String manufacturer, int year, int mileage, double price) {
        this.VIN = VIN;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
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
}

public class CarPark {

    //задание 2.1 массивы
    public static void task2_1() {
        System.out.println("Задание 2.1 Массивы");

        int[] productionYears = new int[50];
        Random random = new Random();

        for (int i = 0; i < productionYears.length; i++) {
            productionYears[i] = 2000 + random.nextInt(26); // 2000-2025
        }

        System.out.println("Все года выпуска: " + Arrays.toString(productionYears));

        System.out.println("\nМашины после 2015 года:");
        for (int year : productionYears) {
            if (year > 2015) {
                System.out.print(year + " ");
            }
        }

        // Средний возраст авто
        int currentYear = java.time.Year.now().getValue();
        double totalAge = 0;
        for (int year : productionYears) {
            totalAge += (currentYear - year);
        }
        double averageAge = totalAge / productionYears.length;
        System.out.println("\n\nСредний возраст авто: " + averageAge);
    }

    //задание 2.2 коллекции
    public static void task2_2() {
        System.out.println("\nЗадание 2.2 Коллекции");

        List<String> carModels = new ArrayList<>(Arrays.asList(
                "Toyota Camry", "Lada Vesta", "Lada Granta", "Lada Niva",
                "Tesla Model S", "Toyota Camry",
                "Audi A4", "Tesla Model 3", "BMW X5", "Honda Civic"
        ));

        System.out.println("Исходный список: " + carModels);

        //удаление дубликатов
        Set<String> uniqueModels = new LinkedHashSet<>(carModels);
        List<String> sortedModels = new ArrayList<>(uniqueModels);

        //сортировка в обратном алфавитном порядке
        sortedModels.sort(Collections.reverseOrder());
        System.out.println("После удаления дубликатов и сортировки: " + sortedModels);

        //замена Tesla на ELECTRO_CAR
        for (int i = 0; i < sortedModels.size(); i++) {
            if (sortedModels.get(i).contains("Tesla")) {
                sortedModels.set(i, "ELECTRO_CAR");
            }
        }
        System.out.println("После замены Tesla: " + sortedModels);

        Set<String> finalSet = new TreeSet<>(Collections.reverseOrder());
        finalSet.addAll(sortedModels);
        System.out.println("Итоговый Set: " + finalSet);
    }

    //задание 2.3 Equals/hashCode
    public static void task2_3() {
        System.out.println("\nЗадание 2.3 Equals/hashCode (Сравнение автомобилей)");

        //создание с дубликатами VIN
        Car car1 = new Car("VIN001", "Lada Vesta", "Lada", 2015, 180000, 450000);
        Car car2 = new Car("VIN001", "Lada Granta", "Lada", 2017, 220000, 380000);
        Car car3 = new Car("VIN002", "UAZ Patriot", "UAZ", 2012, 350000, 850000);
        Car car4 = new Car("VIN003", "GAZ Gazelle", "GAZ", 2010, 480000, 1200000);

        Set<Car> carSet = new HashSet<>();
        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3);
        carSet.add(car4);

        System.out.println("Машины в HashSet (дубликаты не добавлены):");
        carSet.forEach(System.out::println);

        //сортировка по году
        List<Car> sortedCars = new ArrayList<>(carSet);
        Collections.sort(sortedCars);
        System.out.println("\nОтсортировано по году:");
        sortedCars.forEach(System.out::println);
    }

    //задание 2.4 Stream API
    public static void task2_4() {
        System.out.println("\nЗадание 2.4 Stream API");

        List<Car> cars = Arrays.asList(
                new Car("VIN001", "Camry", "Toyota", 2020, 45000, 25000),
                new Car("VIN002", "X5", "BMW", 2022, 35000, 60000),
                new Car("VIN003", "Granta", "Lada", 2023, 15000, 80000),
                new Car("VIN004", "Accord", "Honda", 2019, 60000, 22000),
                new Car("VIN005", "Corolla", "Toyota", 2021, 25000, 20000),
                new Car("VIN006", "Model 3", "Tesla", 2022, 30000, 45000),
                new Car("VIN007", "80", "Audi", 1994, 550000, 10000)
        );

        //фильтрация по пробегу < 50.000 км
        List<Car> lowMileageCars = cars.stream()
                .filter(car -> car.getMileage() < 50000)
                .collect(Collectors.toList());
        System.out.println("Машины с пробегом < 50.000 км:");
        lowMileageCars.forEach(System.out::println);

        //сортировка по цене (по убыванию)
        List<Car> sortedByPrice = cars.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .collect(Collectors.toList());
        System.out.println("\nОтсортировано по цене (по убыванию):");
        sortedByPrice.forEach(System.out::println);

        //топ-3 самые дорогие машины
        List<Car> top3Expensive = cars.stream()
                .sorted((c1, c2) -> Double.compare(c2.getPrice(), c1.getPrice()))
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("\nТоп-3 самые дорогие машины:");
        top3Expensive.forEach(System.out::println);

        //средний пробег всех машин
        double averageMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.printf("\nСредний пробег всех машин: %.2f км\n", averageMileage);

        //группировка по производителю
        Map<String, List<Car>> carsByManufacturer = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));
        System.out.println("\nМашины сгруппированы по производителю:");
        carsByManufacturer.forEach((manufacturer, carList) -> {
            System.out.println(manufacturer + ": " + carList.size() + " машин");
            carList.forEach(car -> System.out.println("  - " + car.getModel()));
        });
    }

    public static void main(String[] args) {
        task2_1();  //массивы
        task2_2();  //коллекции
        task2_3();  //Equals/hashCode
        task2_4();  //Stream API
    }
}