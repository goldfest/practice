package Hotel.rooms;

import Hotel.enums.Prices;

public class EconomyRoom extends Room {
    public EconomyRoom(int roomNumber, int maxPeople) {
        super(roomNumber, maxPeople, Prices.ECONOMY.getPrice());
    }

    public EconomyRoom(int roomNumber) {
        super(roomNumber, Prices.ECONOMY.getPrice());
    }
}