package bll;

import bll.validators.Validator;
import bll.validators.customer.*;
import dao.CustomerDAO;
import model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CustomerBLL {

    private static List<Validator<Customer>> validators;

    /**
     * This is the constructor of the CustomerBLL
     * it does not have any parameters
     * it just adds the specific validators in the validators -ArrayList
     * The specific validators are:
     * IdCustomerValidator, EmailCustomerValidator, PhoneCustomerValidator, NameCustomerValidator
     */
    public CustomerBLL() {
        validators = new ArrayList<>();
        validators.add(new IdCustomerValidator());
        validators.add(new EmailCustomerValidator());
        validators.add(new PhoneCustomerValidator());
        validators.add(new NameCustomerValidator());
        validators.add(new AddressCustomerValidator());
    }

    /**
     * This method validates the input of the customer using the validators and it inserts the customer in the database
     * using the insert method from CustomerDAO
     *
     * @param customer - the customer to be inserted
     * @return customer - the inserted customer
     */
    public static int insert(Customer customer) {
        for (Validator<Customer> v : validators) {
            v.validate(customer);
        }
        return CustomerDAO.insert(customer);
    }

    /**This method validates the input of the customer using the validators and it updates the customer in the database
     * using the insert method from CustomerDAO
     * @param customer - the customer to be updated
     * @return customer - the updated customer
     */
    public static int update(Customer customer) {
        for (Validator<Customer> v : validators) {
            v.validate(customer);
        }
        return CustomerDAO.update(customer);
    }

    /**This method validates the input of the product and it finds and return if positive the product
     * @param idCustomer - the id of the customer to be found
     * @return customer*/
    public Customer findCustomerById(int idCustomer) {
        Customer customer = CustomerDAO.findCustomerById(idCustomer);
        if (customer == null) {
            throw new NoSuchElementException("The customer with id =" + idCustomer + " was not found!");
        }
        return customer;
    }
}
