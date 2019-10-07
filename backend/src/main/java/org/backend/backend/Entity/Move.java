package org.backend.backend.Entity;

import javax.persistence.Entity;

@Entity
public class Move {

    private int [][]gameState;
    private int rating;

    public Move(){

    }

    public Move(int [][]gameState, int rating){
        this.gameState = gameState;
        this.rating = rating;
    }

    public int[][] getGameState(){
        return this.gameState;
    }

    public int getRating(){
        return this.rating;
    }
}
