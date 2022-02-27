package com.swe4550.dropin;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    String userID;
    String userName;
    String userEmail;
    String userPassword;

    public User() {

    }

    public User(String userID, String userName, String userEmail, String userPassword) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    static void addNewUser(String name, String email, String password) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        String ID = mDatabase.push().getKey();

        User user = new User(ID, name, email, password);

        mDatabase.child("Users").child(ID).setValue(user);
    }
}
