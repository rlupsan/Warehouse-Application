package bll;

import bll.validators.Validator;
import bll.validators.product.NameProductValidator;
import bll.validators.product.PriceProductValidator;
import dao.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private static List<Validator<Product>> validators;

    /**
     * This is the constructor of the ProductBLL
     * it does not have any parameters
     * it just adds the specific validators in the validators -ArrayList
     * The specific validators are: PriceProductValidator, NameProductValidator
     */
    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new PriceProductValidator());
        validators.add(new NameProductValidator());
    }

    /**
     * This method validates the input of the product using the validators and it inserts the product in the database
     * using the insert method from ProductDAO
     *
     * @param product - the product to be inserted
     * @return product - the inserted product
     */
    public static int insert(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return ProductDAO.insert(product);
    }

    /**This method validates the input of the product using the validators and it updates the product in the database
     * using the insert method from ProductDAO
     * @param product - the product to be updated
     * @return product - the updated customer
     */
    public static int update(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return ProductDAO.update(product);
    }

    /**This method validates the input of the product and it finds and return if positive the product
     * @param idProduct - the id of the product to be found
     * @return product*/
    public Product findProductById(int idProduct) {
        Product product = ProductDAO.findProductById(idProduct);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + idProduct + " was not found!");
        }
        return product;
    }

}
