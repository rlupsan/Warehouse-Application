package model;

public class OrderItem {
    private int idOrderItem;
    private int customerId;
    private int productId;
    private int quantity;

    /**
     * This is the constructor of the Order Item
     *
     * @param idOrderItem - the id of the order item
     * @param customerId  - the id of the customer id
     * @param productId   - the id of the product
     * @param quantity    - the quantity of the order item
     */
    public OrderItem(int idOrderItem, int customerId, int productId, int quantity) {
        this.idOrderItem = idOrderItem;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * This is the constructor of the Order Item
     *
     * @param customerId - the id of the customer id
     * @param productId  - the id of the product
     * @param quantity   - the quantity of the order item
     */
    public OrderItem(int customerId, int productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getIdOrderItem() {
        return idOrderItem;
    }

    public void setIdOrderItem(int idOrderItem) {
        this.idOrderItem = idOrderItem;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "idOrderItem=" + idOrderItem +
                ", customerId=" + customerId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
