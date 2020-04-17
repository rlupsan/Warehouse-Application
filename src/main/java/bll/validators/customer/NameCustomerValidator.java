package bll.validators.customer;

import bll.validators.Validator;
import model.Customer;

import java.util.regex.Pattern;

public class NameCustomerValidator implements Validator<Customer> {

    private static final String NAME_PATTERN = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    /**
     * This method validates whether the given name input of customer is well written with respect to the regex String - NAME_PATTERN
     * The source of the NAME_PATTERN regex is: https://www.regextester.com/93648
     * It applies to all possible names
     * @param customer - the customer to be validated
     */
    public void validate(Customer customer) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(customer.getNameCustomer()).matches()) {
            throw new IllegalArgumentException("Name is not well written!");
        }
    }
}
