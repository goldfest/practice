package Hotel.enums;

public enum Prices {
    ECONOMY(2300),
    STANDARD(3200),
    LUX(7500),
    ULTRA_LUX(10000);

    private final int price;

    Prices(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}