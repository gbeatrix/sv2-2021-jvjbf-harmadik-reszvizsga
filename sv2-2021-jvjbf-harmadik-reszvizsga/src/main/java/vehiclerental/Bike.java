package vehiclerental;

import java.time.LocalTime;
import java.util.Objects;

public class Bike implements Rentable {
    private String id;
    private LocalTime rentingTime;
    private static final int PRICE_PER_MINUTE = 15;

    public Bike(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be set");
        }
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int calculateSumPrice(long minutes) {
        return (int) (PRICE_PER_MINUTE * minutes);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike bike = (Bike) o;
        return Objects.equals(id, bike.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
