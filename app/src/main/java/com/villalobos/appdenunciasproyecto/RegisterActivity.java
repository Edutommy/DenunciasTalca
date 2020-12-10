package com.villalobos.appdenunciasproyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.villalobos.appdenunciasproyecto.model.Usuario;

public class RegisterActivity extends AppCompatActivity {

    EditText txt_email, txt_name, txt_phone, txt_pass;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_email = findViewById(R.id.register_email);
        txt_name = findViewById(R.id.register_name);
        txt_phone = findViewById(R.id.register_phone);
        txt_pass = findViewById(R.id.register_clave);

        auth = FirebaseAuth.getInstance();
    }

    public void createAccount(View view) {
        final String email, name, phone, pass;
        email = txt_email.getText().toString();
        name = txt_name.getText().toString();
        phone = txt_phone.getText().toString();
        pass = txt_pass.getText().toString();

        if (email.isEmpty() || name.isEmpty() || phone.isEmpty() || pass.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Complete la información", Toast.LENGTH_LONG).show();
        } else {
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("usuarios");
                                Usuario user = new Usuario();
                                user.setName(name);
                                user.setEmail(email);
                                user.setPhone(phone);
                                user.setUid(task.getResult().getUser().getUid());

                                myRef.push().setValue(user);

                                Toast.makeText(RegisterActivity.this, "Cuenta creada", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                String msg = task.getException().getMessage();
                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }

    public void regresar2(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();

    }
}