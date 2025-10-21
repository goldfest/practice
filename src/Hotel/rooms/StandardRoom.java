package Hotel.rooms;

import Hotel.enums.Prices;

public class StandardRoom extends EconomyRoom {
    public StandardRoom(int roomNumber, int maxPeople) {
        super(roomNumber, maxPeople);
    }

    public StandardRoom(int roomNumber) {
        super(roomNumber);
    }

    @Override
    public int getPricePerNight() {
        return Prices.STANDARD.getPrice();
    }
}