package com.swe4550.dropin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Discover extends AppCompatActivity {

    //bonding variables
    User current_user;
    ArrayList<User> user_list;
    ArrayList<String> user_keys;
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
    Button pokesBtn;
    Button profileBtn;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bind views from UI
        pfp_one = findViewById(R.id.pfp_1);
        pfp_two = findViewById(R.id.pfp_2);
        pfp_three = findViewById(R.id.pfp_3);
        pfp_four = findViewById(R.id.pfp_4);
        pfp_five = findViewById(R.id.pfp_5);
        game_one = findViewById(R.id.game_1);
        game_two = findViewById(R.id.game_2);
        game_three = findViewById(R.id.game_3);
        game_four = findViewById(R.id.game_4);
        game_five = findViewById(R.id.game_5);
        interest_one = findViewById(R.id.interest_1);
        interest_two = findViewById(R.id.interest_2);
        interest_three = findViewById(R.id.interest_3);
        interest_four = findViewById(R.id.interest_4);
        interest_five = findViewById(R.id.interest_5);
        pokesBtn = findViewById(R.id.pokes_btn);
        profileBtn = findViewById(R.id.profile_btn);
        //get list of users in database by calling getUsers() and get the list of their keys by calling getUserKeys()
        DatabaseReference mDatabase;

        //Get reference for User Node
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        //Attach valueEventListener to mDatabase object to read all the values
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user_keys = new ArrayList<String>();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        User user = snapshot.getValue(User.class);
                        user_keys.add(snapshot.getKey());
                    }
                    DatabaseReference mDatabase;

                    //Get reference for User Node
                    mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                    //Attach valueEventListener to mDatabase object to read all the values
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            user_list = new ArrayList<User>();
                            User temp_user;
                            if(dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    temp_user = snapshot.getValue(User.class);
                                    user_list.add(temp_user);
                                }
                            }
                                FirebaseUser fire_user;
                                DatabaseReference mDatabase;
                                String userID;

                                fire_user = FirebaseAuth.getInstance().getCurrentUser();
                                mDatabase = FirebaseDatabase.getInstance().getReference("Users");
                                userID = fire_user.getUid();

                                mDatabase.child(userID).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        current_user = snapshot.getValue(User.class);

                                        //Sort ArrayList to prepare for displaying on Discover page, with the currently logged in user as the reference for "best"
                                        ArrayList<Integer> scores = new ArrayList<Integer>();
                                        ArrayList<String> current_user_games_interests = new ArrayList<String>(Arrays.asList(current_user.getGame1(), current_user.getGame2(), current_user.getGame3(), current_user.getGame4(), current_user.getInterest1(), current_user.getInterest2(), current_user.getInterest3(), current_user.getInterest4()));
                                        for (int i = 0; i < user_list.size(); i++) {
                                            ArrayList<String> user_games_interests = new ArrayList<String>(Arrays.asList(user_list.get(i).getGame1(), user_list.get(i).getGame2(), user_list.get(i).getGame3(), user_list.get(i).getGame4(), user_list.get(i).getInterest1(), user_list.get(i).getInterest2(), user_list.get(i).getInterest3(), user_list.get(i).getInterest4()));
                                            scores.add(0);
                                            for (String game_interest : user_games_interests) {
                                                for (String current_user_game_interest : current_user_games_interests) {
                                                    if (game_interest.equals(current_user_game_interest)) {
                                                        scores.set(i, scores.get(i) + 1);
                                                    }
                                                }
                                            }
                                        }
                                        //Each user now has an associated "best fit" ranking associated with it by index in the scores arraylist. Bubble sort to sort the users into the correct display order.
                                        //Order: Highest ranking = Largest number, Lowest Ranking = smallest number
                                        int it, jit, temp_int;
                                        User temp_user;
                                        String temp_string;
                                        int n = scores.size();
                                        boolean swapped;
                                        for (it = 0; it < n - 1; it++) {
                                            swapped = false;
                                            for (jit = 0; jit < n - it - 1; jit++) {
                                                if (scores.get(jit) > scores.get(jit + 1)) {
                                                    //Swap ranking positions
                                                    temp_int = scores.get(jit);
                                                    scores.set(jit, scores.get(jit + 1));
                                                    scores.set(jit + 1, temp_int);
                                                    //Swap user positions
                                                    temp_user = user_list.get(jit);
                                                    user_list.set(jit, user_list.get(jit + 1));
                                                    user_list.set(jit + 1, temp_user);
                                                    swapped = true;
                                                    //Swap user keys positions
                                                    temp_string = user_keys.get(jit);
                                                    user_keys.set(jit + 1, user_keys.get(jit + 1));
                                                    user_keys.set(jit + 1, temp_string);
                                                }
                                            }
                                            if (!swapped) {
                                                break;
                                            }
                                        }
                                        //Cut down the size of the user_list and user_keys to 5 if there are more than 5 users
                                        if (user_list.size() > 5) {
                                            user_list = new ArrayList<User>(user_list.subList(0, 6));
                                            user_keys = new ArrayList<String>(user_keys.subList(0, 6));
                                        }
                                        //Create list of games and interests that actually contain something for each user, to display on the discover page a valid game rather than a potential empty space
                                        ArrayList<ArrayList<String>> usable_games = new ArrayList<ArrayList<String>>();
                                        ArrayList<ArrayList<String>> usable_interests = new ArrayList<ArrayList<String>>();
                                        for (int i = 0; i < user_list.size(); i++) {
                                            usable_games.add(new ArrayList<String>());
                                            usable_interests.add(new ArrayList<String>());
                                        }
                                        //Extract games and interests that are not empty space into the usable array list collections
                                        for (int i = 0; i < user_list.size(); i++) {
                                            ArrayList<String> interests = new ArrayList<String>(Arrays.asList(user_list.get(i).getInterest1(), user_list.get(i).getInterest2(), user_list.get(i).getInterest3(), user_list.get(i).getInterest4()));
                                            ArrayList<String> games = new ArrayList<String>(Arrays.asList(user_list.get(i).getGame1(), user_list.get(i).getGame2(), user_list.get(i).getGame3(), user_list.get(i).getGame4()));
                                            //interests.size() used, but games.size() would have yielded the exact same number, its effectively a constant
                                            for (int j = 0; j < interests.size(); j++) {
                                                if (!interests.get(j).equals(" ")) {
                                                    usable_interests.get(i).add(interests.get(j));
                                                }
                                                if (!games.get(j).equals(" ")) {
                                                    usable_games.get(i).add(games.get(j));
                                                }
                                            }
                                        }
                                        //Now, we only have non-empty fields in the usable_games and usable_interests ArrayLists.
                                        //Make ImageViews for users visible according to amount of users there are to display, and out of the available interests and games, display one of each randomly.
                                        switch (user_list.size()) {
                                            case 5:
                                                pfp_five.setVisibility(View.VISIBLE);
                                                pfp_five.setImageResource(getImageInt(user_list.get(4).getPfp()));
                                                game_five.setText(usable_games.get(4).get(ThreadLocalRandom.current().nextInt(0, usable_games.get(4).size() + 1)));
                                                interest_five.setText(usable_interests.get(4).get(ThreadLocalRandom.current().nextInt(0, usable_interests.get(4).size() + 1)));
                                            case 4:
                                                pfp_four.setVisibility(View.VISIBLE);
                                                pfp_four.setImageResource(getImageInt(user_list.get(3).getPfp()));
                                                game_four.setText(usable_games.get(3).get(ThreadLocalRandom.current().nextInt(0, usable_games.get(3).size() + 1)));
                                                interest_four.setText(usable_interests.get(3).get(ThreadLocalRandom.current().nextInt(0, usable_interests.get(3).size() + 1)));
                                            case 3:
                                                pfp_three.setVisibility(View.VISIBLE);
                                                pfp_three.setImageResource(getImageInt(user_list.get(2).getPfp()));
                                                game_three.setText(usable_games.get(2).get(ThreadLocalRandom.current().nextInt(0, usable_games.get(2).size() + 1)));
                                                interest_three.setText(usable_interests.get(2).get(ThreadLocalRandom.current().nextInt(0, usable_interests.get(2).size() + 1)));
                                            case 2:
                                                pfp_two.setVisibility(View.VISIBLE);
                                                pfp_two.setImageResource(getImageInt(user_list.get(1).getPfp()));
                                                game_two.setText(usable_games.get(1).get(ThreadLocalRandom.current().nextInt(0, usable_games.get(1).size() + 1)));
                                                interest_two.setText(usable_interests.get(1).get(ThreadLocalRandom.current().nextInt(0, usable_interests.get(1).size() + 1)));
                                            case 1:
                                                pfp_one.setVisibility(View.VISIBLE);
                                                pfp_one.setImageResource(getImageInt(user_list.get(0).getPfp()));
                                                game_one.setText(usable_games.get(0).get(ThreadLocalRandom.current().nextInt(0, usable_games.get(0).size() + 1)));
                                                interest_one.setText(usable_interests.get(0).get(ThreadLocalRandom.current().nextInt(0, usable_interests.get(0).size() + 1)));
                                        }
                                        //Sends the user to the PokeView Activity
                                        pokesBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                startActivity(new Intent(Discover.this, PokeView.class));
                                            }
                                        });
                                        //Sends the user to the ProfileView Activity, with their own key as the extra arg
                                        profileBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Intent ViewProfileAct = new Intent(Discover.this, ViewProfile.class);
                                                ViewProfileAct.putExtra("USER KEY", FirebaseAuth.getInstance().getCurrentUser().getUid());
                                                startActivity(ViewProfileAct);
                                            }
                                        });
                                        } // On data change for current_user
                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Log.e("Errory", "Bruh");
                                        }
                                    }); //Value event listener for current user
                        }//ondatachange for second, user_list

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    }); //Value event listener for user_keys

                }//If data snapshot exists for first, user keys
            }//First onDataChange Change

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    //Function sets the correct image resource using the passed in string
    public int getImageInt(String key){
        int imageInt;
        switch(key){
            case "Xbox":
                imageInt = R.id.xbox_icon_logo;
                break;
            case "Computer":
                imageInt = R.id.personal_computer_icon_logo;
                break;
            case "Nintendo":
                imageInt = R.id.nintendo_switch_icon_logo;
                break;
            case "Playstation":
                imageInt = R.id.playstation_icon_logo;
            default:
                imageInt = R.drawable.grey_background_circle;
        }
        return imageInt;
    }

