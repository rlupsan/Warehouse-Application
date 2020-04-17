package bll.validators.orderitem;

import bll.validators.Validator;
import model.OrderItem;

public class IdCustomerOrderValidator implements Validator<OrderItem> {
    /**
     * This method validates whether the given customer id input of the order item is positive
     *
     * @param orderItem - the order item to be validated
     */
    public void validate(OrderItem orderItem) {
        if (orderItem.getCustomerId() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}
