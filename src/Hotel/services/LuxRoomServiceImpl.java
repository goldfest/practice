package Hotel.services;

import Hotel.exceptions.RoomAlreadyBookedException;
import Hotel.rooms.LuxRoom;

public class LuxRoomServiceImpl<T extends LuxRoom> implements LuxRoomService<T> {

    // Реализация методов из RoomService
    @Override
    public void clean(T room) {
        System.out.println("Люкс комната " + room.getRoomNumber() + " очищена с повышенным комфортом");
    }

    @Override
    public void reserve(T room) {
        if (room.isBooked()) {
            throw new RoomAlreadyBookedException("Люкс комната " + room.getRoomNumber() + " уже забронирована!");
        }
        room.setBooked(true);
        System.out.println("Люкс комната " + room.getRoomNumber() + " забронирована с премиальным обслуживанием");
    }

    @Override
    public void free(T room) {
        room.setBooked(false);
        System.out.println("Люкс комната " + room.getRoomNumber() + " освобождена");
    }

    // Реализация нового метода для доставки еды
    @Override
    public void foodDelivery(T room, String order) {
        if (!room.isBooked()) {
            System.out.println("Внимание: комната " + room.getRoomNumber() + " не забронирована, но доставка осуществляется");
        }
        System.out.println("Премиальная доставка еды в комнату " + room.getRoomNumber() + ": " + order);
    }
}