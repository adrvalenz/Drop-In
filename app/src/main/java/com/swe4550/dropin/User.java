package com.swe4550.dropin;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    private String userName;
    private String pfp;
    private String biography;
    private String interest1;
    private String interest2;
    private String interest3;
    private String interest4;
    private String game1;
    private String game2;
    private String game3;
    private String game4;


    //empty constructor
    public User() {
    }

    public User( String userName, String PFP, String Biography,
                 String interest1, String interest2, String interest3, String interest4,
                 String game1, String game2, String game3, String game4) {
        this.userName = userName;
        this.pfp = pfp;
        this.biography = biography;
        this.interest1 = interest1;
        this.interest2 = interest2;
        this.interest3 = interest3;
        this.interest4 = interest4;
        this.game1 = game1;
        this.game2 = game2;
        this.game3 = game3;
        this.game4 = game4;
    }

    //getter functions
    public String getUserName() {
        return userName;
    }
    public String getpfp() { return pfp; }
    public String getBiography() { return biography; }
    public String getInterest1() { return interest1; }
    public String getInterest2() { return interest2; }
    public String getInterest3() { return interest3; }
    public String getInterest4() { return interest4; }
    public String getGame1() { return game1; }
    public String getGame2() { return game2; }
    public String getGame3() { return game3; }
    public String getGame4() { return game4; }

    //setter functions
    public void setUserName(String userName) { this.userName = userName; }
    public void setPFP(String pfp) { this.pfp = pfp; }
    public void setBiography(String biography) { this.biography = biography; }
    public void setInterest1(String interest1) { this.interest1 = interest1; }
    public void setInterest2(String interest2) { this.interest2 = interest2; }
    public void setInterest3(String interest3) { this.interest3 = interest3; }
    public void setInterest4(String interest4) { this.interest4 = interest4; }
    public void setGame1(String game1) { this.game1 = game1; }
    public void setGame2(String game2) { this.game2 = game2; }
    public void setGame3(String game3) { this.game3 = game3; }
    public void setGame4(String game4) { this.game4 = game4; }
}
