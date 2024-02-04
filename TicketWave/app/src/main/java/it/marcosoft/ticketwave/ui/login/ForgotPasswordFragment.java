package it.marcosoft.ticketwave.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import org.apache.commons.validator.routines.EmailValidator;

import it.marcosoft.ticketwave.databinding.FragmentForgotPasswordBinding;
import it.marcosoft.ticketwave.databinding.FragmentRegistrationBinding;
import it.marcosoft.ticketwave.model.Result;
import it.marcosoft.ticketwave.ui.login.UserViewModel;
import it.marcosoft.ticketwave.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForgotPasswordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForgotPasswordFragment extends Fragment {

    private TextInputLayout textInputLayoutEmail;
    private UserViewModel userViewModel;

    private FragmentForgotPasswordBinding binding;

    public ForgotPasswordFragment() {
        // Required empty public constructor
    }


    public static ForgotPasswordFragment newInstance() {
        return new ForgotPasswordFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        userViewModel.setAuthenticationError(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        binding= FragmentForgotPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);



        binding.resetEmailButtonFrame.setOnClickListener(v -> {
        String email = binding.InputEmail.getText().toString().trim();


        if (isEmailOk(email)) {

                userViewModel.sendPasswordMail(email);

                                Snackbar.make(requireActivity().findViewById(android.R.id.content),
                                        "Email Sent",
                                        Snackbar.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(
                                        R.id.action_forgotPasswordFragment_to_loginFragment);
            }
            else {
                userViewModel.setAuthenticationError(true);
                Snackbar.make(requireActivity().findViewById(android.R.id.content),
                        R.string.check_login_data_message, Snackbar.LENGTH_SHORT).show();
            }

        });

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


}