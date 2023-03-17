package BLL.Validators;

public interface Validator<T> {
    /**
     * Validates a given object.
     * @param t the object to validate.
     */
    public void validate(T t);
}
