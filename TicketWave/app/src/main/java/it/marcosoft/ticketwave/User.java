package it.marcosoft.ticketwave;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class User {

    public String dateOfBirth;
    public String name;
    public String email;
    public String phone;



    public User(String email, String dateOfBirth, String name, String phone) {
        this.name=name;
        this.dateOfBirth=dateOfBirth;
        this.phone=phone;
        this.email=email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

