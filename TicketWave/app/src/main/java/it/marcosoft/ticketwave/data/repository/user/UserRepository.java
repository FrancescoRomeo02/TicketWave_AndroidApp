package it.marcosoft.ticketwave.data.repository.user;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Set;


import it.marcosoft.ticketwave.model.Result;
import it.marcosoft.ticketwave.model.User;

import it.marcosoft.ticketwave.data.source.user.BaseUserAuthenticationRemoteDataSource;
import it.marcosoft.ticketwave.data.source.user.BaseUserDataRemoteDataSource;


/**
 * Repository class to get the user information.
 */
public class UserRepository implements IUserRepository, UserResponseCallback {

    private static final String TAG = UserRepository.class.getSimpleName();

    private final BaseUserAuthenticationRemoteDataSource userRemoteDataSource;
    private final BaseUserDataRemoteDataSource userDataRemoteDataSource;

    private final MutableLiveData<Result> userMutableLiveData;
    public UserRepository(BaseUserAuthenticationRemoteDataSource userRemoteDataSource,
                          BaseUserDataRemoteDataSource userDataRemoteDataSource) {


        this.userRemoteDataSource = userRemoteDataSource;
        this.userMutableLiveData = new MutableLiveData<>();
        this.userRemoteDataSource.setUserResponseCallback(this);


        this.userDataRemoteDataSource = userDataRemoteDataSource;
        this.userDataRemoteDataSource.setUserResponseCallback(this);

    }

    public MutableLiveData<Result> getUser(String email, String password, boolean isUserRegistered) {
        if (isUserRegistered) {
            signIn(email, password);
        } else {
            String name="unknown";
            String surname="unknown";
            int age=0;
            signUp(email, password, name, surname, age);
        }
        return userMutableLiveData;
    }



    public MutableLiveData<Result> getUser(String email, String password, String name, String surname, int age, boolean isUserRegistered) {
        if (isUserRegistered) {
            signIn(email, password);
        } else {
            signUp(email, password, name, surname, age);
        }
        return userMutableLiveData;
    }

    @Override
    public User getLoggedUser() {
        return userRemoteDataSource.getLoggedUser();
    }

    @Override
    public MutableLiveData<Result> logout() {
        userRemoteDataSource.logout();
        return userMutableLiveData;
    }


    @Override
    public void signUp(String email, String password, String name, String surname, int age) {
        userRemoteDataSource.signUp(email, password, name, surname, age);
    }

    @Override
    public void signIn(String email, String password) {
        userRemoteDataSource.signIn(email, password);
    }



    @Override
    public void onSuccessFromAuthentication(User user) {

        if (user != null) {
            Log.d(TAG, "OnSuccessIsSuccess");
            userDataRemoteDataSource.saveUserData(user);
        }


    }

    @Override
    public void onFailureFromAuthentication(String message) {
        Result.Error result = new Result.Error(message);
        userMutableLiveData.postValue(result);
    }

    @Override
    public void onSuccessFromRemoteDatabase(User user) {
        Result.UserResponseSuccess result = new Result.UserResponseSuccess(user);
        userMutableLiveData.postValue(result);
    }

    @Override
    public void onFailureFromRemoteDatabase(String message) {
        Result.Error result = new Result.Error(message);
        userMutableLiveData.postValue(result);
    }

    //TODO
    @Override
    public void onSuccessFromPswChange(String message) {
        Result.Error result = new Result.Error(message);
    }

    //TODO
    @Override
    public void onFailureFromPswChange(String message) {
        Result.Error result = new Result.Error(message);
    }

    @Override
    public void resetPsw(String email) {
        userRemoteDataSource.ResetPassword(email);
    }
}
