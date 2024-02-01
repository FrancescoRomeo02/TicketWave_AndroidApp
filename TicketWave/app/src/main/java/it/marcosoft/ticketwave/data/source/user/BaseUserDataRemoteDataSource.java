package it.marcosoft.ticketwave.data.source.user;

import java.util.Set;

import it.marcosoft.ticketwave.data.repository.user.UserResponseCallback;
import it.marcosoft.ticketwave.model.User;

/**
 * Base class to get the user data from a remote source.
 */
public abstract class BaseUserDataRemoteDataSource {
    protected UserResponseCallback userResponseCallback;

    public void setUserResponseCallback(UserResponseCallback userResponseCallback) {
        this.userResponseCallback = userResponseCallback;
    }

    public abstract void saveUserData(User user);

}
