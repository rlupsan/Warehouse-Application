package bll.validators.product;

import bll.validators.Validator;
import model.Product;

public class PriceProductValidator implements Validator<Product> {

    /**
     * This method validates whether the given price input of the product is positive
     *
     * @param product - the product to be validated
     */
    public void validate(Product product) {
        if (product.getPrice() <= 0) {
            throw new IllegalArgumentException("Price cannot be <= 0!");
        }
    }
}
