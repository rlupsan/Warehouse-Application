package bll.validators.product;

import bll.validators.Validator;
import model.Product;

import java.util.regex.Pattern;

public class NameProductValidator implements Validator<Product> {
    private static final String NAME_PATTERN = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";

    /**
     * This method validates whether the given name input of product is well written with respect to the regex String - NAME_PATTERN
     * The source of the NAME_PATTERN regex is: https://www.regextester.com/93648
     * It applies to all possible names
     * @param product - the product to be validated
     */
    public void validate(Product product) {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        if (!pattern.matcher(product.getNameProduct()).matches()) {
            throw new IllegalArgumentException("Name is not well written!");
        }
    }
}
