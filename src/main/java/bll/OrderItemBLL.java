package bll;

import bll.validators.Validator;
import bll.validators.orderitem.QuantityOrderItemValidator;
import dao.OrderItemDAO;
import model.OrderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderItemBLL {
    private static List<Validator<OrderItem>> validators;

    /**
     * This is the constructor of the OrderBLL
     * it does not have any parameters
     * it just adds the specific validators in the validators - ArrayList
     * The specific validators are: QuantityOrderItemValidator
     */
    public OrderItemBLL() {
        validators = new ArrayList<>();
        validators.add(new QuantityOrderItemValidator());
    }


    /**
     * This method validates the input of the ordered item using the validators and it inserts the ordered item in the database
     * using the insert method from OrderItemDAO
     *
     * @param orderItem - the ordered item to be inserted
     * @return orderItem - the inserted order item
     */
    public static int insert(OrderItem orderItem) {
        for (Validator<OrderItem> v : validators) {
            v.validate(orderItem);
        }
        return OrderItemDAO.insert(orderItem);
    }

    /**This method validates the input of the ordered item and it finds and return if positive the ordered item
     * @param idOrderItem - the id of the order item to be found
     * @return orderItem
     */
    public OrderItem findOrderItemById(int idOrderItem) {
        OrderItem orderItem = OrderItemDAO.findOrderItemById(idOrderItem);
        if (orderItem == null) {
            throw new NoSuchElementException("The orderItem with id =" + idOrderItem + " was not found!");
        }
        return orderItem;
    }
}
