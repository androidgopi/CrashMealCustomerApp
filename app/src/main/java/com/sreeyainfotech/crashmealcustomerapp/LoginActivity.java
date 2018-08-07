package com.sreeyainfotech.crashmealcustomerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button forgetpassword_button, signup_button, login_button;
    private LinearLayout facebook_layout, google_plus_layout, twitter_layout, instagram_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewes();
    }

    private void findViewes() {

        forgetpassword_button = (Button) findViewById(R.id.forgetpassword_button);
        forgetpassword_button.setOnClickListener(this);
        signup_button = (Button) findViewById(R.id.signup_button);
        signup_button.setOnClickListener(this);
        login_button = (Button) findViewById(R.id.login_button);
        login_button.setOnClickListener(this);

        facebook_layout = (LinearLayout) findViewById(R.id.facebook_layout);
        facebook_layout.setOnClickListener(this);
        google_plus_layout = (LinearLayout) findViewById(R.id.google_plus_layout);
        google_plus_layout.setOnClickListener(this);
        twitter_layout = (LinearLayout) findViewById(R.id.twitter_layout);
        twitter_layout.setOnClickListener(this);
        instagram_layout = (LinearLayout) findViewById(R.id.instagram_layout);
        instagram_layout.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.forgetpassword_button:
                Intent forgetPwdActivity=new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(forgetPwdActivity);
                break;

            case R.id.signup_button:
                Intent signupActivity=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(signupActivity);
                break;

            case R.id.login_button:
                break;

            case R.id.facebook_layout:
                break;

            case R.id.google_plus_layout:
                break;

            case R.id.twitter_layout:
                break;

            case R.id.instagram_layout:
                break;

        }


    }
}
