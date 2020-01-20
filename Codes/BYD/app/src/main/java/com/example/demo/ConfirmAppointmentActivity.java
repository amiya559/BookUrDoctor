package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmAppointmentActivity extends AppCompatActivity {

    private EditText nameEditText,genderEditText, phoneEditText, addressEditText, cityEditText;
    private Button confirmBkngBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appointment);

        confirmBkngBtn = (Button) findViewById(R.id.confirm_bkng_btn);
        nameEditText = (EditText) findViewById(R.id.patient_name);
        genderEditText = (EditText) findViewById(R.id.patient_gender);
        phoneEditText = (EditText) findViewById(R.id.patient_phone_number);
        addressEditText = (EditText) findViewById(R.id.patient_address);
        cityEditText = (EditText) findViewById(R.id.patient_city);


        confirmBkngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });



    }

    private void Check()
    {
        if (TextUtils.isEmpty(nameEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your full name.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(genderEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your gender.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phoneEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your phone number.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(addressEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your address.", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(cityEditText.getText().toString()))
        {
            Toast.makeText(this, "Please provide your city name.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            ConfirmBooking();
        }
    }



    private void ConfirmBooking()
    {

        final String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());


        //Creating Another Child in database
        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference()
                .child("Bookings")
                .child(Prevalent.currentOnlineUser.getPhone());

        HashMap<String, Object> ordersMap = new HashMap<>();

        ordersMap.put("name", nameEditText.getText().toString());
        ordersMap.put("gender", genderEditText.getText().toString());
        ordersMap.put("phone", phoneEditText.getText().toString());
        ordersMap.put("address", addressEditText.getText().toString());
        ordersMap.put("city", cityEditText.getText().toString());
        ordersMap.put("date", saveCurrentDate);
        ordersMap.put("time", saveCurrentTime);

        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {

                if (task.isSuccessful())
                {
                    Toast.makeText(ConfirmAppointmentActivity.this, "your appointment has been booked successfully.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ConfirmAppointmentActivity.this, NavigationDrawerActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
