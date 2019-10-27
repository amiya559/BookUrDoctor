package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorRegistrationForm extends AppCompatActivity {

    EditText txt_fullname,txt_email,txt_age,txt_specialization,txt_location,txt_password,txt_cnf_password;
    Button btn_register;
    RadioButton radioGenderMale,radioGenderFemale;
    String Gender="";
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration_form);

        txt_fullname=(EditText)findViewById(R.id.txtFullName);
        txt_email=(EditText)findViewById(R.id.txt_email);
        txt_age=(EditText)findViewById(R.id.txt_age);
        txt_specialization=(EditText)findViewById(R.id.txt_specialization);
        txt_location=(EditText)findViewById(R.id.txt_location);
        txt_password=(EditText)findViewById(R.id.password);
        txt_cnf_password=(EditText)findViewById(R.id.cnfpassword);
        btn_register=(Button)findViewById(R.id.doctor_registration);
        radioGenderMale=(RadioButton)findViewById(R.id.radio_male);
        radioGenderFemale=(RadioButton)findViewById(R.id.radio_female);

        progressBar = (ProgressBar) findViewById(R.id.doctorRegisterProgressbar);


        databaseReference=FirebaseDatabase.getInstance().getReference("Doctor");
        firebaseAuth= FirebaseAuth.getInstance();

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Hello");

                final String FullName = txt_fullname.getText().toString();
                final String Email = txt_email.getText().toString();
                final String Age = txt_age.getText().toString();
                final String Specialization = txt_specialization.getText().toString();
                final String Location = txt_location.getText().toString();
                String password = txt_password.getText().toString();
                String cnfPassword = txt_cnf_password.getText().toString();

                if (radioGenderMale.isChecked()) {

                    Gender = "Male";
                }

                if (radioGenderFemale.isChecked()) {

                    Gender = "Female";
                }

                progressBar.setVisibility(View.VISIBLE);

                if (password.equals(cnfPassword)) {

                    firebaseAuth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener(DoctorRegistrationForm.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {


                                        Doctor information = new Doctor(
                                                FullName,
                                                Email,
                                                Age,
                                                Specialization,
                                                Location,
                                                Gender
                                        );

                                        FirebaseDatabase.getInstance().getReference("Doctor")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(DoctorRegistrationForm.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), NavigationDrawerActivity.class));


                                            }
                                        });

                                        //startActivity(new Intent(getApplicationContext(),NavigationDrawerActivity.class));
                                        //Toast.makeText(DoctorRegistrationForm.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(DoctorRegistrationForm.this, "Authentication Failed or Check Your Internet Connection", Toast.LENGTH_SHORT).show();

                                    }


                                    // ...
                                }
                            });

                }

                else{
                    Toast.makeText(DoctorRegistrationForm.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
