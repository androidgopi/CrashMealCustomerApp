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
import android.widget.TextView;

import com.sreeyainfotech.crashmealcustomerapp.customeclass.CustomEditText;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {

    private Button save_button;
    private TextView main_toolbar_title;
    CustomEditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.changepassword);

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
        save_button=(Button)findViewById(R.id.save_button);
        save_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.save_button:
                Intent i=new Intent(ChangePasswordActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }

}
