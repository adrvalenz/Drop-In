package com.swe4550.dropin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {


    //bonding variables
    EditText userNewName;
    EditText userNewEmail;
    EditText userNewPassword;
    EditText userNewPassword_confirm;
    Button signupNewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    userNewName = findViewById(R.id.user_new_name);
    userNewEmail = findViewById(R.id.user_new_email);
    userNewPassword = findViewById(R.id.user_new_password);
    userNewPassword_confirm = findViewById(R.id.user_new_password_confirm);
    signupNewBtn = findViewById(R.id.signup_new_btn);

    // start OnCreate() to check if the email has been passed in from the login Activity using .hasExtra (green is key)
        //If it was, then set the text in the email EditText View
    if(getIntent().hasExtra( "USER EMAIL")){
        userNewEmail.setText(getIntent().getStringExtra("USER EMAIL"));
    }
    // end


    // Main SignUp Code Bulk: Confirm that that the text has been entered into all the textViews
        // Email, Username, Password, and confirmPassword textViews entered, otherwise display error toast then kick out of handler
    signupNewBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(userNewEmail.getText().toString().length() == 0 ||
                    userNewName.getText().toString().length() > 15 || userNewName.getText().toString().length() < 3 ||
                    userNewPassword.getText().toString().length() < 6 ||
                    userNewPassword_confirm.getText().toString().length() < 6){
                Toast.makeText(SignUp.this, "Fill all fields. Username must be 3 to 15 characters and Password more than 6",
                        Toast.LENGTH_LONG).show();

            }
            // Confirm password and confirmPassword match correctly, otherwise display error toast
            else {
                if (userNewPassword.getText().toString().equals(userNewPassword_confirm.getText().toString())) {
                    if (addNewUser(userNewEmail.getText().toString(), userNewPassword.getText().toString(), userNewName.getText().toString())) {
                        startActivity(new Intent(SignUp.this, SetUpProfile.class));
                    } else {
                        Toast.makeText(SignUp.this, "Error Registering User",
                                Toast.LENGTH_LONG).show();
                    }



                } else {
                    Toast.makeText(SignUp.this, "There has been an error: the passwords do not match",
                            Toast.LENGTH_LONG).show();
                }
            }


            }



        });
    }   //end of code contribution
















    // Add new user to Firebase Database and Auth
    static void addNewUser(String name, String email, String password) {
        DatabaseReference mDatabase;
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    User user = new User(name, email, password);

                    mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                //Sign up is successful, redirect to Set Up Profile.

                                //Code to send to Set Up Activity
                            } else {
                                //If sign up fails on the database side then delete user from FirebaseAuth.
                                FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //Display Error Message

                                    }
                                });

                            }
                        }
                    });
                } else {
                    //If sign up fails on the database side display this message to user.

                    //Display Error Message
                }
            }
        });
    }
}