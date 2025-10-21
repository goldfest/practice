package Hotel.rooms;

abstract class ProRoom extends Room {
    public ProRoom(int roomNumber, int maxPeople, int pricePerNight) {
        super(roomNumber, maxPeople, pricePerNight);
    }

    public ProRoom(int roomNumber, int pricePerNight) {
        super(roomNumber, pricePerNight);
    }
}