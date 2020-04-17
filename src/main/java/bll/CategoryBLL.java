package bll;

import bll.validators.Validator;
import bll.validators.category.NoDepartmentCategoryValidator;
import dao.CategoryDAO;
import model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CategoryBLL {
    private static List<Validator<Category>> validators;

    /**
     * This is the constructor of the CategoryBLL
     * it does not have any parameters
     * it just adds the specific validators in the validators - ArrayList
     * The specific validators are: NoDepartmentCategoryValidator
     */
    public CategoryBLL() {
        validators = new ArrayList<>();
        validators.add(new NoDepartmentCategoryValidator());
    }

    /**
     * This method validates the input of the category using the validators and it inserts the category in the database
     * using the insert method from CategoryDAO
     *
     * @param category - the category to be inserted
     * @return category - the inserted category
     */
    public static int insert(Category category) {
        for (Validator<Category> v : validators) {
            v.validate(category);
        }
        return CategoryDAO.insert(category);
    }

    /**This method validates the input of the category using the validators and it updates the category in the database
     * using the insert method from CategoryDAO
     * @param category - the category to be updated
     * @return category - the updated category
     */
    public static int update(Category category) {
        for (Validator<Category> v : validators) {
            v.validate(category);
        }
        return CategoryDAO.update(category);
    }

    /**This method validates the input of the category and it finds and return if positive the category
     * @param idCategory - the id of the category to be found
     * @return category*/
    public Category findCategoryById(int idCategory) {
        Category category = CategoryDAO.findCategoryById(idCategory);
        if (category == null) {
            throw new NoSuchElementException("The category with id =" + idCategory + " was not found!");
        }
        return category;
    }
}
