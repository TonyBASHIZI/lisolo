package com.lisolo.lisolo;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText etEmail, etPasswd, etName;
    Button btnRegister;

    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.email);
        etName = findViewById(R.id.nom);
        etPasswd = findViewById(R.id.passwd);
        btnRegister = findViewById(R.id.register);
        pb = findViewById(R.id.pb);

        pb.setVisibility(View.GONE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pb.setVisibility(View.VISIBLE);

                doRegister();
            }
        });
    }

    private void doRegister() {


        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String email = etEmail.getText().toString();
        String passwd = etPasswd.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(RegisterActivity.this,
                                    "Enregisterment ressi", Toast.LENGTH_SHORT).show();


                        }
                        pb.setVisibility(View.GONE);
                    }


                });


    }


}
