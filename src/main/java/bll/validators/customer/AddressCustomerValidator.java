package bll.validators.customer;

import bll.validators.Validator;
import model.Customer;

import java.util.regex.Pattern;

//Good for testing: https://regex101.com/

public class AddressCustomerValidator implements Validator<Customer> {
    private static final String ADDRESS_PATTERN = "\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.";

    /**
     * This method validates whether the given name input of customer is well written with respect to the regex String - ADDRESS_PATTERN
     * It applies to the addresses names like the ones below:
     * ex: 253 N. Cherry St.
     * 202 N. Brancusi St.
     * General Address: (number) N. (name in letters) St.
     * The source of the ADDRESS_PATTERN is: https://stackoverflow.com/questions/11456670/regular-expression-for-address-field-validation
     */
    public void validate(Customer customer) {
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        if (!pattern.matcher(customer.getAddressCustomer()).matches()) {
            throw new IllegalArgumentException("Address is not well written!");
        }
    }
}
