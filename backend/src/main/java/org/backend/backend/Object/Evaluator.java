package org.backend.backend.Object;

import java.util.HashMap;
import java.util.Map;

public class Evaluator {

    private int [][]board;

    //column-worth
    private Map<Integer,Integer> columnPriority = new HashMap<>();

    public Evaluator(int rowDimensions, int columnDimensions){
        this.board = new int[rowDimensions][columnDimensions];
        fillColumnPriority(this.board);
    }

    public void fillColumnPriority(int [][]board){
        int center = board.length/2;
        int worthFromCenter = 4;
        columnPriority.put(center,worthFromCenter);

        int assignLeft = center-1;
        int assignRight = center+1;
        while(assignRight!=board.length){
            worthFromCenter--;
            columnPriority.put(assignLeft,worthFromCenter);
            columnPriority.put(assignRight,worthFromCenter);
            assignLeft--;
            assignRight++;
        }
    }

    //TODO: Create different methods and add it to totalValue
    public int evaluateState(Move move){
        int totalValue = 0;
        totalValue += spotsTaken(move.getGameState());
        totalValue += twoOrThreeInARow(move.getGameState());
        return totalValue;
    }

    public int spotsTaken(int [][]state){
        int sumOfAllSpots = 0;

        return sumOfAllSpots;
    }

    //TODO: horizontal 2's, all of vertical, all of diagonals
    public int twoOrThreeInARow(int [][]array){
        int player1 = 1;
        int player2 = 2;
        int openPlay = 0;
        int total = 0;

        //horizontal
        for(int x=0; x<array.length; x++){
            for(int y=0; y<array[0].length-3; y++){
                //3s
                if(array[x][y]==player1 && array[x][y+1]==player1 && array[x][y+2]==player1 && array[x][y+3]==openPlay){
                    total += 6;
                }
                else if(array[x][y]==player1 && array[x][y+1]==player1 && array[x][y+2]==openPlay && array[x][y+3]==player1){
                    total += 6;
                }
                else if(array[x][y]==player1 && array[x][y+1]==openPlay && array[x][y+2]==player1 && array[x][y+3]==player1){
                    total += 6;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player1 && array[x][y+2]==player1 && array[x][y+3]==player1){
                    total += 6;
                }
                else if(array[x][y]==player2 && array[x][y+1]==player2 && array[x][y+2]==player2 && array[x][y+3]==openPlay){
                    total -= 6;
                }
                else if(array[x][y]==player2 && array[x][y+1]==player2 && array[x][y+2]==openPlay && array[x][y+3]==player2){
                    total -= 6;
                }
                else if(array[x][y]==player2 && array[x][y+1]==openPlay && array[x][y+2]==player2 && array[x][y+3]==player2){
                    total -= 6;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player2 && array[x][y+2]==player2 && array[x][y+3]==player2){
                    total -= 6;
                }
                //2s
                if(array[x][y]==player1 && array[x][y+1]==player1 && array[x][y+2]==openPlay && array[x][y+3]==openPlay){
                    total += 3;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player1 && array[x][y+2]==player1 && array[x][y+3]==player1){
                    total += 3;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==openPlay && array[x][y+2]==player1 && array[x][y+3]==player1){
                    total += 3;
                }
                else if(array[x][y]==player2 && array[x][y+1]==player2 && array[x][y+2]==openPlay && array[x][y+3]==openPlay){
                    total -= 3;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player2 && array[x][y+2]==player2 && array[x][y+3]==player2){
                    total -= 3;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==openPlay && array[x][y+2]==player2 && array[x][y+3]==player2){
                    total -= 3;
                }


            }
        }
        return total;
    }


    public boolean gameIsFinished(Move move, int player){
        for (int y = 0; y<move.getGameState()[0].length-3 ; y++){
            for (int x = 0; x<move.getGameState().length; x++){
                if (move.getGameState()[x][y] == player && move.getGameState()[x][y+1] == player && move.getGameState()[x][y+2] == player && move.getGameState()[x][y+3] == player){
                    return true;
                }
            }
        }

        for (int x = 0; x<move.getGameState().length-3 ; x++ ){
            for (int y = 0; y<move.getGameState()[0].length; y++){
                if (move.getGameState()[x][y] == player && move.getGameState()[x+1][y] == player && move.getGameState()[x+2][y] == player && move.getGameState()[x+3][y] == player){
                    return true;
                }
            }
        }

        for (int x=3; x<move.getGameState().length; x++){
            for (int y=0; y<move.getGameState()[0].length-3; y++){
                if (move.getGameState()[x][y] == player && move.getGameState()[x-1][y+1] == player && move.getGameState()[x-2][y+2] == player && move.getGameState()[x-3][y+3] == player){
                    return true;
                }
            }
        }

        for (int x=3; x<move.getGameState().length; x++){
            for (int y=3; y<move.getGameState()[0].length; y++){
                if (move.getGameState()[x][y] == player && move.getGameState()[x-1][y-1] == player && move.getGameState()[x-2][y-2] == player && move.getGameState()[x-3][y-3] == player){
                    return true;
                }
            }
        }
        return false;
    }


}
