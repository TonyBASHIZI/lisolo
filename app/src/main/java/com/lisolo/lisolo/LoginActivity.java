package com.lisolo.lisolo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText etEmail, etPasswd;
    Button btnLogin;
    ProgressBar pb;
    TextView linkRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPasswd = findViewById(R.id.passwd);
        btnLogin = findViewById(R.id.register);
        pb = findViewById(R.id.pb);
        linkRegister = findViewById(R.id.linkRegister);

        pb.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                doLogin();
            }
        });
        linkRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                //finish();
            }
        });

    }

    private void doLogin() {

        pb.setVisibility(View.VISIBLE);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String email = etEmail.getText().toString();
        String passwd = etPasswd.getText().toString();

        mAuth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            String errMessage = task.getException().getMessage();
                            Toast.makeText(LoginActivity.this,
                                    errMessage, Toast.LENGTH_LONG).show();
                        }
                        pb.setVisibility(View.GONE);
                    }
                }).addOnFailureListener(new OnFailureListener() {

                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(LoginActivity.this,
                                                        e.getMessage(), Toast.LENGTH_LONG).show();

                                                pb.setVisibility(View.GONE);
                                            }

                                        }
        );

    }
}
