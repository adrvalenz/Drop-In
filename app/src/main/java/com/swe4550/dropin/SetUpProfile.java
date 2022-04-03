package com.swe4550.dropin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        game_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Apex Legends")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Apex Legends has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Apex Legends")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Apex Legends has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Apex Legends")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Apex Legends has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Apex Legends")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Apex Legends has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Apex Legends") && !user_info.getGame3().equals("Apex Legends") && !user_info.getGame4().equals("Apex Legends")) {
                        user_info.setGame1("Apex Legends");
                        Toast.makeText(SetUpProfile.this, "Apex Legends has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Apex Legends") && !user_info.getGame3().equals("Apex Legends") && !user_info.getGame4().equals("Apex Legends")) {
                        user_info.setGame2("Apex Legends");
                        Toast.makeText(SetUpProfile.this, "Apex Legends has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Apex Legends") & !user_info.getGame2().equals("Apex Legends") && !user_info.getGame4().equals("Apex Legends")) {
                        user_info.setGame3("Apex Legends");
                        Toast.makeText(SetUpProfile.this, "Apex Legends has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Apex Legends") && !user_info.getGame2().equals("Apex Legends") && !user_info.getGame3().equals("Apex Legends")) {
                        user_info.setGame4("Apex Legends");
                        Toast.makeText(SetUpProfile.this, "Apex Legends has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });


        game_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Call of Duty Warzone")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Call of Duty Warzone has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Call of Duty Warzone")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Call of Duty Warzone has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Call of Duty Warzone")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Call of Duty Warzone has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Call of Duty Warzone")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Call of Duty Warzone has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Call of Duty Warzone") && !user_info.getGame3().equals("Call of Duty Warzone") && !user_info.getGame4().equals("Call of Duty Warzone")) {
                        user_info.setGame1("Call of Duty Warzone");
                        Toast.makeText(SetUpProfile.this, "Call of Duty Warzone has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Call of Duty Warzone") && !user_info.getGame3().equals("Call of Duty Warzone") && !user_info.getGame4().equals("Call of Duty Warzone")) {
                        user_info.setGame2("Call of Duty Warzone");
                        Toast.makeText(SetUpProfile.this, "Call of Duty Warzone has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Call of Duty Warzone") & !user_info.getGame2().equals("Call of Duty Warzone") && !user_info.getGame4().equals("Call of Duty Warzone")) {
                        user_info.setGame3("Call of Duty Warzone");
                        Toast.makeText(SetUpProfile.this, "Call of Duty Warzone has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Call of Duty Warzone") && !user_info.getGame2().equals("Call of Duty Warzone") && !user_info.getGame3().equals("Call of Duty Warzone")) {
                        user_info.setGame4("Call of Duty Warzone");
                        Toast.makeText(SetUpProfile.this, "Call of Duty Warzone has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });

        game_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Rocket League")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Rocket League has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Rocket League")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Rocket League has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Rocket League")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Rocket League has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Rocket League")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Rocket League has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Rocket League") && !user_info.getGame3().equals("Rocket League") && !user_info.getGame4().equals("Rocket League")) {
                        user_info.setGame1("Rocket League");
                        Toast.makeText(SetUpProfile.this, "Rocket League has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Rocket League") && !user_info.getGame3().equals("Rocket League") && !user_info.getGame4().equals("Rocket League")) {
                        user_info.setGame2("Rocket League");
                        Toast.makeText(SetUpProfile.this, "Rocket League has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Rocket League") & !user_info.getGame2().equals("Rocket League") && !user_info.getGame4().equals("Rocket League")) {
                        user_info.setGame3("Rocket League");
                        Toast.makeText(SetUpProfile.this, "Rocket League has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Rocket League") && !user_info.getGame2().equals("Rocket League") && !user_info.getGame3().equals("Rocket League")) {
                        user_info.setGame4("Rocket League");
                        Toast.makeText(SetUpProfile.this, "Rocket League has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });


        game_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Minecraft")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Minecraft has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Minecraft")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Minecraft has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Minecraft")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Minecraft has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Minecraft")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Minecraft has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Minecraft") && !user_info.getGame3().equals("Minecraft") && !user_info.getGame4().equals("Minecraft")) {
                        user_info.setGame1("Minecraft");
                        Toast.makeText(SetUpProfile.this, "Minecraft has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Minecraft") && !user_info.getGame3().equals("Minecraft") && !user_info.getGame4().equals("Minecraft")) {
                        user_info.setGame2("Minecraft");
                        Toast.makeText(SetUpProfile.this, "Minecraft has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Minecraft") & !user_info.getGame2().equals("Minecraft") && !user_info.getGame4().equals("Minecraft")) {
                        user_info.setGame3("Minecraft");
                        Toast.makeText(SetUpProfile.this, "Minecraft has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Minecraft") && !user_info.getGame2().equals("Minecraft") && !user_info.getGame3().equals("Minecraft")) {
                        user_info.setGame4("Minecraft");
                        Toast.makeText(SetUpProfile.this, "Minecraft has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });

        game_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Grand Theft Auto Online")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Grand Theft Auto Online has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Grand Theft Auto Online")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Grand Theft Auto Online has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Grand Theft Auto Online")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Grand Theft Auto Online has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Grand Theft Auto Online")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Grand Theft Auto Online has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Grand Theft Auto Online") && !user_info.getGame3().equals("Grand Theft Auto Online") && !user_info.getGame4().equals("Grand Theft Auto Online")) {
                        user_info.setGame1("Grand Theft Auto Online");
                        Toast.makeText(SetUpProfile.this, "Grand Theft Auto Online has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Grand Theft Auto Online") && !user_info.getGame3().equals("Grand Theft Auto Online") && !user_info.getGame4().equals("Grand Theft Auto Online")) {
                        user_info.setGame2("Grand Theft Auto Online");
                        Toast.makeText(SetUpProfile.this, "Grand Theft Auto Online has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Grand Theft Auto Online") && !user_info.getGame2().equals("Grand Theft Auto Online") && !user_info.getGame4().equals("Grand Theft Auto Online")) {
                        user_info.setGame3("Grand Theft Auto Online");
                        Toast.makeText(SetUpProfile.this, "Grand Theft Auto Online has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Grand Theft Auto Online") && !user_info.getGame2().equals("Grand Theft Auto Online") && !user_info.getGame3().equals("Grand Theft Auto Online")) {
                        user_info.setGame4("Grand Theft Auto Online");
                        Toast.makeText(SetUpProfile.this, "Grand Theft Auto Online has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });

        game_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the game slots 1-4 to see if the said game is already in it and if it is it'll remove it.
                if (user_info.getGame1().equals("Fallout 76")) {
                    user_info.setGame1(" ");
                    Toast.makeText(SetUpProfile.this,"Fallout 76 has been removed from game One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame2().equals("Fallout 76")){
                    user_info.setGame2(" ");
                    Toast.makeText(SetUpProfile.this,"Fallout 76 has been removed from game two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame3().equals("Fallout 76")){
                    user_info.setGame3(" ");
                    Toast.makeText(SetUpProfile.this,"Fallout 76 has been removed from game three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame4().equals("Fallout 76")){
                    user_info.setGame4(" ");
                    Toast.makeText(SetUpProfile.this,"Fallout 76 has been removed from game four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getGame1().equals(" ")){
                    if (!user_info.getGame2().equals("Fallout 76") && !user_info.getGame3().equals("Fallout 76") && !user_info.getGame4().equals("Fallout 76")) {
                        user_info.setGame1("Fallout 76");
                        Toast.makeText(SetUpProfile.this, "Fallout 76 has been added to game one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame2().equals(" ")){
                    if (!user_info.getGame1().equals("Fallout 76") && !user_info.getGame3().equals("Fallout 76") && !user_info.getGame4().equals("Fallout 76")) {
                        user_info.setGame2("Fallout 76");
                        Toast.makeText(SetUpProfile.this, "Fallout 76 has been added to game two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame3().equals(" ")) {
                    if (!user_info.getGame1().equals("Fallout 76") && !user_info.getGame2().equals("Fallout 76") && !user_info.getGame4().equals("Fallout 76")) {
                        user_info.setGame3("Fallout 76");
                        Toast.makeText(SetUpProfile.this, "Fallout 76 has been added to game three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getGame4().equals(" ")){
                    if (!user_info.getGame1().equals("Fallout 76") && !user_info.getGame2().equals("Fallout 76") && !user_info.getGame3().equals("Fallout 76")) {
                        user_info.setGame4("Fallout 76");
                        Toast.makeText(SetUpProfile.this, "Fallout 76 has been added to game four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another game to add this game.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Sports")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Sports has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Sports")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Sports has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Sports")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Sports has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Sports")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Sports has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Sports") && !user_info.getInterest3().equals("Sports") && !user_info.getInterest4().equals("Sports")) {
                        user_info.setInterest1("Sports");
                        Toast.makeText(SetUpProfile.this, "Sports has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Sports") && !user_info.getInterest3().equals("Sports") && !user_info.getInterest4().equals("Sports")) {
                        user_info.setInterest2("Sports");
                        Toast.makeText(SetUpProfile.this, "Sports has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Sports") && !user_info.getInterest2().equals("Sports") && !user_info.getInterest4().equals("Sports")) {
                        user_info.setInterest3("Sports");
                        Toast.makeText(SetUpProfile.this, "Sports has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Sports") && !user_info.getInterest2().equals("Sports") && !user_info.getInterest3().equals("Sports")) {
                        user_info.setInterest4("Sports");
                        Toast.makeText(SetUpProfile.this, "Sports has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Art")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Art has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Art")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Art has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Art")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Art has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Art")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Art has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Art") && !user_info.getInterest3().equals("Art") && !user_info.getInterest4().equals("Art")) {
                        user_info.setInterest1("Art");
                        Toast.makeText(SetUpProfile.this, "Art has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Art") && !user_info.getInterest3().equals("Art") && !user_info.getInterest4().equals("Art")) {
                        user_info.setInterest2("Art");
                        Toast.makeText(SetUpProfile.this, "Art has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Art") && !user_info.getInterest2().equals("Art") && !user_info.getInterest4().equals("Art")) {
                        user_info.setInterest3("Art");
                        Toast.makeText(SetUpProfile.this, "Art has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Art") && !user_info.getInterest2().equals("Art") && !user_info.getInterest3().equals("Art")) {
                        user_info.setInterest4("Art");
                        Toast.makeText(SetUpProfile.this, "Art has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Cars")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Cars has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Cars")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Cars has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Cars")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Cars has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Cars")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Cars has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Cars") && !user_info.getInterest3().equals("Cars") && !user_info.getInterest4().equals("Cars")) {
                        user_info.setInterest1("Cars");
                        Toast.makeText(SetUpProfile.this, "Cars has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Cars") && !user_info.getInterest3().equals("Cars") && !user_info.getInterest4().equals("Cars")) {
                        user_info.setInterest2("Cars");
                        Toast.makeText(SetUpProfile.this, "Cars has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Cars") && !user_info.getInterest2().equals("Cars") && !user_info.getInterest4().equals("Cars")) {
                        user_info.setInterest3("Cars");
                        Toast.makeText(SetUpProfile.this, "Cars has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Cars") && !user_info.getInterest2().equals("Cars") && !user_info.getInterest3().equals("Cars")) {
                        user_info.setInterest4("Cars");
                        Toast.makeText(SetUpProfile.this, "Cars has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Music")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Music has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Music")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Music has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Music")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Music has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Music")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Music has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Music") && !user_info.getInterest3().equals("Music") && !user_info.getInterest4().equals("Music")) {
                        user_info.setInterest1("Music");
                        Toast.makeText(SetUpProfile.this, "Music has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Music") && !user_info.getInterest3().equals("Music") && !user_info.getInterest4().equals("Music")) {
                        user_info.setInterest2("Music");
                        Toast.makeText(SetUpProfile.this, "Music has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Music") && !user_info.getInterest2().equals("Music") && !user_info.getInterest4().equals("Music")) {
                        user_info.setInterest3("Music");
                        Toast.makeText(SetUpProfile.this, "Music has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Music") && !user_info.getInterest2().equals("Music") && !user_info.getInterest3().equals("Music")) {
                        user_info.setInterest4("Music");
                        Toast.makeText(SetUpProfile.this, "Music has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Cooking")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Cooking has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Cooking")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Cooking has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Cooking")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Cooking has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Cooking")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Cooking has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Cooking") && !user_info.getInterest3().equals("Cooking") && !user_info.getInterest4().equals("Cooking")) {
                        user_info.setInterest1("Cooking");
                        Toast.makeText(SetUpProfile.this, "Cooking has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Cooking") && !user_info.getInterest3().equals("Cooking") && !user_info.getInterest4().equals("Cooking")) {
                        user_info.setInterest2("Cooking");
                        Toast.makeText(SetUpProfile.this, "Cooking has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Cooking") && !user_info.getInterest2().equals("Cooking") && !user_info.getInterest4().equals("Cooking")) {
                        user_info.setInterest3("Cooking");
                        Toast.makeText(SetUpProfile.this, "Cooking has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Cooking") && !user_info.getInterest2().equals("Cooking") && !user_info.getInterest3().equals("Cooking")) {
                        user_info.setInterest4("Cooking");
                        Toast.makeText(SetUpProfile.this, "Cooking has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Anime")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Anime has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Anime")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Anime has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Anime")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Anime has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Anime")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Anime has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Anime") && !user_info.getInterest3().equals("Anime") && !user_info.getInterest4().equals("Anime")) {
                        user_info.setInterest1("Anime");
                        Toast.makeText(SetUpProfile.this, "Anime has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Anime") && !user_info.getInterest3().equals("Anime") && !user_info.getInterest4().equals("Anime")) {
                        user_info.setInterest2("Anime");
                        Toast.makeText(SetUpProfile.this, "Anime has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Anime") && !user_info.getInterest2().equals("Anime") && !user_info.getInterest4().equals("Anime")) {
                        user_info.setInterest3("Anime");
                        Toast.makeText(SetUpProfile.this, "Anime has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Anime") && !user_info.getInterest2().equals("Anime") && !user_info.getInterest3().equals("Anime")) {
                        user_info.setInterest4("Anime");
                        Toast.makeText(SetUpProfile.this, "Anime has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Reading")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Reading has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Reading")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Reading has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Reading")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Reading has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Reading")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Reading has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Reading") && !user_info.getInterest3().equals("Reading") && !user_info.getInterest4().equals("Reading")) {
                        user_info.setInterest1("Reading");
                        Toast.makeText(SetUpProfile.this, "Reading has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Reading") && !user_info.getInterest3().equals("Reading") && !user_info.getInterest4().equals("Reading")) {
                        user_info.setInterest2("Reading");
                        Toast.makeText(SetUpProfile.this, "Reading has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Reading") && !user_info.getInterest2().equals("Reading") && !user_info.getInterest4().equals("Reading")) {
                        user_info.setInterest3("Reading");
                        Toast.makeText(SetUpProfile.this, "Reading has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Reading") && !user_info.getInterest2().equals("Reading") && !user_info.getInterest3().equals("Reading")) {
                        user_info.setInterest4("Reading");
                        Toast.makeText(SetUpProfile.this, "Reading has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
                }
            }
        });

        interest_eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //these if statements will check the interest slots 1-4 to see if the said interest is already in it and if it is it'll remove it.
                if (user_info.getInterest1().equals("Martial Arts")) {
                    user_info.setInterest1(" ");
                    Toast.makeText(SetUpProfile.this,"Martial Arts has been removed from interest One spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest2().equals("Martial Arts")){
                    user_info.setInterest2(" ");
                    Toast.makeText(SetUpProfile.this,"Martial Arts has been removed from interest two spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest3().equals("Martial Arts")){
                    user_info.setInterest3(" ");
                    Toast.makeText(SetUpProfile.this,"Martial Arts has been removed from interest three spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest4().equals("Martial Arts")){
                    user_info.setInterest4(" ");
                    Toast.makeText(SetUpProfile.this,"Martial Arts has been removed from interest four spot.", Toast.LENGTH_LONG).show();
                }else if (user_info.getInterest1().equals(" ")){
                    if (!user_info.getInterest2().equals("Martial Arts") && !user_info.getInterest3().equals("Martial Arts") && !user_info.getInterest4().equals("Martial Arts")) {
                        user_info.setInterest1("Martial Arts");
                        Toast.makeText(SetUpProfile.this, "Martial Arts has been added to interest one spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest2().equals(" ")){
                    if (!user_info.getInterest1().equals("Martial Arts") && !user_info.getInterest3().equals("Martial Arts") && !user_info.getInterest4().equals("Martial Arts")) {
                        user_info.setInterest2("Martial Arts");
                        Toast.makeText(SetUpProfile.this, "Martial Arts has been added to interest two spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest3().equals(" ")) {
                    if (!user_info.getInterest1().equals("Martial Arts") && !user_info.getInterest2().equals("Martial Arts") && !user_info.getInterest4().equals("Martial Arts")) {
                        user_info.setInterest3("Martial Arts");
                        Toast.makeText(SetUpProfile.this, "Martial Arts has been added to interest three spot.", Toast.LENGTH_LONG).show();
                    }
                }else if (user_info.getInterest4().equals(" ")){
                    if (!user_info.getInterest1().equals("Martial Arts") && !user_info.getInterest2().equals("Martial Arts") && !user_info.getInterest3().equals("Martial Arts")) {
                        user_info.setInterest4("Martial Arts");
                        Toast.makeText(SetUpProfile.this, "Martial Arts has been added to interest four spot.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SetUpProfile.this,"All slots are filled, please uncheck another interest to add this interest.", Toast.LENGTH_LONG).show();
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
        FirebaseUser user;
        DatabaseReference mDatabase;
        String userID;

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();


        mDatabase.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                //code to display user information
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error toast
            }
        });
    }

    public void editCurrentUser(User newProfile){
        FirebaseUser user;
        DatabaseReference mDatabase;
        String userID;

        user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        mDatabase.child("Users").child(userID).setValue(newProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //code for Success

            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //code for Failure

            }
        });
    }
}


