package it.marcosoft.ticketwave.data;

import it.marcosoft.ticketwave.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    /**
     * Attempt to log in with the provided username and password.
     *
     * @param username The username.
     * @param password The password.
     * @return The result of the login attempt.
     */
    public Result login(String username, String password) throws IOException {
        try {
            // TODO: handle loggedInUser authentication
            // For demonstration purposes, a fake user is created.
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            // An unexpected exception occurred during the login process.
            return new Result.Error(new IOException("Unexpected error logging in", e));
        }
    }

    /**
     * Logout the user.
     * TODO: Implement the logic to revoke authentication if needed.
     */
    public void logout() {
        // TODO: revoke authentication
    }
}
