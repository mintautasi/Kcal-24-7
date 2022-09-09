package com.example.kursinis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {

    ImageView rLogo;
    EditText rEmail,rPass,rPass_valid;
    Button rReg_btn,rLog_btn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rLogo = findViewById(R.id.logo);
        rEmail = findViewById(R.id.email);
        rPass = findViewById(R.id.pass);
        rPass_valid = findViewById(R.id.pass_valid);
        rReg_btn = findViewById(R.id.reg_btn);
        rLog_btn = findViewById(R.id.log_request);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);


        if (fAuth.getCurrentUser() != null ) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        rReg_btn.setOnClickListener(view -> {
            String email = rEmail.getText().toString().trim();
            String pass = rPass.getText().toString().trim();
            String pass_valid = rPass_valid.getText().toString().trim();

            if (TextUtils.isEmpty(email)){
                rEmail.setError("Reikalingas el. paštas!");
                return;
            }

            if (TextUtils.isEmpty(pass)){
                rPass.setError("Reikalingas slaptažodis!");
                return;
            }

            if (TextUtils.isEmpty(pass_valid)){
                rPass_valid.setError("Reikalingas slaptažodžio pakartojimas!");
                return;
            }

            if (TextUtils.equals(pass,pass_valid)){

            }else {
                rPass_valid.setError("Slaptažodžiai nesutampa!");
                return;
            }

            if(pass.length() < 6){
            rPass.setError("Slaptažodis turi susidėti iš nemažiau kaip 6 simbolių!");
            return;
            }

            progressBar.setVisibility(View.VISIBLE);

            //register user

            fAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(registration.this, "Paskyra sėkmingai sukūrta", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));


                    }else{
                        Toast.makeText(registration.this, "Įvyko klaida!" + task.getException(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        });
        rLog_btn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),login.class)));
    }
}