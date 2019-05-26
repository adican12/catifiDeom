package com.catifidemo.catifidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    String email,password;
    EditText email_,password_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email_ =(EditText)findViewById(R.id.email_input_login);
        password_ =(EditText)findViewById(R.id.password_input_login);

    }

    protected void onLogin(View view){
        Log.d("myapp","onLogin");

        email = email_.getText().toString();
        password = password_.getText().toString();

        Log.d("myapp","onLogin: email: " + email);
        Log.d("myapp","onLogin: paasword: " + password);

        startActivity(new Intent(this, profileActivity.class));

    }

    protected void onSignup(View view){
        Log.d("myapp","onSignup");

        startActivity(new Intent(this, signupActivity.class));

    }
}
