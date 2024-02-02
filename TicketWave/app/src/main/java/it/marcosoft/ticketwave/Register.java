package it.marcosoft.ticketwave;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import it.marcosoft.ticketwave.activity.MainActivity;

public class Register extends AppCompatActivity {

    TextInputEditText editTextEmail,editTextPassword,editTextUsername,editTextPhone;
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
        editTextPassword = findViewById(R.id.password);
        editTextUsername = findViewById(R.id.name);
        editTextPhone = findViewById(R.id.phone);

        mAuth = FirebaseAuth.getInstance();


        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressionBar);
        textView = findViewById(R.id.loginNow);

        textView.setOnClickListener(v -> {
           Intent intent = new Intent(getApplicationContext(),Login.class);
           startActivity(intent);
           finish();
        });



        buttonReg.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            String email, password;
            email=String.valueOf(editTextEmail.getText());
            password=String.valueOf(editTextPassword.getText());

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

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Account created.",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });



        });
    }
}