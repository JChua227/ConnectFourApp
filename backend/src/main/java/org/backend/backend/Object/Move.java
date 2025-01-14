package org.backend.backend.Object;

public class Move {

    private int [][]gameState;
    private int rating;
    private int x;
    private int y;
    private int winner;

    public Move(int [][]gameState, int rating, int x, int y, int winner){
        this.gameState = gameState;
        this.rating = rating;
        this.x = x;
        this.y = y;
        this.winner = winner;
    }

    public Move(int [][]gameState, int rating, int x, int y){
        this.gameState = gameState;
        this.rating = rating;
        this.x = x;
        this.y = y;
    }

    public Move(int [][]gameState, int x, int y){
        this.gameState = gameState;
        this.x = x;
        this.y = y;
    }

    public Move(int [][]gameState, int rating){
        this.gameState = gameState;
        this.rating = rating;
    }

    public Move(int [][]gameState){
        this.gameState = gameState;
    }

    public Move(int winner){
        this.winner = winner;
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

    public int getX(){
        return this.x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y){
        this.y = y;
    }

}