package com.example.responsivedesing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.activity_main__btn__open);

        btnLogin.setOnClickListener(v -> {
            Intent i  = new Intent(MainActivity.this, NotesActivity.class);
            startActivity(i);
        });
    }
}