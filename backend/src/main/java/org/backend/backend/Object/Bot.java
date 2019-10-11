package org.backend.backend.Object;

import java.util.ArrayList;
import java.util.List;

public class Bot {

    private Move move;
    private int depth;
    private Evaluator evaluator;

    public Bot(Move move, int depth){
        this.move = move;
        this.depth = depth;
        this.evaluator = new Evaluator(move.getGameState().length,move.getGameState()[0].length);
    }

    public Bot(Move move){
        this.move = move;
    }

    public Move getMove(){
        return this.move;
    }

    public int getDepth(){
        return this.depth;
    }

    public Move getDecision(){
        return miniMax(this.move, this.depth, false);
    }


    public Move miniMax(Move move, int depth, boolean turn){
        if(depth==0){
            return evaluatePosition(move);
        }
        if(gameIsFinished(move)){

        }
        int player = 1;
        if(!turn){
            player = 2;
        }
        List<Move> states = possiblePositions(move,player);

        if(turn){
            Move maxEval = new Move(move.getGameState(),-100000);
            for(int x=0; x<states.size(); x++){
                Move evaluation = miniMax(states.get(x),depth-1,!turn);
                if(maxEval.getRating()<evaluation.getRating()){
                    maxEval = evaluation;
                }
            }
            return maxEval;
        }
        else{
            Move minEval = new Move(move.getGameState(),100000);
            for(int x=0; x<states.size(); x++){
                Move evaluation = miniMax(states.get(x),depth-1,!turn);
                if(minEval.getRating()>evaluation.getRating()){
                    minEval = evaluation;
                }
            }
            return minEval;
        }

    }

    //TODO: check if game is finished
    public boolean gameIsFinished(Move move){

        int count = 0;
        for(int x=0; x<move.getGameState().length; x++){
            for(int y=0; y<move.getGameState()[0].length; x++){

            }
        }


        return false;
    }

    //TODO: evaluate each position
    public Move evaluatePosition(Move move){
        int rating = this.evaluator.evaluateState(move);
        return new Move(move.getGameState(),rating,move.getX(),move.getY());
    }


    public List<Move> possiblePositions(Move move,int player){
        List<Move> states = new ArrayList<>();
        for(int y=0; y<move.getGameState()[0].length; y++){
            int x=move.getGameState().length-1;
            boolean columnFilled = false;
            while(move.getGameState()[x][y]!=0){
                x--;
                if(x==-1){
                    columnFilled=true;
                    break;
                }
            }
            if(columnFilled){
                continue;
            }
            int temp[][] = copy(move.getGameState());
            temp[x][y]=player;
            states.add(new Move(temp,x,y));
        }
        return states;
    }


    public int[][] copy(int [][]array){
        int temp[][] = new int[array.length][array[0].length];
        for(int x=0; x<array.length; x++){
            for(int y=0; y<array[0].length; y++){
                temp[x][y] = array[x][y];
            }
        }
        return temp;
    }

}
