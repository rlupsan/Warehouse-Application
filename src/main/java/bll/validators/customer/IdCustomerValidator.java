package bll.validators.customer;

import bll.validators.Validator;
import model.Customer;

public class IdCustomerValidator implements Validator<Customer> {
    /**
     * This method validates whether the given customer id input of the customer is positive
     *
     * @param customer - the customer to be validated
     */
    public void validate(Customer customer) {
        if (customer.getIdCustomer() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}
