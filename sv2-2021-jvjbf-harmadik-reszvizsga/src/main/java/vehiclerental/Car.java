package vehiclerental;

import java.time.LocalTime;

public class Car implements Rentable {
    private String id;
    private int pricePerMinute;
    private LocalTime rentingTime;

    public Car(String id, int pricePerMinute) {
        this.id = id;
        this.pricePerMinute = pricePerMinute;
    }

    public String getId() {
        return id;
    }

    public int getPricePerMinute() {
        return pricePerMinute;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (pricePerMinute * minutes);
    }

    @Override
    public LocalTime getRentingTime() {
        return rentingTime;
    }

    @Override
    public void rent(LocalTime time) {
        rentingTime = time;
    }

    @Override
    public void closeRent() {
        rentingTime = null;
    }
}
