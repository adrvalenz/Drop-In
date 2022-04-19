package com.swe4550.dropin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PokeView extends AppCompatActivity {

    //PokeView bonding variables
    ArrayList<User> poker_list;
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
        //Call getPokerInfo(), now poker_list and poker_keys are populated.
        getPokerInfo();
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
        //For each user in the arraylist, make an imageView visible.
        pfp_one = (ImageView)findViewById(R.id.pfp_1);
        pfp_two = (ImageView)findViewById(R.id.pfp_2);
        pfp_three = (ImageView)findViewById(R.id.pfp_3);
        pfp_four = (ImageView)findViewById(R.id.pfp_4);
        pfp_five = (ImageView)findViewById(R.id.pfp_5);
        discoverBtn = findViewById(R.id.discover_btn);
        profileBtn = findViewById(R.id.profile_btn);














        DatabaseReference mDatabase;

        //
        mDatabase = FirebaseDatabase.getInstance().getReference("Pokes");
        //
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                poker_list = new ArrayList<String>();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
            //User user = snapshot.getValue(User.class);
                        if(!snapshot.getKey().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                            poker_list.add(snapshot.getKey());
                        }
                    }
                    DatabaseReference mDatabase;
    }
}
























profileBtn.setOnClickListener(new View.OnClickListener() {
    @Override public void onClick(View view) {
        Intent ViewProfileAct = new Intent(Discover.this, ViewProfile.class);
        ViewProfileAct.putExtra("USER KEY", FirebaseAuth.getInstance().getCurrentUser().getUid());
        startActivity(ViewProfileAct);
    }
});
        }






        discoverBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Discover.this, PokeView.class));
                }
        });

