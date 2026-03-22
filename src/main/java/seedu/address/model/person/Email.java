package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    private static final String SPECIAL_CHARACTERS = "+_.-";
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format local-part@domain "
            + "and adhere to the following constraints:\n"
            + "1. The local-part should only contain alphanumeric characters and these special characters, excluding "
            + "the parentheses, (" + SPECIAL_CHARACTERS + "). The local-part may not start or end with any special "
            + "characters.\n"
            + "2. This is followed by a '@' and then a domain name. The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n"
            + "    - have each domain label consist of alphanumeric characters, separated only by hyphens, if any.";
    // alphanumeric and special characters
    private static final String ALPHANUMERIC_NO_UNDERSCORE = "[^\\W_]+"; // alphanumeric characters except underscore
    private static final String LOCAL_PART_REGEX = "^" + ALPHANUMERIC_NO_UNDERSCORE + "([" + SPECIAL_CHARACTERS + "]"
            + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_PART_REGEX = ALPHANUMERIC_NO_UNDERSCORE
            + "(-" + ALPHANUMERIC_NO_UNDERSCORE + ")*";
    private static final String DOMAIN_LAST_PART_REGEX = "(" + DOMAIN_PART_REGEX + "){2,}$"; // At least two chars
    private static final String DOMAIN_REGEX = "(" + DOMAIN_PART_REGEX + "\\.)*" + DOMAIN_LAST_PART_REGEX;
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@" + DOMAIN_REGEX;

    public final String value;

    /**
     * Constructs an {@code Email}.
     *
     * @param email A valid email address.
     */
    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidEmail(email), MESSAGE_CONSTRAINTS);
        value = email;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns a specific error message describing why the given email is invalid.
     * Assumes the email has already failed {@link #isValidEmail(String)}.
     *
     * @param email {@code Email} that is input by user.
     * @return Error message if email is invalid.
     */
    public static String getDiagnosticMessage(String email) {
        String atSymbolError = checkAtSymbol(email);
        if (atSymbolError != null) {
            return atSymbolError;
        }

        String localPart = extractLocalPart(email);
        String domain = extractDomain(email);

        String localPartError = checkLocalPart(localPart);
        if (localPartError != null) {
            return localPartError;
        }

        String domainError = checkDomain(domain);
        if (domainError != null) {
            return domainError;
        }

        return MESSAGE_CONSTRAINTS;
    }

    private static String checkAtSymbol(String email) {
        if (!email.contains("@")) {
            return "Email is missing '@'. It should be in the format local-part@domain (e.g. johndoe@example.com).";
        }
        if (email.chars().filter(c -> c == '@').count() > 1) {
            return "Email contains more than one '@'. Only one '@' is allowed.";
        }
        return null;
    }

    private static String extractLocalPart(String email) {
        return email.substring(0, email.indexOf('@'));
    }

    private static String extractDomain(String email) {
        return email.substring(email.indexOf('@') + 1);
    }

    private static String checkLocalPart(String localPart) {
        if (localPart.isEmpty()) {
            return "Email is missing a local-part before '@'. It should be in the format local-part@domain.";
        }
        if (!localPart.matches("^" + ALPHANUMERIC_NO_UNDERSCORE + ".*")) {
            return "The local-part (before '@') must start with an alphanumeric character, not '"
                    + localPart.charAt(0) + "'.";
        }
        if (SPECIAL_CHARACTERS.contains(String.valueOf(localPart.charAt(localPart.length() - 1)))) {
            return "The local-part (before '@') must not end with a special character like '"
                    + localPart.charAt(localPart.length() - 1) + "'.";
        }
        if (!localPart.matches(LOCAL_PART_REGEX)) {
            return "The local-part (before '@') contains invalid characters. Only alphanumeric characters and "
                    + SPECIAL_CHARACTERS + " are allowed, and special characters cannot be consecutive.";
        }
        return null;
    }

    private static String checkDomain(String domain) {
        if (domain.isEmpty()) {
            return "Email is missing a domain after '@'."
                    + "It should be in the format local-part@domain (e.g. johndoe@example.com).";
        }
        if (!domain.contains(".")) {
            return "The domain (after '@') is missing a '.' (e.g. it should look like 'example.com').";
        }
        return checkDomainLabels(domain.split("\\."));
    }

    private static String checkDomainLabels(String[] labels) {
        if (labels[labels.length - 1].length() < 2) {
            return "The domain's last part (e.g. 'com' in 'example.com') must be at least 2 characters long.";
        }
        for (String label : labels) {
            String err = checkSingleDomainLabel(label);
            if (err != null) {
                return err;
            }
        }
        return null;
    }

    private static String checkSingleDomainLabel(String label) {
        if (label.isEmpty()) {
            return "The domain contains consecutive '.' separators, which is not allowed.";
        }
        if (!label.matches(DOMAIN_PART_REGEX)) {
            return "The domain label '" + label + "' is invalid. Each label must start and end with "
                    + "an alphanumeric character and may only contain hyphens in between.";
        }
        return null;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Email)) {
            return false;
        }

        Email otherEmail = (Email) other;
        return value.equals(otherEmail.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
