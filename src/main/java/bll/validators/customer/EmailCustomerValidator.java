package bll.validators.customer;

import bll.validators.Validator;
import model.Customer;

import java.util.regex.Pattern;

public class EmailCustomerValidator implements Validator<Customer> {

    private static final String EMAIL_PATTERN = "^(.+)@(.+)$";

    /**
     * This method validates whether the given email input of customer is well written with respect to the regex String - EMAIL_PATTERN
     * It applies to all possible names
     *
     * @param customer - the customer to be validated
     */
    public void validate(Customer customer) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (!pattern.matcher(customer.getEmailCustomer()).matches()) {
            throw new IllegalArgumentException("Email is not a valid email!");
        }
    }

}
