package Hotel.rooms;

public class UltraLuxRoom extends ProRoom {
    public UltraLuxRoom(int roomNumber, int maxPeople, int pricePerNight) {
        super(roomNumber, maxPeople, pricePerNight);
    }

    public UltraLuxRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}