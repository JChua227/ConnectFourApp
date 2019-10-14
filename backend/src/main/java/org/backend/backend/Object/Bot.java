package org.backend.backend.Object;


import java.util.ArrayList;
import java.util.List;

public class Bot {

    private Move move;
    private int depth;
    private Evaluator evaluator;

    public Bot(Move move, int depth){
        this.move = move;
        if(depth<=0){
            this.depth = 1;
        }
        else {
            this.depth = depth;
        }
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
        if(evaluator.gameIsFinished(this.move,1)){
            System.out.println("Player1 wins!");
            return new Move(true);
        }
        else if(evaluator.gameIsFinished(this.move,3)){
            System.out.println("Player2 wins!");
            return new Move(false);
        }

        boolean playerTurn = checkPlayerTurn(this.move.getGameState());
        System.out.println(playerTurn);
        return miniMax(this.move, this.depth, playerTurn,0,0);
    }

    public boolean checkPlayerTurn(int [][]array){
        int countOfTurns = 0;
        for(int x=0; x<array.length; x++){
            for(int y=0; y<array[0].length; y++){
                if(array[x][y]==1){
                    countOfTurns++;
                }
                else if(array[x][y]==2){
                    countOfTurns--;
                }
            }
        }
        return countOfTurns>0?false:true;
    }

    public Move miniMax(Move move, int depth, boolean turn, int childX, int childY){
        int player = 1;
        if(!turn){
            player = 2;
        }

        if(evaluator.gameIsFinished(move,1)){
            return new Move(move.getGameState(),100000,childX,childY);
        }
        else if(evaluator.gameIsFinished(move,2)){
            return new Move(move.getGameState(),-100000,childX,childY);
        }
        else if(depth==0){
            return evaluatePosition(move,childX,childY);
        }

        List<Move> states = possiblePositions(move,player);

        if(turn){
            Move maxEval = new Move(move.getGameState(),-999999999);
            for(int x=0; x<states.size(); x++){
                if(depth==this.depth){
                    childX = states.get(x).getX();
                    childY = states.get(x).getY();
                }
                Move evaluation = miniMax(states.get(x),depth-1,!turn,childX,childY);
                if(maxEval.getRating()<evaluation.getRating()){
                    maxEval = evaluation;
                }
            }
            return maxEval;
        }
        else{
            Move minEval = new Move(move.getGameState(),999999999);
            for(int x=0; x<states.size(); x++){
                if(depth==this.depth){
                    childX = states.get(x).getX();
                    childY = states.get(x).getY();
                }
                Move evaluation = miniMax(states.get(x),depth-1,!turn,childX,childY);
                if(minEval.getRating()>evaluation.getRating()){
                    minEval = evaluation;
                }
            }
            return minEval;
        }

    }

    //TODO: evaluate each position
    public Move evaluatePosition(Move move, int childX, int childY){
        int rating = this.evaluator.evaluateState(move);
        return new Move(move.getGameState(),rating,childX,childY);
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
