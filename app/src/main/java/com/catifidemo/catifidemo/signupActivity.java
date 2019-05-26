package com.catifidemo.catifidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class signupActivity extends AppCompatActivity {
    String email,password,name,gender;
    EditText email_,password_,name_;
    Spinner gender_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email_      =   (EditText)findViewById(R.id.email_input_signup);
        password_   =   (EditText)findViewById(R.id.password_input_signup);
        name_       =   (EditText)findViewById(R.id.name_input_signup);
        gender_     =   (Spinner)findViewById(R.id.gender_spinner);
    }

    protected void onSignup(View view){
        Log.d("myapp","onSignup activity");

        email = email_.getText().toString();
        password = password_.getText().toString();
        name = name_.getText().toString();
        gender = gender_.getSelectedItem().toString();

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

}
