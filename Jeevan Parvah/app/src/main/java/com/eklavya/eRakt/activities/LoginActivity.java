package com.eklavya.eRakt.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.eklavya.eRakt.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button signin, signup, resetpass;
    private EditText inputemail, inputpassword;
    private FirebaseAuth mAuth;
    private CustomPbar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pbar= new CustomPbar(LoginActivity.this);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null)
        {
            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
            startActivity(intent);
            finish();
        }


        inputemail = findViewById(R.id.input_username);
        inputpassword = findViewById(R.id.input_password);

        signin = findViewById(R.id.button_login);
        signup = findViewById(R.id.button_register);
        resetpass = findViewById(R.id.button_forgot_password);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputemail.getText().toString()+"";
                final String password = inputpassword.getText().toString()+"";

                try {
                    if(password.length()>0 && email.length()>0) {
                        pbar.showPbar();
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_LONG).show();
                                            pbar.hidePbar();
//                                            Log.v("error", task.getException().getMessage());
                                        } else {
                                            pbar.hidePbar();
                                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                            startActivity(intent);
                                            finish();
                                        }

                                    }
                                });
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Please fill all the field.", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RestorePassword.class);
                startActivity(intent);
            }
        });


    }

}
