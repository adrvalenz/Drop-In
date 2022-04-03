package com.swe4550.dropin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SetUpProfile extends AppCompatActivity {

    //bonding variables
    User user_info;
    ImageView userPfp;
    TextView userName;
    EditText bio;
    Button pfp_one;
    Button pfp_two;
    Button pfp_three;
    Button pfp_four;
    Button game_one;
    Button game_two;
    Button game_three;
    Button game_four;
    Button game_five;
    Button game_six;
    Button game_seven;
    Button game_eight;
    Button interest_one;
    Button interest_two;
    Button interest_three;
    Button interest_four;
    Button interest_five;
    Button interest_six;
    Button interest_seven;
    Button interest_eight;
    Button submitBtn;
    Button cancelBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_profile);
        //set the variables to their UI counter parts
        userPfp = findViewById(R.id.user_pfp);
        userName = findViewById(R.id.user_name);
        bio = findViewById(R.id.biography);
        pfp_one = findViewById(R.id.pfp_1);
        pfp_two = findViewById(R.id.pfp_2);
        pfp_three = findViewById(R.id.pfp_3);
        pfp_four = findViewById(R.id.pfp_4);
        game_one = findViewById(R.id.game_1);
        game_two = findViewById(R.id.game_2);
        game_three = findViewById(R.id.game_3);
        game_four = findViewById(R.id.game_4);
        game_five = findViewById(R.id.game_5);
        game_six = findViewById(R.id.game_6);
        game_seven = findViewById(R.id.game_7);
        game_eight = findViewById(R.id.game_8);
        interest_one = findViewById(R.id.interest_1);
        interest_two = findViewById(R.id.interest_2);
        interest_three = findViewById(R.id.interest_3);
        interest_four = findViewById(R.id.interest_4);
        interest_five = findViewById(R.id.interest_5);
        interest_six = findViewById(R.id.interest_6);
        interest_seven = findViewById(R.id.interest_7);
        interest_eight = findViewById(R.id.interest_8);
        submitBtn = findViewById(R.id.submit_btn);
        cancelBtn = findViewById(R.id.cancel_btn);

        //call the get current user data function
        getCurrentUserData();
        /*a if-else-if function that will check if the current user has a
        preselected profile picture and if it does it will display the picture and
        if not itt will display a toast to tell them to choose one.
         */
        if (user_info.getPfp().equals(" ")) {
            Toast.makeText(SetUpProfile.this, "Please choose your profile picture.", Toast.LENGTH_LONG).show();
        } else if (user_info.getPfp().equals("Xbox")) {
            userPfp.setImageResource(R.drawable.xbox_icon_logo);
        } else if (user_info.getPfp().equals("Computer")) {
            userPfp.setImageResource(R.drawable.personal_computer_icon_logo);
        } else if (user_info.getPfp().equals("Nintendo")) {
            userPfp.setImageResource(R.drawable.nintendo_switch_icon_logo);
        } else if (user_info.getPfp().equals("Playstation")) {
            userPfp.setImageResource(R.drawable.playstation_icon_logo);
        }

        //pre filling the username text box with the username in the users database
        userName.setText(user_info.getUserName());

        // this should display text in the bio text box.
        if (!user_info.getBiography().equals(" ")) {
            bio.setText(user_info.getBiography());
        }

        //creating the on click listeners for the actual profile picture buttons
        pfp_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_info.setPfp("Xbox");
                userPfp.setImageResource(R.drawable.xbox_icon_logo);
            }
        });

        pfp_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_info.setPfp("Computer");
                userPfp.setImageResource(R.drawable.personal_computer_icon_logo);
            }
        });

        pfp_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_info.setPfp("Nintendo");
                userPfp.setImageResource(R.drawable.nintendo_switch_icon_logo);
            }
        });

        pfp_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_info.setPfp("Playstation");
                userPfp.setImageResource(R.drawable.playstation_icon_logo);
            }
        });

        //creating the set on click listeners for the game buttons 1-8.
        game_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Elden Ring")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Elden Ring has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Elden Ring")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Elden Ring has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Elden Ring")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Elden Ring has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Elden Ring")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Elden Ring has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Elden Ring") && !user_info.getGame3().equals("Elden Ring") && !user_info.getGame4().equals("Elden Ring")) {
                        user_info.setGame1("Elden Ring");
                        Toast.makeText(SetUpProfile.this, "Elden Ring has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Elden Ring") && !user_info.getGame3().equals("Elden Ring") && !user_info.getGame4().equals("Elden Ring")) {
                        user_info.setGame2("Elden Ring");
                        Toast.makeText(SetUpProfile.this, "Elden Ring has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Elden Ring") & !user_info.getGame2().equals("Elden Ring") && !user_info.getGame4().equals("Elden Ring")) {
                        user_info.setGame3("Elden Ring");
                        Toast.makeText(SetUpProfile.this, "Elden Ring has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Elden Ring") && !user_info.getGame2().equals("Elden Ring") && !user_info.getGame3().equals("Elden Ring")) {
                        user_info.setGame4("Elden Ring");
                        Toast.makeText(SetUpProfile.this, "Elden Ring has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });

        game_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Fortnite")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Fortnite has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Fortnite")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Fortnite has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Fortnite")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Fortnite has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Fortnite")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Fortnite has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Fortnite") && !user_info.getGame3().equals("Fortnite") && !user_info.getGame4().equals("Fortnite")) {
                        user_info.setGame1("Fortnite");
                        Toast.makeText(SetUpProfile.this, "Fortnite has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Fortnite") && !user_info.getGame3().equals("Fortnite") && !user_info.getGame4().equals("Fortnite")) {
                        user_info.setGame2("Fortnite");
                        Toast.makeText(SetUpProfile.this, "Fortnite has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Fortnite") & !user_info.getGame2().equals("Fortnite") && !user_info.getGame4().equals("Fortnite")) {
                        user_info.setGame3("Fortnite");
                        Toast.makeText(SetUpProfile.this, "Fortnite has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Fortnite") && !user_info.getGame2().equals("Fortnite") && !user_info.getGame3().equals("Fortnite")) {
                        user_info.setGame4("Fortnite");
                        Toast.makeText(SetUpProfile.this, "Fortnite has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });







        // creating the cancel button which will send user to discovery page
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SetUpProfile.this, Discover.class));
            }
        });

        /* this is the creation of the submit button which will check if the
        all the correct parameters have been filled and then save all the info
        */
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_info.getPfp().equals(" ")){
                    Toast.makeText(SetUpProfile.this, "You must Select a Profile Picture.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ") && user_info.getGame2().equals(" ") && user_info.getGame3().equals(" ") && user_info.getGame4().equals(" ")){
                    Toast.makeText(SetUpProfile.this, "You must Select at least one game.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ") && user_info.getInterest2().equals(" ") && user_info.getInterest3().equals(" ") && user_info.getInterest4().equals(" ")){
                    Toast.makeText(SetUpProfile.this, "You must Select at least one interest.", Toast.LENGTH_LONG).show();
                }else{
                    user_info.setBiography(bio.getText().toString().trim());
                    editCurrentUser(user_info);
                }
            }
        });

    }

    public void getCurrentUserData(){

    }

    public void editCurrentUser(User user_lowercase){

    }
}
