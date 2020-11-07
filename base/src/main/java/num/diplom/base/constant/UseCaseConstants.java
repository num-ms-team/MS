package num.diplom.base.constant;

public final class UseCaseConstants {

    private UseCaseConstants() {
    }

    public static final String REGISTER_NUMBER_REGEX = "\\p{IsCyrillic}{2}[0-9]{8}";

    public static final String INPUT_CANNOT_BE_NULL = "Input cannot be null!";
    public static final String INPUT_CANNOT_BE_NULL_OR_EMPTY = "Input cannot be null or empty!";
    public static final String INVALID_REGISTER_NUMBER = "User's register number invalid!";

    public static final String PATIENT_ID_CANNOT_BE_NULL_OR_EMPTY = "Patient ID cannot be null or empty!";
}
