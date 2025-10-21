package Hotel;

import Hotel.enums.Prices;
import Hotel.exceptions.RoomAlreadyBookedException;
import Hotel.rooms.*;
import Hotel.services.LuxRoomService;
import Hotel.services.LuxRoomServiceImpl;
import Hotel.services.RoomService;
import Hotel.services.RoomServiceImpl;

// 9. тестирование
public class RoomTest {
    public static void main(String[] args) {
        //создание сервисов
        RoomService<EconomyRoom> economyService = new RoomServiceImpl<>();
        RoomService<StandardRoom> standardService = new RoomServiceImpl<>();
        RoomService<LuxRoom> luxService = new RoomServiceImpl<>();
        RoomService<UltraLuxRoom> ultraLuxService = new RoomServiceImpl<>();

        // создание сервисов для люксовых комнат с дополнительным функционалом
        LuxRoomService<LuxRoom> luxRoomService = new LuxRoomServiceImpl<>();
        LuxRoomService<UltraLuxRoom> ultraLuxRoomService = new LuxRoomServiceImpl<>();

        //создание комнат
        EconomyRoom economyRoom = new EconomyRoom(23, Prices.ECONOMY.getPrice());
        StandardRoom standardRoom = new StandardRoom(41, Prices.STANDARD.getPrice());
        LuxRoom luxRoom = new LuxRoom(76, Prices.LUX.getPrice());
        UltraLuxRoom ultraLuxRoom = new UltraLuxRoom(54, Prices.ULTRA_LUX.getPrice());

        //EconomyRoom
        System.out.println("EconomyRoom");
        economyService.clean(economyRoom);
        economyService.reserve(economyRoom);

        //бронируем снова - должна быть ошибка
        try {
            economyService.reserve(economyRoom);
        } catch (RoomAlreadyBookedException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        economyService.free(economyRoom);

        //StandardRoom
        System.out.println("\nStandardRoom");
        standardService.clean(standardRoom);
        standardService.reserve(standardRoom);
        standardService.free(standardRoom);

        //LuxRoom
        System.out.println("\nLuxRoom");
        luxService.clean(luxRoom);
        luxService.reserve(luxRoom);
        luxService.free(luxRoom);

        //LuxRoom с люксовым сервисом (доп. задание)
        System.out.println("\nLuxRoom (доп. сервис)");
        luxRoomService.clean(luxRoom);
        luxRoomService.reserve(luxRoom);
        luxRoomService.foodDelivery(luxRoom, "водка со льдом");
        luxRoomService.free(luxRoom);

        //UltraLuxRoom
        System.out.println("\nUltraLuxRoom");
        ultraLuxService.clean(ultraLuxRoom);
        ultraLuxService.reserve(ultraLuxRoom);
        ultraLuxService.free(ultraLuxRoom);

        // UltraLuxRoom с люксовым сервисом (доп. задание)
        System.out.println("\nUltraLuxRoom (доп. сервис)");
        ultraLuxRoomService.clean(ultraLuxRoom);
        ultraLuxRoomService.reserve(ultraLuxRoom);
        ultraLuxRoomService.foodDelivery(ultraLuxRoom, "устрицы, лобстер и кристалл");
        ultraLuxRoomService.free(ultraLuxRoom);

        //проверка с разными типами через один сервис
        System.out.println("\nдженерик тест");
        RoomService<Room> genericService = new RoomServiceImpl<>();

        genericService.reserve(economyRoom);
        genericService.reserve(standardRoom);

        //вывод цен из перечисления
        System.out.println("\nЦены из перечисления Prices");
        System.out.println("ECONOMY: " + Prices.ECONOMY.getPrice() + " руб.");
        System.out.println("STANDARD: " + Prices.STANDARD.getPrice() + " руб.");
        System.out.println("LUX: " + Prices.LUX.getPrice() + " руб.");
        System.out.println("ULTRA_LUX: " + Prices.ULTRA_LUX.getPrice() + " руб.");

        System.out.println("Готово");
    }
}