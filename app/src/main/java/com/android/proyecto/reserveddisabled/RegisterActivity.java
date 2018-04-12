package com.android.proyecto.reserveddisabled;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_Name, et_Email, et_Password;
    Button btt_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_Name = findViewById(R.id.nameRegisterEditText);
        et_Email = findViewById(R.id.emailRegisterEditText);
        et_Password = findViewById(R.id.passwordRegisterEditText);

        btt_Register = findViewById(R.id.registerRegisterButton);

        btt_Register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        final String name=et_Name.getText().toString();
        final String email=et_Email.getText().toString();
        final String password=et_Password.getText().toString();

        Response.Listener<String> respoListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObjectResponse = new JSONObject(response);
                    boolean success= jsonObjectResponse.getBoolean("success");

                    if(success) {
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        RegisterActivity.this.startActivity(intent);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                        builder.setMessage("Error al registrarte")
                                .setNegativeButton("Vuelve a intentarlo",null)
                                .create().show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

    RegisterRequest registerRequest= new RegisterRequest(name, email, password, respoListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(registerRequest);

    }
}
