package bll.validators.orderitem;

import bll.validators.Validator;
import model.OrderItem;

public class QuantityOrderItemValidator implements Validator<OrderItem> {

    /**
     * This method validates whether the given quantity input of the order item is positive
     *
     * @param orderItem - the order item to be validated
     */
    public void validate(OrderItem orderItem) {
        if (orderItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity cannot be <= 0!");
        }
    }
}
