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

public class LogIn extends AppCompatActivity {

    //bonding variables
    EditText userEmail;
    EditText userPassword;
    Button loginBtn;
    Button signupBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //Check if a user is logged in, send to discover activity if there is one on startup.
//        if(FirebaseAuth.getInstance().getCurrentUser() != null)
//        {
//            FirebaseAuth.getInstance().signOut();
//        }
        FirebaseAuth.getInstance().signOut();
        if(FirebaseAuth.getInstance().getCurrentUser() != null)
        {
            startActivity(new Intent(LogIn.this, Discover.class));
        }
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        loginBtn = findViewById(R.id.login_btn);
        signupBtn = findViewById(R.id.signup_btn);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userEmail.getText().toString().trim().length() == 0 || userPassword.getText().toString().trim().length() < 6){
                    Toast.makeText(LogIn.this, "Email and Password must be filled and Password longer than six characters", Toast.LENGTH_LONG).show();
                }
                else{
                    userLogIn(userEmail.getText().toString().trim(), userPassword.getText().toString().trim());
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if statement checks if the email field has been filled
                if (userEmail.getText().toString().trim().length() > 0){
                    /*if it does have valid amount input it will gather it all and
                    put it in the text field of the sign up page*/
                    Intent a_intent = new Intent(LogIn.this, SignUp.class);
                    String  USER_EMAIL  = userEmail.getText().toString().trim();
                    a_intent.putExtra( "USER EMAIL", USER_EMAIL);
                    startActivity(a_intent);
                    // the code above will send the user to the sign up screen with the email already filled
                }
                else{
                    startActivity(new Intent(LogIn.this, SignUp.class));
                    //send user to the sign up screen with out any prefilled email field
                }
            }
        });


    }







    public void userLogIn(String email, String password){
        //initialize variables
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        //Sign in and check if task is completed correctly or not
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    //send to discover activity
                    startActivity(new Intent(LogIn.this, Discover.class));
                }else{
                    //error message
                    Toast.makeText(LogIn.this, "Incorrect Email or Password. Make sure credentials are correct.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
// 3/14/22 stuff

// public void onClick(view v) {
/* switch (v.getId()){
  case R.id.register:
    startActivity(new Layout(getTheme()));
          }
     }
 }
 */