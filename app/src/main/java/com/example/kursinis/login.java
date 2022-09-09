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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    ImageView rLogo;
    EditText rEmail,rPass;
    Button rReg_request,rLog_btn;
    ProgressBar progressBar2;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rLogo = findViewById(R.id.logo);
        rEmail = findViewById(R.id.email);
        rPass = findViewById(R.id.pass);
        rReg_request = findViewById(R.id.reg_request);
        rLog_btn = findViewById(R.id.log_btn);

        progressBar2 = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();

        rLog_btn.setOnClickListener(view -> {

            String email = rEmail.getText().toString().trim();
            String pass = rPass.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                rEmail.setError("Reikalingas el. paštas!");
                return;
            }

            if (TextUtils.isEmpty(pass)) {
                rPass.setError("Reikalingas slaptažodis!");
                return;
            }


            if (pass.length() < 6) {
                rPass.setError("Slaptažodis turi susidėti iš nemažiau kaip 6 simbolių!");
                return;
            }

            progressBar2.setVisibility(View.VISIBLE);

            //vartotojo autentifikavimas


            fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {

                if (task.isSuccessful()) {
                    Toast.makeText(login.this, "Prisijungta sėkmingai prie paskyros!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } else {
                    Toast.makeText(login.this, "Įvyko klaida!" + task.getException(), Toast.LENGTH_SHORT).show();

                }
            });
        });
        rReg_request.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(),registration.class)));
    }
}




