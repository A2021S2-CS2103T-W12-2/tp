package seedu.address.logic.filters;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.customer.Customer;

public abstract class AbstractFilter {
    protected final String filterString;

    public AbstractFilter(String filterString) {
        this.filterString = filterString;
    }

    public abstract boolean test(Customer customer);

    public List<Customer> filterAllCustomers(List<Customer> customers) {
        return customers.stream().filter(this::test).collect(Collectors.toUnmodifiableList());
    }
}
