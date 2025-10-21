package Hotel.services;

import Hotel.rooms.Room;

// 5.интерфейс RoomService с дженериком
public interface RoomService<T extends Room> {
    void clean(T room);
    void reserve(T room);
    void free(T room);
}