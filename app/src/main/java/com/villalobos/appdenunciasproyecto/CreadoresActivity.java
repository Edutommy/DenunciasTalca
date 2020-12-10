package com.villalobos.appdenunciasproyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreadoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creadores2);
    }

    public void regresar(View view) {
        Intent intent = new Intent(CreadoresActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}