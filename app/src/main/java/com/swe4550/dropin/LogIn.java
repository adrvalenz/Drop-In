package com.swe4550.dropin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }



    boolean userLogIn(String email, String password){
        //initialize variables
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final boolean[] TaskSuccessful = new boolean[1];

        //Sign in and check if task is completed correctly or not
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    TaskSuccessful[0] = true;
                }else{
                    TaskSuccessful[0] = false;
                }
            }
        });

        //return succesful status
        return TaskSuccessful[0];
    }
}

//This is an edit
//Thisi another edit