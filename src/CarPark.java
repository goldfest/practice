import java.util.*;
import java.util.stream.Collectors;

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
        Car car1 = new Car("VIN001", "Lada Vesta", "Lada", 2015, 180000, 450000, CarType.SEDAN);
        Car car2 = new Car("VIN001", "Lada Granta", "Lada", 2017, 220000, 380000, CarType.HATCHBACK);
        Car car3 = new Car("VIN002", "UAZ Patriot", "UAZ", 2012, 350000, 850000, CarType.TRUCK);
        Car car4 = new Car("VIN003", "GAZ Gazelle", "GAZ", 2010, 480000, 1200000,  CarType.MINIVAN);

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
                new Car("VIN001", "Camry", "Toyota", 2020, 45000, 25000, CarType.SEDAN),
                new Car("VIN002", "X5", "BMW", 2022, 35000, 60000, CarType.SEDAN),
                new Car("VIN003", "Granta", "Lada", 2023, 15000, 80000, CarType.SEDAN),
                new Car("VIN004", "Accord", "Honda", 2019, 60000, 22000, CarType.SEDAN),
                new Car("VIN005", "Corolla", "Toyota", 2021, 25000, 20000, CarType.SEDAN),
                new Car("VIN006", "Model 3", "Tesla", 2022, 30000, 45000, CarType.SEDAN),
                new Car("VIN007", "80", "Audi", 1994, 550000, 10000, CarType.SEDAN)
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

    //создание меню
    public static void menu() {
        CarDealership dealership = new CarDealership();
        Scanner scanner = new Scanner(System.in);

        // Добавляем тестовые данные
        dealership.addCar(new Car("VIN001", "Lada Vesta", "Lada", 2022, 45000, 1200000, CarType.SEDAN));
        dealership.addCar(new Car("VIN002", "Lada Granta", "Lada", 2021, 60000, 800000, CarType.SEDAN));
        dealership.addCar(new Car("VIN003", "UAZ Patriot", "UAZ", 2020, 80000, 1500000, CarType.SUV));
        dealership.addCar(new Car("VIN004", "GAZ Gazelle", "GAZ", 2019, 150000, 2000000, CarType.TRUCK));
        dealership.addCar(new Car("VIN005", "Tesla Model 3", "Tesla", 2023, 10000, 3500000, CarType.ELECTRIC));

        boolean running = true;
        while (running) {
            System.out.println("\nМеню Автоцентр 'Вано-авто'");
            System.out.println("1. Показать все машины");
            System.out.println("2. Добавить машину");
            System.out.println("3. Найти машины по производителю");
            System.out.println("4. Средняя цена по типу");
            System.out.println("5. Машины отсортированные по году");
            System.out.println("6. Статистика по типам");
            System.out.println("7. Самая старая и новая машина");
            System.out.println("0. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nВсе машины в автоцентре:");
                    dealership.printAllCars();
                    break;

                case 2:
                    System.out.print("Введите VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Введите модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Введите производителя: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Введите год выпуска: ");
                    int year = scanner.nextInt();
                    System.out.print("Введите пробег: ");
                    int mileage = scanner.nextInt();
                    System.out.print("Введите цену: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Выберите тип (SEDAN, SUV, ELECTRIC, HATCHBACK, TRUCK, MINIVAN, COUPE, UNIVERSAL): ");
                    String typeStr = scanner.nextLine().toUpperCase();
                    CarType type;
                    try {
                        type = CarType.valueOf(typeStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный тип, установлен SEDAN по умолчанию.");
                        type = CarType.SEDAN;
                    }

                    Car newCar = new Car(vin, model, manufacturer, year, mileage, price, type);
                    dealership.addCar(newCar);
                    break;

                case 3:
                    System.out.print("Введите производителя: ");
                    String manuf = scanner.nextLine();
                    List<Car> foundCars = dealership.findCarsByManufacturer(manuf);
                    if (foundCars.isEmpty()) {
                        System.out.println("Машины производителя " + manuf + " не найдены");
                    } else {
                        System.out.println("Найдено машин: " + foundCars.size());
                        foundCars.forEach(System.out::println);
                    }
                    break;

                case 4:
                    System.out.println("Выберите тип для расчета средней цены: ");
                    String avgTypeStr = scanner.nextLine().toUpperCase();
                    try {
                        CarType avgType = CarType.valueOf(avgTypeStr);
                        double avgPrice = dealership.getAveragePriceByType(avgType);
                        System.out.printf("Средняя цена машин типа %s: %.2f руб.\n", avgType, avgPrice);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Неверный тип!");
                    }
                    break;

                case 5:
                    List<Car> sortedCars = dealership.getCarsSortedByYear();
                    System.out.println("Машины отсортированные по году:");
                    sortedCars.forEach(System.out::println);
                    break;

                case 6:
                    Map<CarType, Long> typeStats = dealership.getCarCountByType();
                    System.out.println("Кол-во машин по типам:");
                    typeStats.forEach((carType, count) ->
                            System.out.println(carType + ": " + count + " машин"));
                    break;

                case 7:
                    dealership.printOldestAndNewestCars();
                    break;

                case 0:
                    running = false;
                    System.out.println("Выход из программы...");
                    break;

                default:
                    System.out.println("Неверный выбор");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        task2_1();  //массивы
        task2_2();  //коллекции
        task2_3();  //Equals/hashCode
        task2_4();  //Stream API
        menu(); //доп меню
    }
}