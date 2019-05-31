package com.catifidemo.catifidemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String URLstring_post = "http://35.246.251.3/login";
    boolean respond;
    JSONObject tmp_obj;

    ArrayList<users> usersModelArrayList = new ArrayList<>();
    TextView postResponse;
    String email,password;
    EditText email_,password_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        respond=false;
        email_ =(EditText)findViewById(R.id.email_input_login);
        password_ =(EditText)findViewById(R.id.password_input_login);

        postResponse=findViewById(R.id.postResponse_signup);
    }

    private void requestJSON_post(final String email,final String password){
        Log.d("myapp","requestJSON_post");

        StringRequest postRequest = new StringRequest(Request.Method.POST, URLstring_post,
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
                                JSONArray dataArray = tmp_obj.getJSONArray("data");

                                for (int i = 0; i < dataArray.length(); i++) {
                                    users Users = new users();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    Users.setId(Integer.parseInt(dataobj.getString("id")));
                                    Users.setName(dataobj.getString("name"));
                                    Users.setEmail(dataobj.getString("email"));
                                    Users.setGender(dataobj.getString("password"));
                                    Users.setGender(dataobj.getString("gender"));
                                    Users.setGender(dataobj.getString("mobile"));
                                    Users.setGender(dataobj.getString("user_type"));
                                    Users.setGender(dataobj.getString("image"));
                                    Users.setGender(dataobj.getString("birthday"));
                                    Users.setGender(dataobj.getString("status"));
                                    Users.setGender(dataobj.getString("user_category"));
                                    Users.setGender(dataobj.getString("location_id"));
                                    usersModelArrayList.add(Users);
                                }
                                respond=true;
                                respond_post();
                            }else{
                                respond=false;
                                respond_post();
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
                        postResponse.setText(String.valueOf(error));
                        Log.d("myapp", "onErrorResponse: "+String.valueOf(error));
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(postRequest);
    }

//    private void requestJSON_get(){
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("myapp",">>"+response);
//
//                        try {
//                            //getting the whole json object from the response
//                            JSONObject obj = new JSONObject(response);
//                            if(obj.optString("status").equals("true")){
//
//                                ArrayList<users> usersModelArrayList = new ArrayList<>();
//                                JSONArray dataArray  = obj.getJSONArray("data");
//
//                                for (int i = 0; i < dataArray.length(); i++) {
//
//                                    users Users = new users();
//                                    JSONObject dataobj = dataArray.getJSONObject(i);
//
//                                    Users.setId(Integer.parseInt(dataobj.getString("id") ) );
//                                    Users.setName(dataobj.getString("name"));
//                                    Users.setEmail(dataobj.getString("email"));
//                                    Users.setGender(dataobj.getString("gender"));
//
//                                    usersModelArrayList.add(Users);
//
//                                }
//
//                                for (int j = 0; j < usersModelArrayList.size(); j++){
//                                    textView.setText(textView.getText()+ Integer.toString(usersModelArrayList.get(j).getId() )+ " "+ usersModelArrayList.get(j).getName()+ " "+ usersModelArrayList.get(j).getEmail()+ " "+usersModelArrayList.get(j).getGender()+" \n");
//                                }
//
//                            }else {
//                                Toast.makeText(MainActivity.this, obj.optString("message")+"", Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //displaying the error in toast if occurrs
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//        //creating a request queue
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        //adding the string request to request queue
//        requestQueue.add(stringRequest);
//
//    }
    ////////////////////////////////////////////////////

    protected void onLogin(View view){

        email = email_.getText().toString();
        password = password_.getText().toString();
//        Log.d("myapp","onLogin: email: " + email);
//        Log.d("myapp","onLogin: paasword: " + password);
        requestJSON_post(email,password);
    }


    protected void respond_post() {
        if (respond == true) {
            if (usersModelArrayList.size() == 1) {
                users u = usersModelArrayList.get(0);
                Log.d("myapp u", u.toString());

                Intent profileActivity = new Intent(this, profileActivity.class);
                profileActivity.putExtra("users", u);
                startActivity(profileActivity);
            } else {
                Log.d("myapp", "usersModelArrayList.size() == 1");
                postResponse.setText("Sorry something went wrong, please contact us via catwifi.com ");
            }
        }else{
            postResponse.setText("User not found please try again !");
        }
    }

    protected void onSignup(View view){
//        Log.d("myapp","onSignup");
        startActivity(new Intent(this, signupActivity.class));
    }
}
