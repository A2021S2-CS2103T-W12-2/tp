package seedu.address.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's preferred car in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCar(String)}
 */
public class Car {

    public static final String MESSAGE_CONSTRAINTS = "Car can take any values, and it should not be blank";

    /*
     * The first character of the preferred car must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String carId;

    /**
     * Constructs a {@code Car}.
     *
     * @param car A valid car.
     */
    public Car(String car) {
        requireNonNull(car);
        checkArgument(isValidCar(car), MESSAGE_CONSTRAINTS);
        carId = car;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidCar(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return carId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // Not checking carId since it shouldn't be unique
    }

    @Override
    public int hashCode() {
        return carId.hashCode();
    }

}
