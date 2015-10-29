package com.agmis.stalofutbolas;

/**
 * Created by Haroldas on 10/29/2015.
 */
public class Player {

    public Player(){

    }

    private int id;
    private String username;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