//    //Get, in the order that they appear in the Database, all the users from the database stored as an array list.
//    public void getUsers(){
//
//        user_list = new ArrayList<>();
//        DatabaseReference mDatabase;
//
//        //Get reference for User Node
//        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
//        //Attach valueEventListener to mDatabase object to read all the values
//        mDatabase.addListenerForSingleValueEvent(valueEventListener);
//
//    }
//
//    //Value Event Listener reads user from firebase database
//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            user_list.clear();
//            if(dataSnapshot.exists()){
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    User user = snapshot.getValue(User.class);
//                    user_list.add(user);
//                }
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    };
//

    //Get users identification hash's for each user, in order of database, and store them in an array list.
//    public void getUserKeys(){
//
//        user_keys = new ArrayList<>();
//        DatabaseReference mDatabase;
//
//        //Get reference for User Node
//        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
//        //Attach valueEventListener to mDatabase object to read all the values
//        mDatabase.addListenerForSingleValueEvent(valueEventListener2);
//
//    }
//    //Value Event Listener reads user from firebase database
//    ValueEventListener valueEventListener2 = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//            user_keys.clear();
//            if(dataSnapshot.exists()){
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    User user = snapshot.getValue(User.class);
//                    user_keys.add(snapshot.getKey());
//                }
//            }
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError error) {
//
//        }
//    };


}
//comment made by carlos at midnight
//Test Comment