package it.marcosoft.ticketwave.util;

import android.app.Application;

import java.io.IOException;
import java.security.GeneralSecurityException;

import it.marcosoft.ticketwave.data.repository.user.IUserRepository;
import it.marcosoft.ticketwave.data.repository.user.UserRepository;
import it.marcosoft.ticketwave.data.source.user.BaseUserAuthenticationRemoteDataSource;
import it.marcosoft.ticketwave.data.source.user.UserAuthenticationRemoteDataSource;
import it.marcosoft.ticketwave.data.source.user.BaseUserDataRemoteDataSource;
import it.marcosoft.ticketwave.data.source.user.UserDataRemoteDataSource;

public class ServiceLocator {

    private static volatile ServiceLocator INSTANCE = null;

    private ServiceLocator() {}

    /**
     * Returns an instance of ServiceLocator class.
     * @return An instance of ServiceLocator.
     */
    public static ServiceLocator getInstance() {
        if (INSTANCE == null) {
            synchronized(ServiceLocator.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ServiceLocator();
                }
            }
        }
        return INSTANCE;
    }



    public IUserRepository getUserRepository(Application application) {
        SharedPreferencesUtil sharedPreferencesUtil = new SharedPreferencesUtil(application);

        BaseUserAuthenticationRemoteDataSource userRemoteAuthenticationDataSource =
                new UserAuthenticationRemoteDataSource();
        /*
        BaseUserDataRemoteDataSource userDataRemoteDataSource =
                new UserDataRemoteDataSource(sharedPreferencesUtil);
        */

        return new UserRepository(userRemoteAuthenticationDataSource);
    }
}
