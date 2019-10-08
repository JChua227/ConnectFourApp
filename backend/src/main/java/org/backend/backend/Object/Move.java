package org.backend.backend.Object;


public class Move {

    private int [][]gameState;
    private int rating;

    public Move(int [][]gameState, int rating){
        this.gameState = gameState;
        this.rating = rating;
    }

    public Move(int [][]gameState){
        this.gameState = gameState;
    }

    public int[][] getGameState(){
        return this.gameState;
    }

    public int getRating(){
        return this.rating;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
}
