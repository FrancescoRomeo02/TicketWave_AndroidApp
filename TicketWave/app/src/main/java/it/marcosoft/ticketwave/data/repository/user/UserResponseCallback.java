package it.marcosoft.ticketwave.data.repository.user;

import java.util.List;

import it.marcosoft.ticketwave.model.User;

public interface UserResponseCallback {
    void onSuccessFromAuthentication(User user);
    void onFailureFromAuthentication(String message);
    void onSuccessFromRemoteDatabase(User user);
    void onFailureFromRemoteDatabase(String message);

}
