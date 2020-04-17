package model;

public class Product {
    private int quantityOnStock;
    private int idProduct;
    private String nameProduct;
    private int categoryId;
    private double price;

    /**
     * This is the constructor of the Product
     *
     * @param idProduct       - the id of the product
     * @param nameProduct     - the name of the product
     * @param categoryId      - the id of the product
     * @param price           - the price of the product
     * @param quantityOnStock - the quantity on stock of the product
     */
    public Product(int idProduct, String nameProduct, int categoryId, double price, int quantityOnStock) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.categoryId = categoryId;
        this.price = price;
        this.quantityOnStock = quantityOnStock;
    }

    /**
     * This is the second constructor of the Product
     *
     * @param nameProduct     - the name of the product
     * @param categoryId      - the id of the product
     * @param price           - the price of the product
     * @param quantityOnStock - the quantity on stock of the product
     */
    public Product(String nameProduct, int categoryId, double price, int quantityOnStock) {
        this.nameProduct = nameProduct;
        this.categoryId = categoryId;
        this.price = price;
        this.quantityOnStock = quantityOnStock;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityOnStock() {
        return quantityOnStock;
    }

    public void setQuantityOnStock(int quantityOnStock) {
        this.quantityOnStock = quantityOnStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", quantityOnStock=" + quantityOnStock +
                '}';
    }
}
