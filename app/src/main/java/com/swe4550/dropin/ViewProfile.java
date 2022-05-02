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

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class ViewProfile extends AppCompatActivity {

    //bonded the variables
    User user_info;
    ImageView userPfp;
    TextView userName;
    EditText bio;
    ImageView game_one;
    ImageView game_two;
    ImageView game_three;
    ImageView game_four;
    TextView interest_one;
    TextView interest_two;
    TextView interest_three;
    TextView interest_four;
    Button star_btn_one;
    Button star_btn_two;
    Button star_btn_three;
    Button star_btn_four;
    Button star_btn_five;
    ImageView star_image_one;
    ImageView star_image_two;
    ImageView star_image_three;
    ImageView star_image_four;
    ImageView star_image_five;
    Button setupProfile;
    Button logoutBtn;
    Button pokeBtn;
    TextView poke_text;
    TextView rate_text;
    ImageView setupProfileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        //setting the variables to their UI counterparts
        userPfp = (ImageView)findViewById(R.id.user_pfp);
        userName = findViewById(R.id.user_name);
        bio = findViewById(R.id.biography);
        game_one = findViewById(R.id.game_1);
        game_two = findViewById(R.id.game_2);
        game_three = findViewById(R.id.game_3);
        game_four = findViewById(R.id.game_4);
        interest_one = findViewById(R.id.interest_1);
        interest_two = findViewById(R.id.interest_2);
        interest_three = findViewById(R.id.interest_3);
        interest_four = findViewById(R.id.interest_4);
        star_btn_one = findViewById(R.id.star_btn_1);
        star_btn_two = findViewById(R.id.star_btn_2);
        star_btn_three = findViewById(R.id.star_btn_3);
        star_btn_four = findViewById(R.id.star_btn_4);
        star_btn_five = findViewById(R.id.star_btn_5);
        star_image_one = findViewById(R.id.star_image_1);
        star_image_two = findViewById(R.id.star_image_2);
        star_image_three = findViewById(R.id.star_image_3);
        star_image_four = findViewById(R.id.star_image_4);
        star_image_five = findViewById(R.id.star_image_5);
        setupProfile = findViewById(R.id.setup_profile);
        logoutBtn = findViewById(R.id.login_btn);
        pokeBtn = findViewById(R.id.poke_btn);
        poke_text = findViewById(R.id.poke_txt);
        rate_text = findViewById(R.id.rate_txt);
        setupProfileImg = findViewById(R.id.setup_profile_img);


        //if yes
        //Set log-out and edit profile buttons to visible
        //set each star, rate text, Poke text, and the Poke button to invisible
        //Set the logout button, and the edit profile button that takes the user to "SetupProfile" Activity
        // to VISIBLE
        String viewed_user_key = getIntent().getStringExtra("USER KEY");
        if (viewed_user_key.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {

            logoutBtn.setVisibility(View.VISIBLE);
            setupProfile.setVisibility(View.VISIBLE);
            setupProfileImg.setVisibility(View.VISIBLE);

            star_btn_one.setVisibility(View.INVISIBLE);
            star_btn_two.setVisibility(View.INVISIBLE);
            star_btn_three.setVisibility(View.INVISIBLE);
            star_btn_four.setVisibility(View.INVISIBLE);
            star_btn_five.setVisibility(View.INVISIBLE);
            star_image_one.setVisibility(View.INVISIBLE);
            star_image_two.setVisibility(View.INVISIBLE);
            star_image_three.setVisibility(View.INVISIBLE);
            star_image_four.setVisibility(View.INVISIBLE);
            star_image_five.setVisibility(View.INVISIBLE);


            rate_text.setVisibility(View.INVISIBLE);
            poke_text.setVisibility(View.INVISIBLE);
            pokeBtn.setVisibility(View.INVISIBLE);
        }
//event listeners

// End of Get user key sent from previous activity
        //Check if the profile being viewed is the person who is logged in

        //getIntent().putExtra("USER KEY", FirebaseAuth.getInstance().getCurrentUser().getUid());
        // if (setupProfile.getText().toString().equals(logoutBtn.FirebaseAuth.getInstance().getCurrentUser().getUid))


        // End of Check (now for the yes or no)
        // end of yes
        //if no
        //Database code here: download the user information.....









        //End of Database code

        //(start of matthew's code)
        //setting the profile picture to the one that they chose before.
        if (user_info.getPfp().equals("Xbox")) {
            userPfp.setImageResource(R.drawable.xbox_icon_logo);
        } else if (user_info.getPfp().equals("Computer")) {
            userPfp.setImageResource(R.drawable.personal_computer_icon_logo);
        } else if (user_info.getPfp().equals("Nintendo")) {
            userPfp.setImageResource(R.drawable.nintendo_switch_icon_logo);
        } else if (user_info.getPfp().equals("Playstation")) {
            userPfp.setImageResource(R.drawable.playstation_icon_logo);
        }


        //setting the users user name.
        userName.setText(user_info.getUserName());

        // setting the users Bio
        bio.setText(user_info.getBiography());

        //displaying the games and interests.
        ArrayList<String> given_games = new ArrayList<>();
        ArrayList<String> given_interests = new ArrayList<>();
        ArrayList<String> games = new ArrayList<>(Arrays.asList(user_info.getGame1(),user_info.getGame2(),user_info.getGame3(),user_info.getGame4()));
        ArrayList<String> interests = new ArrayList<>(Arrays.asList(user_info.getInterest1(),user_info.getInterest2(),user_info.getInterest3(),user_info.getInterest4()));
        for(int U = 0; U < interests.size(); U++) {
            if(!interests.get(U).equals(" ")){
                given_interests.add(interests.get(U));
            }
            if(!games.get(U).equals(" ")){
                given_games.add(games.get(U));
            }
        }

        switch (given_games.size()){
            case 4:
                game_four.setVisibility(View.VISIBLE);
                game_four.setImageResource(gameImageDrawable(given_games.get(3)));
            case 3:
                game_three.setVisibility(View.VISIBLE);
                game_three.setImageResource(gameImageDrawable(given_games.get(2)));
            case 2:
                game_two.setVisibility(View.VISIBLE);
                game_two.setImageResource(gameImageDrawable(given_games.get(1)));
            case 1:
                game_one.setVisibility(View.VISIBLE);
                game_one.setImageResource(gameImageDrawable(given_games.get(0)));

        }
        //switch statement for interests.
        switch (given_interests.size()){
            case 4:
                interest_four.setVisibility(View.VISIBLE);
                interest_four.setText(given_interests.get(3));
            case 3:
                interest_three.setVisibility(View.VISIBLE);
                interest_three.setText(given_interests.get(2));
            case 2:
                interest_two.setVisibility(View.VISIBLE);
                interest_two.setText(given_interests.get(1));
            case 1:
                interest_one.setVisibility(View.VISIBLE);
                interest_one.setText(given_interests.get(0));
        }


        //on user's profile(matthew code)
        // edit profile button
        setupProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfile.this, SetUpProfile.class));
            }
        });

        //log out button(husk for database)
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(ViewProfile.this, LogIn.class));
            }
        });

        //On Other User's Profile
        // Star Clicked: Control taken by Database Team.
        // Point out the key of the user being viewed and the variable holding the rating that was given to them
        star_btn_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inside here
                setStarImageDrawable("1");
                setEmptyStars("1");
                //I need to send data here, the data I need to be sent is the number 1 as a string, and below is the currently viewed user's key
                String viewed_user_key = getIntent().getStringExtra("USER KEY");
            }
        });

        star_btn_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inside here
                setStarImageDrawable("2");
                setEmptyStars("2");
                // I need to send data here, the data I need to be sent is the number 2 as a string, and below is the currently viewed user's key
                String viewed_user_key = getIntent().getStringExtra("USER KEY");
            }
        });

        star_btn_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inside here
                setStarImageDrawable("3");
                setEmptyStars("3");
                // I need to send data here, the data I need to be sent is the number 3 as a string, and below is the currently viewed user's key
                String viewed_user_key = getIntent().getStringExtra("USER KEY");

            }
        });

        star_btn_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inside here
                setStarImageDrawable("4");
                setEmptyStars("4");
                //I need to send data here, the data I need to be sent is the number 4 as a string, and below is the currently viewed user's key
                String viewed_user_key = getIntent().getStringExtra("USER KEY");
            }
        });

        star_btn_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inside here
                setStarImageDrawable("5");
                setEmptyStars("5");
                //I need to send data here, the data I need to be sent is the number 5 as a string, and below is the currently viewed user's key
                String viewed_user_key = getIntent().getStringExtra("USER KEY");
            }
        });

        pokeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // here is the user that is currently signed in:
                String current_user = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //Database code and stuff starts here

            }
        });



        // Database Code here: Set the star rating of the user who's profile ...


        //end of Database code


}

    //Method sets the correct image resource using the passed in string
    private void setStarImageDrawable(String key){
        switch(key){
            case "5":
                star_image_five.setImageResource(R.drawable.filled_star);
            case "4":
                star_image_four.setImageResource(R.drawable.filled_star);
            case "3":
                star_image_three.setImageResource(R.drawable.filled_star);
            case "2":
                star_image_two.setImageResource(R.drawable.filled_star);
            case "1":
                star_image_one.setImageResource(R.drawable.filled_star);
                //imageInt = R.drawable.grey_background_circle;
        }
    }

    private void setEmptyStars(String key){
        switch(key){
            case "1":
                star_image_two.setImageResource(R.drawable.empty_star);
            case "2":
                star_image_three.setImageResource(R.drawable.empty_star);
            case "3":
                star_image_four.setImageResource(R.drawable.empty_star);
            case "4":
                star_image_five.setImageResource(R.drawable.empty_star);

        }
    }


    //Method sets the correct image resource using the passed in string
    public int gameImageDrawable(String key){
        int imageInt;
        switch(key){
            case "Elden Ring":
                imageInt = R.drawable.elden_ring_game_image;
                break;
            case "Fortnite":
                imageInt = R.drawable.fortnite_game_image;
                break;
            case "Apex Legends":
                imageInt = R.drawable.apex_legends_game_image;
                break;
            case "Call of Duty Warzone":
                imageInt = R.drawable.callofduty_warzone_game_image;
                break;
            case "Rocket League":
                imageInt = R.drawable.rocket_league_game_image;
                break;
            case "Minecraft":
                imageInt = R.drawable.minecraft_game_image;
                break;
            case "Grand Theft Auto Online":
                imageInt = R.drawable.grand_theft_auto_game_image;
                break;
            case "Fallout 76":
                imageInt = R.drawable.fallout_76_game_image;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + key);
        }
        return imageInt;
    }
}