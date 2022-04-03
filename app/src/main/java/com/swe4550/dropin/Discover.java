package com.swe4550.dropin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Discover extends AppCompatActivity {

    //bonding variables
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
        //Bind views from UI
//        summary_one = findViewById(R.id.summary_1);
//        summary_two = findViewById(R.id.summary_2);
//        summary_three = findViewById(R.id.summary_3);
//        summary_four = findViewById(R.id.summary_4);
//        summary_five = findViewById(R.id.summary_5);
//        pfp_one = findViewById(R.id.pfp_1);
//        pfp_two = findViewById(R.id.pfp_2);
//        pfp_three = findViewById(R.id.pfp_3);
//        pfp_four = findViewById(R.id.pfp_4);
//        pfp_five = findViewById(R.id.pfp_5);
//        game_one = findViewById(R.id.game_1);
//        game_two = findViewById(R.id.game_2);
//        game_three = findViewById(R.id.game_3);
//        game_four = findViewById(R.id.game_4);
//        game_five = findViewById(R.id.game_5);
//        interest_one = findViewById(R.id.interest_1);
//        interest_two = findViewById(R.id.interest_2);
//        interest_three = findViewById(R.id.interest_3);
//        interest_four = findViewById(R.id.interest_4);
//        interest_five = findViewById(R.id.interest_5);
//        pokesBtn = findViewById(R.id.pokes_btn);
//        profileBtn = findViewById(R.id.profile_btn);
        //get list of users in database by calling getUsers() and get the list of their keys by calling getUserKeys()
        //getUserKeys();
        getUsers();
        //getCurrentUserData();
        ArrayList<Integer> scores = new ArrayList<Integer>();
        //Sort ArrayList to prepare for displaying on Discover page, with the currently logged in user as the reference for "best"


    }
    //Get, in the order that they appear in the Database, all the users from the database stored as an array list.
    public void getUsers(){

        user_list = new ArrayList<>();
        DatabaseReference mDatabase;

        //Get reference for User Node
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        //Attach valueEventListener to mDatabase object to read all the values
        mDatabase.addListenerForSingleValueEvent(valueEventListener);

    }

    //Value Event Listener reads user from firebase database
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            user_list.clear();
            if(dataSnapshot.exists()){
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);
                    user_list.add(user);
                }
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


}
//comment made by carlos at midnight
//Test Comment