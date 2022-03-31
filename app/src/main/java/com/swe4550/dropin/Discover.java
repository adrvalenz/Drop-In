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
        ArrayList<String> list = new ArrayList<String>();
        list.add(interest_one.toString());
        getuserName();
        // assign a value of one to an interesting game
         interest_one = 1



















public class ListNode{
            //data were are interested (user in our case)
   private  int val;
   private ListNode next;  // always pass by value (missed node) (one direction)
    private ListNode previous; // link to the previous one and an object reference
   public ListNode()  {}
   public ListNode (int val){this.val = val;}
    public ListNode (int val, ListNode next) {this.val = val; this.next = next;}
     public ListNode(int val, ListNode next, ListNode previous){ this.val = val; this.next = next; this.previous = previous; }

public void connect_two_previous(ListNode previous){this.previous = previous;}
    public void connect_two_next(ListNode next){this.previous = previous;}

public ListNode getNext(){return this.next; }
    public ListNode getPrevious(){return this.previous; }
 public int getVal(){return this.val;}


}

ListNode node_one = new ListNode(3);
ListNode node_three = new ListNode(4);
ListNode node_two = new ListNode(3, node_one, node_three);
        node_one.connect_two_next(node_two);
        node_three.connect_two_previous(node_two);
        node_three.connect_two_next(node_one);
        node_one.connect_two_previous(node_three);
        System.out.println(node_one.getVal());
        System.out.println(node_one.getNext().getVal());
        System.out.println(node_two.getNext().getVal());
        System.out.println(node_three.getNext().getVal());

        System.out.println(node_three.getPrevious().getVal());
        System.out.println(node_two.getPrevious().getVal());
        System.out.println(node_one.getPrevious().getVal());


       // list.add(interest_one.toString());
       // list.add(interest_two.toString());
      //  list.add(interest_three.toString());
       // list.add(interest_four.toString());
       // list.add(interest_five.toString());
       // list.add(game_one.toString());
       // list.add(game_two.toString());
       // list.add(game_three.toString());
       // list.add(game_four.toString());
      //  list.add(game_five.toString());




// separate into two parts of code: one for interests and one for games










    }

    private void add(int i, TextView interest_one) {
    }
}
//comment made by carlos at midnight
//Test Comment