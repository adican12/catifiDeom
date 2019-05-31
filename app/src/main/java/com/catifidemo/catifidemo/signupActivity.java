package com.catifidemo.catifidemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class signupActivity extends AppCompatActivity {
    String email,password,name,gender,birthday,phone,category,image;
    EditText email_,password_,name_,phone_,category_;
    TextView postResponse_signup;
    Spinner gender_;
    CalendarView birthdate_;
    String URLstring_post_sign_up="http://35.246.251.3/signup";
    JSONObject tmp_obj;
    boolean respond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        respond     =   false;
        name_       =   (EditText)findViewById(R.id.name_input_signup);
        email_      =   (EditText)findViewById(R.id.email_input_signup);
        password_   =   (EditText)findViewById(R.id.password_input_signup);

        birthdate_  =   (CalendarView)findViewById(R.id.birthdate_input_signup);

        gender_     =   (Spinner) findViewById(R.id.gender_spinner);
        phone_      =   (EditText)findViewById(R.id.phone_input_signup);
        category_   =   (EditText)findViewById(R.id.category_input_signup);
        postResponse_signup=   (TextView)findViewById(R.id.postResponse_signup);

        birthdate_.setOnDateChangeListener( new CalendarView.OnDateChangeListener(){
                @Override
                // In this Listener have one method
                // and in this method we will
                // get the value of DAYS, MONTH, YEARS
                public void onSelectedDayChange(@NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {
                                // Store the value of date with
                                // format in String type Variable
                                // Add 1 in month because month
                                // index is start with 0
                                birthday= year+"-"+ (month + 1) + "-" + dayOfMonth ;
                            }
                        });
    }

    protected void onSignup(View view){
        Log.d("myapp","onSignup activity");

        name        = name_.getText().toString();
        email       = email_.getText().toString();
        password    = password_.getText().toString();
        gender      = gender_.getSelectedItem().toString();
        phone       = phone_.getText().toString();
        category    = category_.getText().toString();
        image       = "image_here";
        requestJSON_post(name,email,password,birthday,gender,phone,category,image);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }


    private void requestJSON_post(final String name,final String email,final String password,final String birthday, final String gender,final String phone,final String category,final String image){
        Log.d("myapp","requestJSON_post");

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLstring_post_sign_up,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("myapp","onResponse: "+response);
                        try {
                            //getting the whole json object from the response
                            tmp_obj= new JSONObject(response);
                            if (tmp_obj.optString("status").equals("true")) {
                                respond=true;
                                respond_check();
                            }else{
                                respond=false;
                                respond_check();
                            }

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        postResponse_signup.setText(String.valueOf(error));
                        Log.d("myapp", "onErrorResponse: "+String.valueOf(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("birthday", birthday);
                params.put("gender", gender);
                params.put("phone", phone);
                params.put("category", category);
                params.put("image", image);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }

    private void respond_check(){
        if(respond==true){
            startActivity(new Intent(this, MainActivity.class));
        }else {
            postResponse_signup.setText("Sorry something went wrong! Please try again ");
            Log.d("myapp","respond = false");
        }
    }
}
