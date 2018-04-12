package com.android.proyecto.reserveddisabled;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    TextView tv_name, tv_Email, tv_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tv_name = findViewById(R.id.nameUserTextView);
        tv_Email = findViewById(R.id.emailUserTextView);
        tv_Password = findViewById(R.id.passwordUserTextView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        tv_name.setText(name);
        tv_Email.setText(email);
        tv_Password.setText(password);


    }
}
