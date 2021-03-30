package seedu.address.logic.filters;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_OWNED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_PREFERRED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COE_EXPIRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.model.customer.Customer;

public class Filters {
    public static AbstractFilter getCorrespondingFilter(String info) {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(info, PREFIX_NAME, PREFIX_EMAIL, PREFIX_PHONE,
            PREFIX_ADDRESS, PREFIX_DOB, PREFIX_TAG, PREFIX_CARS_OWNED, PREFIX_CARS_PREFERRED);

        if (argumentMultimap.getTotalSize() != 1) {
            throw new NullPointerException("Number of filters between two logical operators should be exactly 1");
        }

        if (argumentMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            return new EmailFilter(argumentMultimap.getValue(PREFIX_EMAIL).get());
        }

        if (argumentMultimap.getValue(PREFIX_PHONE).isPresent()) {
            return new EmailFilter(argumentMultimap.getValue(PREFIX_PHONE).get());
        }

        if (argumentMultimap.getValue(PREFIX_COE_EXPIRY).isPresent()) {
            return new EmailFilter(argumentMultimap.getValue(PREFIX_COE_EXPIRY).get());
        }


        return new AbstractFilter(info) {

            @Override
            public boolean test(Customer customer) {
                return false;
            }

            @Override
            public List<Customer> filterAllCustomers(List<Customer> customer) {
                return null;
            }
        };
    }
}
