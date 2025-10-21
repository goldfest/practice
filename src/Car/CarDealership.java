package Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//класс автоцентра
class CarDealership {
    private List<Car> cars;

    public CarDealership() {
        this.cars = new ArrayList<>();
    }

    //1. добавление машины в автоцентр
    public boolean addCar(Car car) {
        if (cars.contains(car)) {
            System.out.println("Машина с VIN " + car.getVIN() + " уже существует!");
            return false;
        }
        cars.add(car);
        System.out.println("Машина " + car.getModel() + " успешно добавлена!");
        return true;
    }

    //2. поиск всех машин указанного производителя
    public List<Car> findCarsByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    //3. вывод средней цены машин определённого типа
    public double getAveragePriceByType(CarType type) {
        return cars.stream()
                .filter(car -> car.getType() == type)
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0);
    }

    //4. возврат списка машин, отсортированных по году выпуска
    public List<Car> getCarsSortedByYear() {
        return cars.stream()
                .sorted(Comparator.comparingInt(Car::getYear).reversed())
                .collect(Collectors.toList());
    }

    //5.1 кол-во машин каждого типа
    public Map<CarType, Long> getCarCountByType() {
        return cars.stream()
                .collect(Collectors.groupingBy(Car::getType, Collectors.counting()));
    }

    //5.2 самая старая и самая новая машина в наличии
    public void printOldestAndNewestCars() {
        Car oldest = cars.stream()
                .min(Comparator.comparingInt(Car::getYear))
                .orElse(null);
        Car newest = cars.stream()
                .max(Comparator.comparingInt(Car::getYear))
                .orElse(null);

        System.out.println("Самая старая машина: " + oldest);
        System.out.println("Самая новая машина: " + newest);
    }

    //вывод всех машин
    public void printAllCars() {
        if (cars.isEmpty()) {
            System.out.println("В автоцентре нет машин!");
            return;
        }
        cars.forEach(System.out::println);
    }
}