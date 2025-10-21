package Hotel.rooms;

// 1.класс Room
public abstract class Room {
    private int roomNumber;
    private int maxPeople;
    private int pricePerNight;
    private boolean isBooked;

    // 2.конструкторы
    public Room(int roomNumber, int maxPeople, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.maxPeople = maxPeople;
        this.pricePerNight = pricePerNight;
        this.isBooked = false;
    }

    public Room(int roomNumber, int pricePerNight) {
        this(roomNumber, (int)(Math.random() * 4) + 1, pricePerNight); // Случайное количество человек от 1 до 4
    }

    public int getRoomNumber() { return roomNumber; }
    public int getMaxPeople() { return maxPeople; }
    public int getPricePerNight() { return pricePerNight; }
    public boolean isBooked() { return isBooked; }
    public void setBooked(boolean booked) { isBooked = booked; }
}







