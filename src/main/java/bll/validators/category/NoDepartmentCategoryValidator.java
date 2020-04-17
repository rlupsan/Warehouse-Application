package bll.validators.category;

import bll.validators.Validator;
import model.Category;

public class NoDepartmentCategoryValidator implements Validator<Category> {
    /**
     * This method validates whether the given number of department input of the category is positive
     *
     * @param category - the category to be validated
     */
    public void validate(Category category) {
        if (category.getNoDepartment() <= 0) {
            throw new IllegalArgumentException("Number of department cannot be <= 0!");
        }
    }
}
