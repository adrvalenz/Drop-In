package com.swe4550.dropin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class PokeView extends AppCompatActivity {

    //PokeView bonding variables
    ArrayList<User> poker_list;
    ArrayList<String> user_keys;
    TextView poker_one;
    TextView poker_two;
    TextView poker_three;
    TextView poker_four;
    TextView poker_five;
    TextView wants_to_playOne;
    TextView wants_to_playTwo;
    TextView wants_to_playThree;
    TextView wants_to_playFour;
    TextView wants_to_playFive;
    ImageView pfp_one;
    ImageView pfp_two;
    ImageView pfp_three;
    ImageView pfp_four;
    ImageView pfp_five;
    Button discoverBtn;
    Button profileBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_view);
        poker_one = findViewById(R.id.poker_1);
        poker_two = findViewById(R.id.poker_2);
        poker_three = findViewById(R.id.poker_3);
        poker_four = findViewById(R.id.poker_4);
        poker_five = findViewById(R.id.poker_5);
        wants_to_playOne = findViewById(R.id.wants_to_play_1);
        wants_to_playTwo = findViewById(R.id.wants_to_play_2);
        wants_to_playThree = findViewById(R.id.wants_to_play_3);
        wants_to_playFour= findViewById(R.id.wants_to_play_4);
        wants_to_playFive= findViewById(R.id.wants_to_play_5);
        pfp_one = findViewById(R.id.pfp_1);
        pfp_two = findViewById(R.id.pfp_2);
        pfp_three = findViewById(R.id.pfp_3);
        pfp_four = findViewById(R.id.pfp_4);
        pfp_five = findViewById(R.id.pfp_5);
        discoverBtn = findViewById(R.id.discover_btn);
        profileBtn = findViewById(R.id.profile_btn);
        //getPokerInfo();
//Call getPokerInfo(), now poker_list and poker_keys are populated.
//For each user in the arraylist, make an imageView visible.
        //Database function start here, need the list of users that have poked the currently logged in user
        //Database code here
        user_keys = new ArrayList<String>();
        //get list of users in database by calling getUsers() and get the list of their keys by calling getUserKeys()
        DatabaseReference mDatabase;
        //Get currently signed in user's keys
        mDatabase = FirebaseDatabase.getInstance().getReference("pokes");

        //Attach valueEventListener to mDatabase object to read all the values
        mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String user_key_string;
                user_key_string = dataSnapshot.getValue(String.class);






        //splitting user_keys with Leading and Trailing white space
        ArrayList<String> split = new ArrayList<>(Arrays.asList(user_key_string.trim().split("\\s+")));



            //Get reference for User Node
            DatabaseReference mDatabase2 = FirebaseDatabase.getInstance().getReference("Users");
            //Attach valueEventListener to mDatabase object to read all the values
            mDatabase2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    poker_list = new ArrayList<User>();

                    User temp_user;
                    if(dataSnapshot.exists()) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            for(int i = 0; i < split.size(); i++) {
                                if (snapshot.getValue(String.class).equals(split.get(i))) {
                                    i++;
                                    temp_user = snapshot.getValue(User.class);
                                    poker_list.add(temp_user);
                                }
                            }
                        }
                    }



                    //
                    //Now at this point, poker_list is populated
//String game_three = poker_list.get(0).getGame3();

            switch(poker_list.size()){
    case 5:
        pfp_five.setVisibility(View.VISIBLE);
        pfp_five.setImageResource(getImageDrawable(poker_list.get(4).getPfp()));
        wants_to_playFive.setVisibility(View.VISIBLE);
        poker_five.setText(poker_list.get(4).getUserName());
    case 4:
        pfp_four.setVisibility(View.VISIBLE);
        pfp_four.setImageResource(getImageDrawable(poker_list.get(3).getPfp()));
        wants_to_playFour.setVisibility(View.VISIBLE);
        poker_four.setText(poker_list.get(3).getUserName());
    case 3:
        pfp_three.setVisibility(View.VISIBLE);
        pfp_three.setImageResource(getImageDrawable(poker_list.get(2).getPfp()));
        wants_to_playThree.setVisibility(View.VISIBLE);
        poker_three.setText(poker_list.get(2).getUserName());
    case 2:
        pfp_two.setVisibility(View.VISIBLE);
        pfp_two.setImageResource(getImageDrawable(poker_list.get(1).getPfp()));
        wants_to_playTwo.setVisibility(View.VISIBLE);
        poker_two.setText(poker_list.get(1).getUserName());
    case 1:
        pfp_one.setVisibility(View.VISIBLE);
        pfp_one.setImageResource(getImageDrawable(poker_list.get(0).getPfp()));
        wants_to_playOne.setVisibility(View.VISIBLE);
        poker_one.setText(poker_list.get(0).getUserName());
}
//This is the end of where the database stuff is needed

profileBtn.setOnClickListener(new View.OnClickListener() {
    @Override public void onClick(View view) {
        Intent ViewProfileAct = new Intent(PokeView.this, ViewProfile.class);
        ViewProfileAct.putExtra("USER KEY", FirebaseAuth.getInstance().getCurrentUser().getUid());
        startActivity(ViewProfileAct);
    }
});


        discoverBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(PokeView.this, Discover.class));
                }
        });
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

});
    }



    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    }
//Database method no longer needed here/ends here



    public int getImageDrawable(String key){
        int imageInt;
        switch(key){
            case "Xbox":
                imageInt = R.drawable.xbox_icon_logo;
                break;
            case "Computer":
                imageInt = R.drawable.personal_computer_icon_logo;
                break;
            case "Nintendo":
                imageInt = R.drawable.nintendo_switch_icon_logo;
                break;
            case "Playstation":
                imageInt = R.drawable.playstation_icon_logo;
                break;
            default:
                imageInt = R.drawable.grey_background_circle;
        }
        return imageInt;
    }
}
