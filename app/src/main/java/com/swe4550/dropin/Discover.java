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

    User current_user;
    ArrayList<User> user_list;
    ArrayList<String> user_keys;
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
        //Sort ArrayList to prepare for displaying on Discover page, with the currently logged in user as the reference for "best"


    }
    public void getUserKeys(){}

    public void getCurrentUsers(){}

    public void getCurrentUserData(){}
}