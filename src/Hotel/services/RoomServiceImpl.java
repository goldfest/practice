package Hotel.services;

import Hotel.exceptions.RoomAlreadyBookedException;
import Hotel.rooms.Room;

// 6.класс, имплементирующий интерфейс
public class RoomServiceImpl<T extends Room> implements RoomService<T> {

    // 7.реализация методов интерфейса
    @Override
    public void clean(T room) {
        System.out.println("Комната " + room.getRoomNumber() + " очищена");
    }

    @Override
    public void reserve(T room) {
        if (room.isBooked()) {
            // 8. Выбрасываем ошибку при попытке забронировать занятую комнату
            throw new RoomAlreadyBookedException("Комната " + room.getRoomNumber() + " уже забронирована!");
        }
        room.setBooked(true);
        System.out.println("Комната " + room.getRoomNumber() + " забронирована");
    }

    @Override
    public void free(T room) {
        room.setBooked(false);
        System.out.println("Комната " + room.getRoomNumber() + " освобождена");
    }
}