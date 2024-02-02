package it.marcosoft.ticketwave.ui.login;

import static it.marcosoft.ticketwave.util.Constants.EMAIL_ADDRESS;
import static it.marcosoft.ticketwave.util.Constants.ENCRYPTED_DATA_FILE_NAME;
import static it.marcosoft.ticketwave.util.Constants.ENCRYPTED_SHARED_PREFERENCES_FILE_NAME;
import static it.marcosoft.ticketwave.util.Constants.PASSWORD;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.commons.validator.routines.EmailValidator;

import java.io.IOException;
import java.security.GeneralSecurityException;

import it.marcosoft.ticketwave.MainActivity;
import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.databinding.FragmentRegistrationBinding;
import it.marcosoft.ticketwave.model.Result;
import it.marcosoft.ticketwave.model.User;
import it.marcosoft.ticketwave.util.DataEncryptionUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationFragment extends Fragment {

    private static final String TAG = RegistrationFragment.class.getSimpleName();
    private static final boolean USE_NAVIGATION_COMPONENT = true;
    //serve per usare nav o intent, in base se e' u frag. o un activity
    private FragmentRegistrationBinding binding;
    private UserViewModel userViewModel;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutName;

    private DataEncryptionUtil dataEncryptionUtil;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.setAuthenticationError(false);
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menu.clear();
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == android.R.id.home) {
                    Navigation.findNavController(requireView()).navigateUp();
                }
                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);




        binding.RegisterButton.setOnClickListener(v -> {

            String email = binding.InputEmail.getText().toString().trim();
            String password = binding.InputPassword.getText().toString().trim();
            String name = binding.InputName.getText().toString().trim();

            if (isEmailOk(email) & isPasswordOk(password)) {

                if (!userViewModel.isAuthenticationError()) {
                    userViewModel.getUserMutableLiveData(email, password, name, false).observe(
                            getViewLifecycleOwner(), result -> {

                                if (result.isSuccess()) {
                                    User user = ((Result.UserResponseSuccess) result).getData();

                                    userViewModel.setAuthenticationError(false);
                                    Navigation.findNavController(view).navigate(
                                            R.id.action_registrationFragment_to_mainActivity);
                                } else {
                                    userViewModel.setAuthenticationError(true);
                                    Snackbar.make(requireActivity().findViewById(android.R.id.content),
                                            "getErrorMessage(((Result.Error) result).getMessage())",
                                            Snackbar.LENGTH_SHORT).show();
                                }

                            });
                } else {
                    userViewModel.getUser(email, password, false);
                }

            } else {
                userViewModel.setAuthenticationError(true);
                Snackbar.make(requireActivity().findViewById(android.R.id.content),
                        "R.string.check_login_data_message", Snackbar.LENGTH_SHORT).show();
            }
        });

        binding.toLoginButtonFrame.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_registrationFragment_to_loginFragment);
        });
    }








    /**
     * It starts another Activity using Intent or NavigationComponent.
     * @param destinationActivity The class of Activity to start.
     * @param destination The ID associated with the action defined in welcome_nav_graph.xml.
     */
    private void startActivityBasedOnCondition(Class<?> destinationActivity, int destination) {
        if (USE_NAVIGATION_COMPONENT) {
            Navigation.findNavController(requireView()).navigate(destination);
        } else {
            Intent intent = new Intent(requireContext(), destinationActivity);
            startActivity(intent);
        }
        requireActivity().finish();
    }

    /**
     * Checks if the email address has a correct format.
     * @param email The email address to be validated
     * @return true if the email address is valid, false otherwise
     */
    private boolean isEmailOk(String email) {
        // Check if the email is valid through the use of this library:
        // https://commons.apache.org/proper/commons-validator/

        //qua fa crashare?
        if (!EmailValidator.getInstance().isValid((email))) {

            binding.emailInputEditText.setError(getString(R.string.error_email));
            return false;
        } else {
            binding.emailInputEditText.setError(null);
            return true;
        }
    }

    /**
     * Checks if the password is not empty.
     * @param password The password to be checked
     * @return True if the password is not empty, false otherwise
     */
    private boolean isPasswordOk(String password) {
        // Check if the password length is correct
        if (password.isEmpty() || password.length()<8) {
            binding.passwordInputEditText.setError(getString(R.string.error_password));
            return false;
        } else {
            binding.passwordInputEditText.setError(null);
            return true;
        }
    }

    /**
     * Encrypts login data using DataEncryptionUtil class.
     * @param email The email address to be encrypted and saved
     * @param password The password to be encrypted and saved
     */
    private void saveLoginData(String email, String password) {
        try {
            dataEncryptionUtil.writeSecretDataWithEncryptedSharedPreferences(
                    ENCRYPTED_SHARED_PREFERENCES_FILE_NAME, EMAIL_ADDRESS, email);
            dataEncryptionUtil.writeSecretDataWithEncryptedSharedPreferences(
                    ENCRYPTED_SHARED_PREFERENCES_FILE_NAME, PASSWORD, password);

            dataEncryptionUtil.writeSecreteDataOnFile(ENCRYPTED_DATA_FILE_NAME,
                    email.concat(":").concat(password));
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

}

