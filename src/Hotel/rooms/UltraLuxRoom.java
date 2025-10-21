package Hotel.rooms;

import Hotel.enums.Prices;

public class UltraLuxRoom extends LuxRoom {
    public UltraLuxRoom(int roomNumber, int maxPeople, int pricePerNight) {
        super(roomNumber, maxPeople, pricePerNight);
    }

    public UltraLuxRoom(int roomNumber,  int pricePerNight) {
        super(roomNumber,  pricePerNight);
    }
}