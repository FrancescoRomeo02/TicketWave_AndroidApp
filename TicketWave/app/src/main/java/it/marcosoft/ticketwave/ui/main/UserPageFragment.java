package it.marcosoft.ticketwave.ui.main;

import static it.marcosoft.ticketwave.util.Constants.FIREBASE_REALTIME_DATABASE;
import static it.marcosoft.ticketwave.util.Constants.FIREBASE_USERS_COLLECTION;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import it.marcosoft.ticketwave.R;
import it.marcosoft.ticketwave.data.repository.user.IUserRepository;
import it.marcosoft.ticketwave.model.User;
import it.marcosoft.ticketwave.ui.login.UserViewModel;
import it.marcosoft.ticketwave.ui.login.UserViewModelFactory;
import it.marcosoft.ticketwave.util.ServiceLocator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserPageFragment extends Fragment {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutSurname;
    private TextInputLayout textInputLayoutAge;

    private UserViewModel userViewModel;
    private User user;

    public UserPageFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UserPageFragment newInstance() {
        return new UserPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IUserRepository userRepository = ServiceLocator.getInstance().
                getUserRepository(requireActivity().getApplication());
        userViewModel = new ViewModelProvider(
                requireActivity(),
                new UserViewModelFactory(userRepository)).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_page, container, false);


        Button logoutBtn = rootView.findViewById(R.id.Logout);

        logoutBtn.setOnClickListener(v -> {
            userViewModel.logout();
            getActivity().getViewModelStore().clear();
            ((MainActivity)getActivity()).goToLogin();
        });


        String token=userViewModel.getLoggedUser().getIdToken();


        final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(FIREBASE_REALTIME_DATABASE);
        DatabaseReference ref = firebaseDatabase.getReference().getRef();

        ref.child(FIREBASE_USERS_COLLECTION).child(token).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                user = snapshot.getValue(User.class);
                textInputLayoutEmail = rootView.findViewById(R.id.emailInputEditText);
                textInputLayoutEmail.setHint(user.getEmail());

                textInputLayoutName = rootView.findViewById(R.id.nameInputEditText);
                textInputLayoutName.setHint(user.getName());

                textInputLayoutSurname = rootView.findViewById(R.id.surnameInputEditText);
                textInputLayoutSurname.setHint(user.getSurname());


                String ageString = String.valueOf(user.getAge());


                textInputLayoutAge = rootView.findViewById(R.id.ageInputEditText);
                textInputLayoutAge.setHint(ageString);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("User Page Fragment","error");
            }

        });




        // Inflate the layout for this fragment
        return rootView;
      }



}