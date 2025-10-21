package Hotel.rooms;

import Hotel.enums.Prices;

public class LuxRoom extends ProRoom {
    public LuxRoom(int roomNumber, int maxPeople, int pricePerNight) {
        super(roomNumber, maxPeople, pricePerNight);
    }

    public LuxRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}