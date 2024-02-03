package it.marcosoft.ticketwave.data.source.user;

import it.marcosoft.ticketwave.data.repository.user.UserResponseCallback;
import it.marcosoft.ticketwave.model.User;

/**
 * Base class to manage the user authentication.
 */
public abstract class BaseUserAuthenticationRemoteDataSource {
    protected UserResponseCallback userResponseCallback;

    public void setUserResponseCallback(UserResponseCallback userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }
    public abstract User getLoggedUser();
    public abstract void logout();
    public abstract void signUp(String email, String password, String name, String Surname, int age);
    public abstract void signIn(String email, String password);

}
