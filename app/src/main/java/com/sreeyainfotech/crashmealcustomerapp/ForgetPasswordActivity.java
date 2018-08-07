package com.sreeyainfotech.crashmealcustomerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button submit_button;
    private EditText otp_edtxt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.forgetpassword);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_cancel);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewes();
    }

    private void findViewes() {
        otp_edtxt=(EditText)findViewById(R.id.otp_edtxt);
        submit_button=(Button)findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.submit_button:
                Intent changePw=new Intent(ForgetPasswordActivity.this,ChangePasswordActivity.class);
                startActivity(changePw);

                break;
        }
    }
}
