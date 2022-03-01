package com.swe4550.dropin;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    private String userName;
    private String userEmail;
    private String userPassword;

    public User() {

    }

    public User( String userName, String userEmail, String userPassword) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
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

    static boolean addNewUser(String name, String email, String password) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference();

        if(!name.isEmpty() || !email.isEmpty() || !password.isEmpty()) {
            User user = new User(name, email, password);

            mDatabase.child("Users").child(name).setValue(user);
            return true;
        }
        else
            return false;
    }
}
