package com.affinity.affinityteam.affinity;

import android.util.Log;


import com.affinity.affinityteam.affinity.Models.Topic;
import com.affinity.affinityteam.affinity.Models.User;

import java.util.ArrayList;

public  class MatchLogic {

     //Requirements: MatchLogic -> Determine possible matches between currentUser and each user in userList
     //Things to determine: affinity points (common topics), isPossibleMatch(?).
     //(isPossibleMatch() : currentUser -> otherUser -> bool) = true if match state is = "possible" else false
     //->match state is "possible" when the match haven't been requested or accepted.

     //(matchingUp: ArrayList<User> userList -> currentUser -> ArrayList<User> possibleMatches)
     //matchingUp returns all posibles matches in an ArrayList<Match>

     public MatchLogic(){}




     private int processData(int delta){ //make a small number more bigger!
        if(delta==0){
            return 100;
        } else {
            return Math.round((1/delta) *100);
        }


     }
 }
