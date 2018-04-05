package com.android.proyecto.reserveddisabled;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register= findViewById(R.id.registerRegisterButton);

        register.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentReg = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intentReg);
            }
        });
    }
}

