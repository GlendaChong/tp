package seedu.address.model.company;

import static java.util.Objects.requireNonNull;


/**
 * Represents a Company's application status  in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidApplicationStatus(String)}
 */
public class ApplicationStatus {

    public static final String MESSAGE_CONSTRAINTS =
            "Please enter a valid application status. Valid statuses are: "
                    + "PA (PENDING APPLICATION), PI (PENDING INTERVIEW), PO (PENDING OUTCOME), "
                    + "A (ACCEPTED), R (REJECTED)";

    /**
     * The application status of a company.
     */
    public enum ApplicationStatusEnum {
        PA("PA", "PENDING APPLICATION"),
        PI("PI", "PENDING INTERVIEW"),
        PO("PO", "PENDING OUTCOME"),
        A("A", "ACCEPTED"),
        R("R", "REJECTED");

        private final String code;
        private final String description;

        ApplicationStatusEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public String getDescription() {
            return description;
        }
    }

    public final ApplicationStatusEnum status;


    /**
     * Constructs a {@code ApplicationStatus}.
     * @param status
     */
    public ApplicationStatus(String status) throws IllegalArgumentException {
        requireNonNull(status);
        status = status.toUpperCase();
        switch (status) {
        case "PA":
            this.status = ApplicationStatusEnum.PA;
            break;
        case "PI":
            this.status = ApplicationStatusEnum.PI;
            break;
        case "PO":
            this.status = ApplicationStatusEnum.PO;
            break;
        case "A":
            this.status = ApplicationStatusEnum.A;
            break;
        case "R":
            this.status = ApplicationStatusEnum.R;
            break;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid application status.
     */
    public static boolean isValidApplicationStatus(String test) {
        test = test.toUpperCase();
        return test.equals("PA") || test.equals("PI") || test.equals("PO") || test.equals("A") || test.equals("R");
    }

    @Override
    public String toString() {
        return status.getCode();
    }

    public String getDescription() {
        return status.getDescription();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicationStatus)) {
            return false;
        }

        ApplicationStatus otherApplicationStatus = (ApplicationStatus) other;
        return status.equals(otherApplicationStatus.status);
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}
