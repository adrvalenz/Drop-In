package com.swe4550.dropin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
    TextView pokeText;
    TextView rateText;


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
        pokeText = findViewById(R.id.poke_txt);
        rateText = findViewById(R.id.rate_txt);

    }
}