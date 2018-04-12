package com.android.proyecto.reserveddisabled;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView register;
    EditText et_Name, et_Email, et_Password;
    Button btt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register= findViewById(R.id.singInLoginTextViewButton);
        et_Name = findViewById(R.id.nameRegisterEditText);
        et_Email = findViewById(R.id.emailRegisterEditText);
        et_Password = findViewById(R.id.passwordRegisterEditText);

        btt_login = findViewById(R.id.startLoginButton);

        register = findViewById(R.id.singInLoginTextViewButton);

        register.setOnClickListener (new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intentReg = new Intent(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intentReg);
            }
        });

        btt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = et_Email.getText().toString();
                final String password = et_Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success) {
                                String name = jsonResponse.getString("name");
                                String email = jsonResponse.getString("email");
                                String password = jsonResponse.getString("password");

                                Intent intent = new Intent(MainActivity.this, UserActivity.class);
                                intent.putExtra("name",name);
                                intent.putExtra("email",email);
                                intent.putExtra("password",password);

                                MainActivity.this.startActivity(intent);

                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Error al iniciar sesión")
                                        .setNegativeButton("Vuelve a intentarlo",null)
                                        .create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(email, password, responseListener);

                    RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                    queue.add(loginRequest);
            }
        });

    }
}

