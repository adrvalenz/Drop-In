package com.swe4550.dropin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Discover extends AppCompatActivity {

    //bonding variables
    User current_user;
    User user_list;
    String user_keys;
    Button summary_one;
    Button summary_two;
    Button summary_three;
    Button summary_four;
    Button summary_five;
    ImageView pfp_one;
    ImageView pfp_two;
    ImageView pfp_three;
    ImageView pfp_four;
    ImageView pfp_five;
    TextView game_one;
    TextView game_two;
    TextView game_three;
    TextView game_four;
    TextView game_five;
    TextView interest_one;
    TextView interest_two;
    TextView interest_three;
    TextView interest_four;
    TextView interest_five;
    TextView pokesBtn;
    TextView profileBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // beginning of code contribution
        //get list of users in database by calling getUsers() and get the list of their keys by calling getUserKeys()
        getUserKeys();
        getCurrentUsers();
        getCurrentUserData();
        // Sort the arrayList using an algorithm that ranks users with the most amount of interests and games in common
        //Rank users by interests and games in common at the top (lowest index) to bottom (higher index)

    }
    public void getUserKeys(){}

    public void getCurrentUsers(){}

    public void getCurrentUserData(){}
}