package vehiclerental;

import java.time.LocalTime;
import java.util.Objects;

public class Car implements Rentable {
    private String id;
    private int pricePerMinute;
    private LocalTime rentingTime;

    public Car(String id, int pricePerMinute) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be set");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return pricePerMinute == car.pricePerMinute && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pricePerMinute);
    }
}
