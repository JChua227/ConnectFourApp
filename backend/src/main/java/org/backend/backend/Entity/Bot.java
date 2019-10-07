package org.backend.backend.Entity;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bot {

    private Move move;
    private int depth;

    public Bot(){

    }


    public Bot(Move move, int depth){
        this.move = move;
        this.depth = depth;
    }


    public Move getDecision(){
        Move move = miniMax(this.move, this.depth, false);
        return move;
    }


    public Move miniMax(Move move, int depth, boolean turn){
        if(depth==0){
            return evaluatePosition();
        }
        if(gameIsFinished(move)){

        }

        List<Move> list = possiblePositions();
        for(int x=0; x<list.size(); x++){

        }

        return new Move();
    }


    public boolean gameIsFinished(Move move){

        int count = 0;
        for(int x=0; x<move.getGameState().length; x++){
            for(int y=0; y<move.getGameState()[0].length; x++){

            }
        }


        return false;
    }


    public Move evaluatePosition(){
        
        return 0;
    }


    public List<Move> possiblePositions(){

    }
}
