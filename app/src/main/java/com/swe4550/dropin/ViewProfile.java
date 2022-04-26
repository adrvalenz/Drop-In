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
    String user_key;
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

        //Get user key sent from previous activity stored with key: "USER KEY"
        setupProfile.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent ViewProfileAct = new Intent(ViewProfile.this, SetUpProfile.class);

                startActivity(ViewProfileAct);
            }
        });
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

                if (user_info.getGame4().equals("Elden Ring")) {
                game_four.setImageResource(R.drawable.elden_ring_game_image);
                }else if (user_info.getGame4().equals("Fortnite")){
                game_four.setImageResource(R.drawable.fortnite_game_image);
                }else if (user_info.getGame4().equals("Apex Legends")){
                game_four.setImageResource(R.drawable.apex_legends_game_image);
                }else if (user_info.getGame4().equals("Call of Duty Warzone")){
                game_four.setImageResource(R.drawable.callofduty_warzone_game_image);
                }else if (user_info.getGame4().equals("Rocket League")){
                game_four.setImageResource(R.drawable.rocket_league_game_image);
                }else if (user_info.getGame4().equals("Minecraft")){
                game_four.setImageResource(R.drawable.minecraft_game_image);
                }else if (user_info.getGame4().equals("Grand Theft Auto Online")){
                game_four.setImageResource(R.drawable.grand_theft_auto_game_image);
                }else if (user_info.getGame4().equals("Fallout 76")){
                game_four.setImageResource(R.drawable.fallout_76_game_image);
                }
            case 3:
                game_three.setVisibility(View.VISIBLE);

                if (user_info.getGame3().equals("Elden Ring")) {
                    game_three.setImageResource(R.drawable.elden_ring_game_image);
                }else if (user_info.getGame3().equals("Fortnite")){
                    game_three.setImageResource(R.drawable.fortnite_game_image);
                }else if (user_info.getGame3().equals("Apex Legends")){
                    game_three.setImageResource(R.drawable.apex_legends_game_image);
                }else if (user_info.getGame3().equals("Call of Duty Warzone")){
                    game_three.setImageResource(R.drawable.callofduty_warzone_game_image);
                }else if (user_info.getGame3().equals("Rocket League")){
                    game_three.setImageResource(R.drawable.rocket_league_game_image);
                }else if (user_info.getGame3().equals("Minecraft")){
                    game_three.setImageResource(R.drawable.minecraft_game_image);
                }else if (user_info.getGame3().equals("Grand Theft Auto Online")){
                    game_three.setImageResource(R.drawable.grand_theft_auto_game_image);
                }else if (user_info.getGame3().equals("Fallout 76")){
                    game_three.setImageResource(R.drawable.fallout_76_game_image);
                }
            case 2:
                game_two.setVisibility(View.VISIBLE);

                if (user_info.getGame2().equals("Elden Ring")) {
                    game_two.setImageResource(R.drawable.elden_ring_game_image);
                }else if (user_info.getGame2().equals("Fortnite")){
                    game_two.setImageResource(R.drawable.fortnite_game_image);
                }else if (user_info.getGame2().equals("Apex Legends")){
                    game_two.setImageResource(R.drawable.apex_legends_game_image);
                }else if (user_info.getGame2().equals("Call of Duty Warzone")){
                    game_two.setImageResource(R.drawable.callofduty_warzone_game_image);
                }else if (user_info.getGame2().equals("Rocket League")){
                    game_two.setImageResource(R.drawable.rocket_league_game_image);
                }else if (user_info.getGame2().equals("Minecraft")){
                    game_two.setImageResource(R.drawable.minecraft_game_image);
                }else if (user_info.getGame2().equals("Grand Theft Auto Online")){
                    game_two.setImageResource(R.drawable.grand_theft_auto_game_image);
                }else if (user_info.getGame2().equals("Fallout 76")){
                    game_two.setImageResource(R.drawable.fallout_76_game_image);
                }
            case 1:
                game_one.setVisibility(View.VISIBLE);

                if (user_info.getGame1().equals("Elden Ring")) {
                    game_one.setImageResource(R.drawable.elden_ring_game_image);
                }else if (user_info.getGame1().equals("Fortnite")){
                    game_one.setImageResource(R.drawable.fortnite_game_image);
                }else if (user_info.getGame1().equals("Apex Legends")){
                    game_one.setImageResource(R.drawable.apex_legends_game_image);
                }else if (user_info.getGame1().equals("Call of Duty Warzone")){
                    game_one.setImageResource(R.drawable.callofduty_warzone_game_image);
                }else if (user_info.getGame1().equals("Rocket League")){
                    game_one.setImageResource(R.drawable.rocket_league_game_image);
                }else if (user_info.getGame1().equals("Minecraft")){
                    game_one.setImageResource(R.drawable.minecraft_game_image);
                }else if (user_info.getGame1().equals("Grand Theft Auto Online")){
                    game_one.setImageResource(R.drawable.grand_theft_auto_game_image);
                }else if (user_info.getGame1().equals("Fallout 76")){
                    game_one.setImageResource(R.drawable.fallout_76_game_image);
                }

        }
        //switch statement for interests.
        switch (given_interests.size()){
            case 4:
                interest_four.setVisibility(View.VISIBLE);

                if (user_info.getInterest4().equals("Sports")) {
                    interest_four.setText("Sports");
                }else if (user_info.getInterest4().equals("Art")){
                    interest_four.setText("Art");
                }else if (user_info.getInterest4().equals("Cars")){
                    interest_four.setText("Cars");
                }else if (user_info.getInterest4().equals("Music")){
                    interest_four.setText("Music");
                }else if (user_info.getInterest4().equals("Cooking")){
                    interest_four.setText("Cooking");
                }else if (user_info.getInterest4().equals("Anime")){
                    interest_four.setText("Anime");
                }else if (user_info.getInterest4().equals("Reading")){
                    interest_four.setText("Reading");
                }else if (user_info.getInterest4().equals("Martial Arts")){
                    interest_four.setText("Martial Arts");
                }
            case 3:
                interest_three.setVisibility(View.VISIBLE);

                if (user_info.getInterest3().equals("Sports")) {
                    interest_three.setText("Sports");
                }else if (user_info.getInterest3().equals("Art")){
                    interest_three.setText("Art");
                }else if (user_info.getInterest3().equals("Cars")){
                    interest_three.setText("Cars");
                }else if (user_info.getInterest3().equals("Music")){
                    interest_three.setText("Music");
                }else if (user_info.getInterest3().equals("Cooking")){
                    interest_three.setText("Cooking");
                }else if (user_info.getInterest3().equals("Anime")){
                    interest_three.setText("Anime");
                }else if (user_info.getInterest3().equals("Reading")){
                    interest_three.setText("Reading");
                }else if (user_info.getInterest3().equals("Martial Arts")){
                    interest_three.setText("Martial Arts");
                }
            case 2:
                interest_two.setVisibility(View.VISIBLE);

                if (user_info.getInterest2().equals("Sports")) {
                    interest_two.setText("Sports");
                }else if (user_info.getInterest2().equals("Art")){
                    interest_two.setText("Art");
                }else if (user_info.getInterest2().equals("Cars")){
                    interest_two.setText("Cars");
                }else if (user_info.getInterest2().equals("Music")){
                    interest_two.setText("Music");
                }else if (user_info.getInterest2().equals("Cooking")){
                    interest_two.setText("Cooking");
                }else if (user_info.getInterest2().equals("Anime")){
                    interest_two.setText("Anime");
                }else if (user_info.getInterest2().equals("Reading")){
                    interest_two.setText("Reading");
                }else if (user_info.getInterest2().equals("Martial Arts")){
                    interest_two.setText("Martial Arts");
                }
            case 1:
                interest_one.setVisibility(View.VISIBLE);

                if (user_info.getInterest1().equals("Sports")) {
                    interest_one.setText("Sports");
                }else if (user_info.getInterest1().equals("Art")){
                    interest_one.setText("Art");
                }else if (user_info.getInterest1().equals("Cars")){
                    interest_one.setText("Cars");
                }else if (user_info.getInterest1().equals("Music")){
                    interest_one.setText("Music");
                }else if (user_info.getInterest1().equals("Cooking")){
                    interest_one.setText("Cooking");
                }else if (user_info.getInterest1().equals("Anime")){
                    interest_one.setText("Anime");
                }else if (user_info.getInterest1().equals("Reading")){
                    interest_one.setText("Reading");
                }else if (user_info.getInterest1().equals("Martial Arts")){
                    interest_one.setText("Martial Arts");
                }
        }


        //on user's profile(matthew code)
        // edit profile button
        setupProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProfile.this, SetUpProfile.class));
            }
        });

        //On Other User's Profile
        // Star Clicked: Control taken by Database Team.
        // Point out the key of the user being viewed and the variable holding the rating that was given to them
        int User;
        char getRating;
        getratingExists();





        // Database Code here: Set the star rating of the user who's profile ...


        //end of Database code


}

    private void getratingExists() {
    }
}