package az.xecore.appluni.utils;

public enum Status {
    PENDING("Pending", "Application is under review"),
    SUBMITTED("Submitted", "Application has been submitted"),
    ACCEPTED("Accepted", "Application has been accepted"),
    REJECTED("Rejected", "Application has been rejected");

    private final String displayName;
    private final String description;

    Status(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Checks if transition to new status is valid
     */
    public boolean canTransitionTo(Status newStatus) {
        return switch (this) {
            case PENDING -> newStatus == SUBMITTED || newStatus == REJECTED;
            case SUBMITTED -> newStatus == ACCEPTED || newStatus == REJECTED;
            case ACCEPTED -> false; // Once accepted, no further changes
            case REJECTED -> false; // Once rejected, no further changes
        };
    }

    /**
     * Returns the next possible statuses
     */
    public Status[] getPossibleNextStatuses() {
        return switch (this) {
            case PENDING -> new Status[]{SUBMITTED, REJECTED};
            case SUBMITTED -> new Status[]{ACCEPTED, REJECTED};
            case ACCEPTED, REJECTED -> new Status[]{};
        };
    }

    /**
     * Checks if the status represents a final state
     */
    public boolean isFinalState() {
        return this == ACCEPTED || this == REJECTED;
    }
}