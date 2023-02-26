package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

// Reused from Guides for AB3 Tutorial: Adding a command
// with minor modification

/**
 * Remark class enables user to store short notes as a description to a specific person.
 */
public class Remark {

    public static final String VALIDATION_REGEX = "[^\n]+";

    public final String value;

    /**
     * Creates a Remark object that stores remark message.
     *
     * @param remark for a person.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        value = remark;
    }

    public static boolean isValidRemark(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

// Reused from Guides for AB3 Tutorial: Adding a command
// with minor modification
