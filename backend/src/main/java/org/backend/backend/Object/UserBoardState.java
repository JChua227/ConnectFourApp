package org.backend.backend.Object;

public class UserBoardState {

    private Move move;
    private int depth;

    public UserBoardState(Move move, int depth){
        this.move = move;
        this.depth = depth;
    }

    public Move getMove(){
        return this.move;
    }

    public int getDepth(){
        return this.depth;
    }
}
