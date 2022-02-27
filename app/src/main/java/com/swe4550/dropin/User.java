package com.swe4550.dropin;

public class User {

    String userID;
    String userName;
    String userEmail;
    String userPassword;

    public User(){

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
}


/*public void addNewUser(String name, String email, String password) {
    DatabaseReference mDatabase;
    mDatabase = FirebaseDatabase.getInstance().getReference("Users");

    String ID = mDatabase.push().getKey();

    User user = new User(ID, name, email, password);

    mDatabase.child(ID).setValue(user);
};*/