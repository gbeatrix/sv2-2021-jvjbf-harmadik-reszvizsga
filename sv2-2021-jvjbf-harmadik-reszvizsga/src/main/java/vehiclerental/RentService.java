package vehiclerental;

import java.time.LocalTime;
import java.util.*;

public class RentService {
    private Set<Rentable> rentables = new LinkedHashSet<>();
    private Set<User> users = new LinkedHashSet<>();
    private Map<Rentable, User> actualRenting = new TreeMap<>(Comparator.comparing(Rentable::getRentingTime));

    public Set<Rentable> getRentables() {
        return new LinkedHashSet<>(rentables);
    }

    public Set<User> getUsers() {
        return new LinkedHashSet<>(users);
    }

    public Map<Rentable, User> getActualRenting() {
        return new LinkedHashMap<>(actualRenting);
    }

    public void registerUser(User user) {
        if (user == null) {
            throw new IllegalStateException("User cannot be null");
        }
        if (users.contains(user)) {
            throw new UserNameIsAlreadyTakenException("Username is taken!");
        }
        users.add(user);
    }

    public void addRentable(Rentable rentable) {
        if (rentable == null) {
            throw new IllegalArgumentException("Rentable cannot be null");
        }
        rentables.add(rentable);
    }

    public void rent(User user, Rentable rentable, LocalTime time) {
        if (isRentable(rentable) && canRent(user, rentable.calculateSumPrice(3L * 60))) {
            rentable.rent(time);
            actualRenting.put(rentable, user);
        } else {
            throw new IllegalStateException("Something went wrong. " + user + rentable + time);
        }
    }

    private boolean canRent(User user, int price) {
        return user.getBalance() >= price;
    }

    private boolean isRentable(Rentable rentable) {
        return rentable.getRentingTime() == null;
    }

    public void closeRent(Rentable rentable, int minutes) {
        if (actualRenting.containsKey(rentable)) {
            User user = actualRenting.remove(rentable);
            rentable.closeRent();
            user.minusBalance(rentable.calculateSumPrice(minutes));
        } else {
            throw new IllegalArgumentException("Cannot find rent.");
        }
    }
}
