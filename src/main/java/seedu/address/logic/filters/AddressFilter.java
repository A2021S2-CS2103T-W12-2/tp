package seedu.address.logic.filters;

import seedu.address.model.customer.Customer;

import java.util.List;
import java.util.stream.Collectors;

public class AddressFilter extends AbstractFilter {
    public AddressFilter(String filterString) {
        super(filterString.trim());
    }

    @Override
    public boolean test(Customer customer) {
        return customer.getAddress().value.trim().startsWith(filterString);
    }
}
