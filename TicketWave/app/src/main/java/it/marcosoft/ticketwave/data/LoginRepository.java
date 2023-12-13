package it.marcosoft.ticketwave.data;

import java.io.IOException;

import it.marcosoft.ticketwave.data.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private final LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Get the singleton instance of LoginRepository.
     *
     * @param dataSource The data source for authentication.
     * @return The singleton instance of LoginRepository.
     */
    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            synchronized (LoginRepository.class) {
                if (instance == null) {
                    instance = new LoginRepository(dataSource);
                }
            }
        }
        return instance;
    }

    /**
     * Check if a user is currently logged in.
     *
     * @return True if a user is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return user != null;
    }

    /**
     * Logout the current user.
     */
    public void logout() {
        user = null;
        dataSource.logout();
    }

    /**
     * Set the logged-in user and update the local storage if necessary.
     *
     * @param user The logged-in user.
     */
    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    /**
     * Attempt to log in with the provided username and password.
     *
     * @param username The username.
     * @param password The password.
     * @return The result of the login attempt.
     */
    public Result login(String username, String password) throws IOException {
        // handle login
        Result result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getValue());
        }
        return result;
    }
}
