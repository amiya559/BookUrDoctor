package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doctorRegister_btn(View view) {
        Intent intent=new Intent(MainActivity.this,DoctorRegistrationForm.class);
        startActivity(intent);
    }

    public void loginnowbtn(View view) {
        Intent in = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(in);
    }

    public void signupbtn(View view) {
        Intent intent = new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
    }


    public void skipnow_btn(View view) {
        Intent intent=new Intent(MainActivity.this,NavigationDrawerActivity.class);
        startActivity(intent);
    }


}
