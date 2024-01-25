package it.marcosoft.ticketwave.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    // User ID of the logged in user
    private final String userId;

    // Display name of the logged in user
    private final String displayName;

    /**
     * Constructor for LoggedInUser
     *
     * @param userId      The user ID of the logged in user
     * @param displayName The display name of the logged in user
     */
    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    /**
     * Get the user ID of the logged in user
     *
     * @return The user ID of the logged in user
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Get the display name of the logged in user
     *
     * @return The display name of the logged in user
     */
    public String getDisplayName() {
        return displayName;
    }
}