package bll.validators.customer;

import bll.validators.Validator;
import model.Customer;

import java.util.regex.Pattern;

public class PhoneCustomerValidator implements Validator<Customer> {
    private static final String PHONE_PATTERN = "^(\\+4|)?(07[0-8]{1}[0-9]{1}|02[0-9]{2}|03[0-9]{2}){1}?(\\s|\\.|\\-)?([0-9]{3}(\\s|\\.|\\-|)){2}$";

    /**
     * This method validates whether the given phone input of product is well written with respect to the regex String - PHONE_PATTERN
     * The source of the PHONE_PATTERN regex is: https://regexr.com/39fv1
     * It applies to the Romanian phone numbers
     *
     * @param customer - the customer to be validated
     */
    public void validate(Customer customer) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        if (!pattern.matcher(customer.getPhoneCustomer()).matches()) {
            throw new IllegalArgumentException("Phone number is not a valid phone number!");
        }
    }
}
