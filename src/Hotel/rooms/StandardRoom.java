package Hotel.rooms;

public class StandardRoom extends EconomyRoom {
    public StandardRoom(int roomNumber, int maxPeople, int pricePerNight) {
        super(roomNumber, maxPeople, pricePerNight);
    }

    public StandardRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}