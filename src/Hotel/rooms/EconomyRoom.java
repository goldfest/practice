package Hotel.rooms;

public class EconomyRoom extends Room {
    public EconomyRoom(int roomNumber, int maxPeople, int pricePerNight) {
        super(roomNumber, maxPeople, pricePerNight);
    }

    public EconomyRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}