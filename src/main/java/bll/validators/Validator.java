package bll.validators;

public interface Validator<T> {
    /**
     * This method is package-private and it validates
     *
     * @param t - the object T
     */
    void validate(T t);
}
