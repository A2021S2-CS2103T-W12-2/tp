package seedu.address.testutil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import seedu.address.model.customer.Address;
import seedu.address.model.customer.Car;
import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.DateOfBirth;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Customer objects.
 */
public class CustomerBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DATE_OF_BIRTH = "2020 01 31";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private DateOfBirth dateOfBirth;
    private Set<Tag> tags;
    private Map<Car, CoeExpiry> carsOwned;
    private Set<Car> carsPreferred;

    /**
     * Creates a {@code CustomerBuilder} with the default details.
     */
    public CustomerBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        dateOfBirth = new DateOfBirth(DEFAULT_DATE_OF_BIRTH);
        tags = new HashSet<>();
        carsOwned = new HashMap<>();
        carsPreferred = new HashSet<>();
    }

    /**
     * Initializes the CustomerBuilder with the data of {@code customerToCopy}.
     */
    public CustomerBuilder(Customer customerToCopy) {
        name = customerToCopy.getName();
        phone = customerToCopy.getPhone();
        email = customerToCopy.getEmail();
        address = customerToCopy.getAddress();
        dateOfBirth = customerToCopy.getDateOfBirth();
        tags = new HashSet<>(customerToCopy.getTags());
        carsOwned = new HashMap<>(customerToCopy.getCarsOwned());
        carsPreferred = new HashSet<>(customerToCopy.getCarsPreferred());
    }

    /**
     * Sets the {@code Name} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code DateOfBirth} of the {@code Customer} that we are building.
     */
    public CustomerBuilder withDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = new DateOfBirth(dateOfBirth);
        return this;
    }

    /**
     * Parses the {@code carsOwned} into a {@code Map<Car, CoeExpiry>} and set it to the {@code Customer} that we are
     * building.
     *
     * @param carsOwned
     * @return
     */
    public CustomerBuilder withCarsOwned(Map<Car, CoeExpiry> carsOwned) {
        this.carsOwned = new HashMap<>(carsOwned);
        return this;
    }

    /**
     * Parses the {@code cars} into a {@code Set<Car>} and set it to the {@code Customer} that we are building.
     */
    public CustomerBuilder withCarsPreferred(String... cars) {
        this.carsPreferred = SampleDataUtil.getCarSet(cars);
        return this;
    }

    public Customer build() {
        return new Customer(name, phone, email, address, dateOfBirth, tags, carsOwned, carsPreferred);
    }

}
