package seedu.address.logic.filters;

import java.util.Objects;

import seedu.address.model.customer.Customer;

/**
 * This class is used to test whether a {@code Customer} has a Date of Birth which satisfies a given filtration
 * criterion by a {@code filterString} which is given to build the object. The filtration criterion is a simple check of
 * whether the date of birth contains the given string as a substring.
 */
public class DobFilter extends Filter {
    /**
     * Constructor for DoBFilter, creates a list of keywords in the date, to later match with the customer's DOB.
     *
     * @param filterString the birthdate to match. No particular format needed.
     */
    public DobFilter(String filterString) {
        super(filterString);
    }

    /**
     * Checks whether the birth date of the customer contains the {@code filterString} that was given to this object at
     * the time of creation.
     *
     * @param customer the customer for which to check
     * @return whether the filterString is present
     */
    @Override
    public boolean test(Customer customer) {
        Objects.requireNonNull(customer);
        String dob = customer.getDateOfBirth().getBirthDate();
        return dob.contains(filterString.trim());
    }
}
