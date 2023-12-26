package it.marcosoft.ticketwave;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail,editTextPassword,editTextName,editTextPhone,editTextBirth;
    Button buttonReg;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.email);
        editTextName = findViewById(R.id.name);
        editTextPassword = findViewById(R.id.password);
        editTextPhone = findViewById(R.id.phone);
        editTextBirth = findViewById(R.id.birth);

        mAuth = FirebaseAuth.getInstance();


        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressionBar);
        textView = findViewById(R.id.loginNow);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),Login.class);
               startActivity(intent);
               finish();
            }
        });



        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email, password, datebirth, name, phone;
                email=String.valueOf(editTextEmail.getText());
                password=String.valueOf(editTextPassword.getText());
                datebirth = String.valueOf(editTextBirth.getText());
                name =String.valueOf(editTextName.getText());
                phone = String.valueOf(editTextPhone.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Register.this,"enter email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this,"enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6){
                    Toast.makeText(Register.this,"password too short, enter password again",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(email.indexOf('@') == -1){
                    Toast.makeText(Register.this,"email is not an email address",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(datebirth.indexOf('/') != 2 && datebirth.lastIndexOf('/') != 5 ){
                    Toast.makeText(Register.this,"date of birth needs to be in the xx/xx/xxxx form",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(datebirth.length()>10){
                    Toast.makeText(Register.this,"date of birth has too many characters",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(phone.length()>10){
                    Toast.makeText(Register.this,"not a phone number",Toast.LENGTH_SHORT).show();
                    return;
                }



                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {

                                    InsertData(email,name,datebirth,phone);
                                    Toast.makeText(Register.this, "Account created.",
                                            Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



                FirebaseAuth auth = FirebaseAuth.getInstance();
                FirebaseUser Fuser = auth.getCurrentUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build();
                Fuser.updateProfile(profileUpdates);

                if(Fuser != null){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

            

        });



    }



    public void InsertData(String email, String name, String datebirth, String phone) {

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        DatabaseReference usersRef = ref.child("Users");
        User user = new User(email, datebirth, name, phone);

        usersRef.push().setValue(user);
        /*
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference keyRef = rootRef.push();
        String key = keyRef.getKey();
        keyRef.setValue(user);
        */


    }



}