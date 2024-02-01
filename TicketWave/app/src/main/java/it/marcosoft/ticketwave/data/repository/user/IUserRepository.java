package it.marcosoft.ticketwave.data.repository.user;

import androidx.lifecycle.MutableLiveData;

import java.util.Set;

import it.marcosoft.ticketwave.model.Result;
import it.marcosoft.ticketwave.model.User;

public interface IUserRepository {
    MutableLiveData<Result> getUser(String email, String password, boolean isUserRegistered);
    MutableLiveData<Result> getGoogleUser(String idToken);
    MutableLiveData<Result> logout();
    User getLoggedUser();
    void signUp(String email, String password);
    void signIn(String email, String password);
    void signInWithGoogle(String token);

}
