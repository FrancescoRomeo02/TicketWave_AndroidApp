package it.marcosoft.ticketwave.data.source.user;


import android.util.Log;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import it.marcosoft.ticketwave.data.source.user.BaseUserAuthenticationRemoteDataSource;
import it.marcosoft.ticketwave.model.User;

/**
 * Class that manages the user authentication using Firebase Authentication.
 */
public class UserAuthenticationRemoteDataSource extends BaseUserAuthenticationRemoteDataSource {

    private static final String TAG = it.marcosoft.ticketwave.data.source.user.UserAuthenticationRemoteDataSource.class.getSimpleName();

    private final FirebaseAuth firebaseAuth;

    public UserAuthenticationRemoteDataSource() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public User getLoggedUser() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser == null) {
            return null;
        } else {
            return new User(firebaseUser.getDisplayName(), firebaseUser.getEmail(), firebaseUser.getUid());
        }
    }

    @Override
    public void logout() {
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    firebaseAuth.removeAuthStateListener(this);
                    Log.d(TAG, "User logged out");
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
        firebaseAuth.signOut();
    }

    @Override
    public void signUp(String email, String password, String name, String surname, int age) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name).build();

                firebaseUser.updateProfile(profileUpdates).addOnCompleteListener(task2 -> {
                    if (task.isSuccessful()){
                        Log.d(TAG, "name inserted");
                    }
                });


                if (firebaseUser != null) {

                    userResponseCallback.onSuccessFromAuthentication(
                            new User(name, email, surname, age, firebaseUser.getUid())
                );
                } else {
                    userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
                }
            } else {
                userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {

                    userResponseCallback.onSuccessFromAuthentication(
                            new User(firebaseUser.getDisplayName(), email, firebaseUser.getUid())
                    );

                } else {
                    userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
                }
            } else {
                userResponseCallback.onFailureFromAuthentication(getErrorMessage(task.getException()));
            }
        });
    }

    @Override
    public void ResetPassword(String email) {

        firebaseAuth.sendPasswordResetEmail(email)/*.addOnSuccessListener(task -> {
            Log.d("S","DebugMSGxPsw, ricorda di rimuovere");
            userResponseCallback.onSuccessFromPswChange("Email for password change sent");

        }).addOnFailureListener(task ->{
            Log.d("S","DebugMSGxPsw, ricorda di rimuovere FAIL");
          userResponseCallback.onFailureFromAuthentication("Email for password change failed");
        })*/;


    }


    private String getErrorMessage(Exception exception) {
        if (exception instanceof FirebaseAuthWeakPasswordException) {
            return "WEAK_PASSWORD_ERROR";
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            return "INVALID_CREDENTIALS_ERROR";
        } else if (exception instanceof FirebaseAuthInvalidUserException) {
            return "INVALID_USER_ERROR";
        } else if (exception instanceof FirebaseAuthUserCollisionException) {
            return "USER_COLLISION_ERROR";
        }
        return "UNEXPECTED_ERROR";
    }
}
