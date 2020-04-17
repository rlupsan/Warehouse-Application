package bll.validators.orderitem;

import bll.validators.Validator;
import model.OrderItem;

public class IdProductOrderItemValidator implements Validator<OrderItem> {
    /**
     * This method validates whether the given product id input of the order item is positive
     *
     * @param orderItem - the order item to be validated
     */
    public void validate(OrderItem orderItem) {
        if (orderItem.getProductId() < 0) {
            throw new IllegalArgumentException("The id cannot be negative!");
        }
    }
}
