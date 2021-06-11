package com.affinity.affinityteam.affinity.Models;
import java.util.ArrayList;
public class AffinityCard {


        String otherUserId;
        int background;
        String username;
        int profilePhoto;
        int affinityScore;

    public AffinityCard(String otherUserId, int background, String username, int profilePhoto, int affinityScore) {
        this.otherUserId = otherUserId;
        this.background = background;
        this.username = username;
        this.profilePhoto = profilePhoto;
        this.affinityScore = affinityScore;
    }

    public String getOtherUserId() {
        return otherUserId;
    }

    public void setOtherUserId(String otherUserId) {
        this.otherUserId = otherUserId;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public int getAffinityScore() {
        return affinityScore;
    }

    public void setAffinityScore(int affinityScore) {
        this.affinityScore = affinityScore;
    }
}


