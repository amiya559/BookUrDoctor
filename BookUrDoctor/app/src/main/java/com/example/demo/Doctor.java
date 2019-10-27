package com.example.demo;

public class Doctor {
    public String FullName,Email,Age,Specialization,Location,Gender;

    public Doctor(){

    }

    public Doctor(String fullName, String email, String age, String specialization, String location, String gender) {
        FullName = fullName;
        Email = email;
        Age = age;
        Specialization = specialization;
        Location = location;
        Gender = gender;
    }
}
