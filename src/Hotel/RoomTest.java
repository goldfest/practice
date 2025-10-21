package Hotel;

import Hotel.exceptions.RoomAlreadyBookedException;
import Hotel.rooms.*;
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

        //создание комнат
        EconomyRoom economyRoom = new EconomyRoom(23, 1100);
        StandardRoom standardRoom = new StandardRoom(41, 3000);
        LuxRoom luxRoom = new LuxRoom(76, 7500);
        UltraLuxRoom ultraLuxRoom = new UltraLuxRoom(54, 10000);

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

        //UltraLuxRoom
        System.out.println("\nUltraLuxRoom");
        ultraLuxService.clean(ultraLuxRoom);
        ultraLuxService.reserve(ultraLuxRoom);
        ultraLuxService.free(ultraLuxRoom);

        //проверка с разными типами через один сервис
        System.out.println("\nдженерик тест");
        RoomService<Room> genericService = new RoomServiceImpl<>();

        genericService.reserve(economyRoom);
        genericService.reserve(standardRoom);

        System.out.println("Готово");
    }
}