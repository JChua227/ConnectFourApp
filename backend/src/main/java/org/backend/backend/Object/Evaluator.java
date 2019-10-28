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


    public int evaluateState(Move move){
        int totalValue = 0;
        totalValue += spotsTaken(move.getGameState());
        totalValue += threeSet(move.getGameState());

        //numberOfAvailableWins could be used to improve future heuristic
        /* totalValue += numberOfAvailableWins(move.getGameState()); */

        //twoSet evaluation seems to worsen decision making
        /*totalValue += twoSet(move.getGameState());*/
        return totalValue;
    }


    public static int numberOfAvailableWins(int [][]array){
        int openPlay = 0;
        int player1 = 1;
        int player2 = 2;
        int total = 0;

        //horizontal
        for(int x=0; x<array.length; x++){
            for(int y=0; y<array[0].length-3; y++){
                if((array[x][y]==player1 || array[x][y]==openPlay) && (array[x][y+1]==player1 || array[x][y+1]==openPlay) && (array[x][y+2]==player1 || array[x][y+2]==openPlay) && (array[x][y+3]==player1 || array[x][y+3]==openPlay)){
                    total += 1;
                }
                if((array[x][y]==player2 || array[x][y]==openPlay) && (array[x][y+1]==player2 || array[x][y+1]==openPlay) && (array[x][y+2]==player2 || array[x][y+2]==openPlay) && (array[x][y+3]==player2 || array[x][y+3]==openPlay)){
                    total -= 1;
                }
            
                
            }
        }

        //vertical
        for(int x=0; x<array.length-3; x++){
            for(int y=0; y<array[0].length; y++){
                if((array[x][y]==player1 || array[x][y]==openPlay) && (array[x+1][y]==player1  || array[x+1][y]==openPlay) && (array[x+2][y]==player1 || array[x+2][y]==openPlay) && (array[x+3][y]==player1 || array[x+3][y]==openPlay)){
                    total += 1;
                }
                if((array[x][y]==player2 || array[x][y]==openPlay) && (array[x+1][y]==player2  || array[x+1][y]==openPlay) && (array[x+2][y]==player2 || array[x+2][y]==openPlay) && (array[x+3][y]==player2 || array[x+3][y]==openPlay)){
                    total -= 1;
                }
            }
        }

        //ascending diagonal
        for (int x=3; x<array.length; x++){
            for (int y=0; y<array[0].length-3; y++){
                if ((array[x][y]==player1 || array[x][y]==openPlay) && (array[x-1][y+1] == player1 || array[x-1][y+1]==openPlay) && (array[x-2][y+2]==player1 || array[x-2][y+3]==openPlay) && (array[x-3][y+3]==player1 || array[x-3][y+3]==openPlay)){
                    total += 1;
                }
                if ((array[x][y]==player2 || array[x][y]==openPlay) && (array[x-1][y+1] == player2 || array[x-1][y+1]==openPlay) && (array[x-2][y+2]==player2 || array[x-2][y+3]==openPlay) && (array[x-3][y+3]==player2 || array[x-3][y+3]==openPlay)){
                    total -= 1;
                }
            }
        }

        //descending diagonal
        for (int x=3; x<array.length; x++){
            for (int y=3; y<array[0].length; y++){
                if ((array[x][y]==player1 || array[x][y]==openPlay) && (array[x-1][y-1]==player1 || array[x-1][y-1]==openPlay) && (array[x-2][y-2]==player1 || array[x-2][y-2]==openPlay) && (array[x-3][y-3]==player1 || array[x-3][y-3]==openPlay)){
                    total +=1;
                }
                if ((array[x][y]==player2 || array[x][y]==openPlay) && (array[x-1][y-1]==player2 || array[x-1][y-1]==openPlay) && (array[x-2][y-2]==player2 || array[x-2][y-2]==openPlay) && (array[x-3][y-3]==player2 || array[x-3][y-3]==openPlay)){
                    total -=1;
                }
            }
        }

        return total;
    }


    public int spotsTaken(int [][]state){
        int sumOfAllSpots = 0;
        for(int x=0; x<state.length; x++) {
            for (int y = 0; y < state[0].length; y++) {
                if(!columnPriority.containsKey(y)){
                    continue;
                }
                if (state[x][y] == 1) {
                    sumOfAllSpots += columnPriority.get(y);
                }
                else if (state[x][y] == 2) {
                    sumOfAllSpots -= columnPriority.get(y);
                }
            }
        }
        return sumOfAllSpots;
    }


    public int twoSet(int [][]array){
        int openPlay = 0;
        int player1 = 1;
        int player2 = 2;
        int total = 0;

        int [][]openWorthPlayer1 = new int[array.length][array[0].length];
        int [][]openWorthPlayer2 = new int[array.length][array[0].length];
        //horizontal
        for(int x=0; x<array.length; x++){
            for(int y=0; y<array[0].length-3; y++){
                if(array[x][y]==player1 && array[x][y+1]==player1 && array[x][y+2]==openPlay && array[x][y+3]==openPlay){
                    openWorthPlayer1[x][y+2]++;
                    openWorthPlayer1[x][y+3]++;
                }
                else if(array[x][y]==player1 && array[x][y+1]==openPlay && array[x][y+2]==player1 && array[x][y+3]==openPlay){
                    openWorthPlayer1[x][y+1]++;
                    openWorthPlayer1[x][y+3]++;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player1 && array[x][y+2]==player1 && array[x][y+3]==openPlay){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x][y+3]++;
                }
                else if(array[x][y]==player1 && array[x][y+1]==openPlay && array[x][y+2]==openPlay && array[x][y+3]==player1){
                    openWorthPlayer1[x][y+1]++;
                    openWorthPlayer1[x][y+2]++;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player1 && array[x][y+2]==openPlay && array[x][y+3]==player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x][y+2]++;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==openPlay && array[x][y+2]==player1 && array[x][y+3]==player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x][y+1]++;
                }
                else if(array[x][y]==player2 && array[x][y+1]==player2 && array[x][y+2]==openPlay && array[x][y+3]==openPlay){
                    openWorthPlayer2[x][y+2]--;
                    openWorthPlayer2[x][y+3]--;
                }
                else if(array[x][y]==player2 && array[x][y+1]==openPlay && array[x][y+2]==player2 && array[x][y+3]==openPlay){
                    openWorthPlayer2[x][y+1]--;
                    openWorthPlayer2[x][y+3]--;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player2 && array[x][y+2]==player2 && array[x][y+3]==openPlay){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x][y+3]--;
                }
                else if(array[x][y]==player2 && array[x][y+1]==openPlay && array[x][y+2]==openPlay && array[x][y+3]==player2){
                    openWorthPlayer2[x][y+1]--;
                    openWorthPlayer2[x][y+2]--;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player2 && array[x][y+2]==openPlay && array[x][y+3]==player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x][y+2]--;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==openPlay && array[x][y+2]==player2 && array[x][y+3]==player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x][y+1]--;
                }

            }
        }

        //vertical
        for(int x=0; x<array.length-3; x++){
            for(int y=0; y<array[0].length; y++){
                if(array[x][y]==player1 && array[x+1][y]==player1 && array[x+2][y]==openPlay && array[x+3][y]==openPlay){
                    openWorthPlayer1[x+2][y]++;
                    openWorthPlayer1[x+3][y]++;
                }
                else if(array[x][y]==player1 && array[x+1][y]==openPlay && array[x+2][y]==player1 && array[x+3][y]==openPlay){
                    openWorthPlayer1[x+1][y]++;
                    openWorthPlayer1[x+3][y]++;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==player1 && array[x+2][y]==player1 && array[x+3][y]==openPlay){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x+3][y]++;
                }
                else if(array[x][y]==player1 && array[x+1][y]==openPlay && array[x+2][y]==openPlay && array[x+3][y]==player1){
                    openWorthPlayer1[x+1][y]++;
                    openWorthPlayer1[x+2][y]++;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==player1 && array[x+2][y]==openPlay && array[x+3][y]==player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x+2][y]++;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==openPlay && array[x+2][y]==player1 && array[x+3][y]==player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x+1][y]++;
                }
                else if(array[x][y]==player2 && array[x+1][y]==player2 && array[x+2][y]==openPlay && array[x+3][y]==openPlay){
                    openWorthPlayer2[x+2][y]--;
                    openWorthPlayer2[x+3][y]--;
                }
                else if(array[x][y]==player2 && array[x+1][y]==openPlay && array[x+2][y]==player2 && array[x+3][y]==openPlay){
                    openWorthPlayer2[x+1][y]--;
                    openWorthPlayer2[x+3][y]--;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==player2 && array[x+2][y]==player2 && array[x+3][y]==openPlay){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x+3][y]--;
                }
                else if(array[x][y]==player2 && array[x+1][y]==openPlay && array[x+2][y]==openPlay && array[x+3][y]==player2){
                    openWorthPlayer2[x+1][y]--;
                    openWorthPlayer2[x+2][y]--;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==player2 && array[x+2][y]==openPlay && array[x+3][y]==player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x+2][y]--;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==openPlay && array[x+2][y]==player2 && array[x+3][y]==player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x+1][y]--;
                }
            }
        }

        //ascending diagonal
        for (int x=3; x<array.length; x++){
            for (int y=0; y<array[0].length-3; y++){
                if (array[x][y] == player1 && array[x-1][y+1] == player1 && array[x-2][y+2] == openPlay && array[x-3][y+3] == openPlay){
                    openWorthPlayer1[x-2][y+2]++;
                    openWorthPlayer1[x-3][y+3]++;
                }
                else if (array[x][y] == player1 && array[x-1][y+1] == openPlay && array[x-2][y+2] == player1 && array[x-3][y+3] == openPlay){
                    openWorthPlayer1[x-1][y+1]++;
                    openWorthPlayer1[x-3][y+3]++;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == player1 && array[x-2][y+2] == player1 && array[x-3][y+3] == openPlay){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x-3][y+3]++;
                }
                else if (array[x][y] == player1 && array[x-1][y+1] == openPlay && array[x-2][y+2] == openPlay && array[x-3][y+3] == player1){
                    openWorthPlayer1[x-1][y+1]++;
                    openWorthPlayer1[x-2][y+2]++;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == player1 && array[x-2][y+2] == openPlay && array[x-3][y+3] == player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x-2][y+2]++;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == openPlay && array[x-2][y+2] == player1 && array[x-3][y+3] == player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x-1][y+1]++;
                }
                else if (array[x][y] == player2 && array[x-1][y+1] == player2 && array[x-2][y+2] == openPlay && array[x-3][y+3] == openPlay){
                    openWorthPlayer2[x-2][y+2]--;
                    openWorthPlayer2[x-3][y+3]--;
                }
                else if (array[x][y] == player2 && array[x-1][y+1] == openPlay && array[x-2][y+2] == player2 && array[x-3][y+3] == openPlay){
                    openWorthPlayer2[x-1][y+1]--;
                    openWorthPlayer2[x-3][y+3]--;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == player2 && array[x-2][y+2] == player2 && array[x-3][y+3] == openPlay){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x-3][y+3]--;
                }
                else if (array[x][y] == player2 && array[x-1][y+1] == openPlay && array[x-2][y+2] == openPlay && array[x-3][y+3] == player2){
                    openWorthPlayer2[x-1][y+1]--;
                    openWorthPlayer2[x-2][y+2]--;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == player2 && array[x-2][y+2] == openPlay && array[x-3][y+3] == player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x-2][y+2]--;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == openPlay && array[x-2][y+2] == player2 && array[x-3][y+3] == player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x-1][y+1]--;
                }
            }
        }

        //descending diagonal
        for (int x=3; x<array.length; x++){
            for (int y=3; y<array[0].length; y++){
                if (array[x][y] == player1 && array[x-1][y-1] == player1 && array[x-2][y-2] == openPlay && array[x-3][y-3] == openPlay){
                    openWorthPlayer1[x-2][y-2]++;
                    openWorthPlayer1[x-3][y-3]++;
                }
                else if (array[x][y] == player1 && array[x-1][y-1] == openPlay && array[x-2][y-2] == player1 && array[x-3][y-3] == openPlay){
                    openWorthPlayer1[x-1][y-1]++;
                    openWorthPlayer1[x-3][y-3]++;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == player1 && array[x-2][y-2] == player1 && array[x-3][y-3] == openPlay){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x-3][y-3]++;
                }
                else if (array[x][y] == player1 && array[x-1][y-1] == openPlay && array[x-2][y-2] == openPlay && array[x-3][y-3] == player1){
                    openWorthPlayer1[x-1][y-1]++;
                    openWorthPlayer1[x-2][y-2]++;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == player1 && array[x-2][y-2] == openPlay && array[x-3][y-3] == player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x-2][y-2]++;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == openPlay && array[x-2][y-2] == player1 && array[x-3][y-3] == player1){
                    openWorthPlayer1[x][y]++;
                    openWorthPlayer1[x-1][y-1]++;
                }
                else if (array[x][y] == player2 && array[x-1][y-1] == player2 && array[x-2][y-2] == openPlay && array[x-3][y-3] == openPlay){
                    openWorthPlayer2[x-2][y-2]--;
                    openWorthPlayer2[x-3][y-3]--;
                }
                else if (array[x][y] == player2 && array[x-1][y-1] == openPlay && array[x-2][y-2] == player2 && array[x-3][y-3] == openPlay){
                    openWorthPlayer2[x-1][y-1]--;
                    openWorthPlayer2[x-3][y-3]--;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == player2 && array[x-2][y-2] == player2 && array[x-3][y-3] == openPlay){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x-3][y-3]--;
                }
                else if (array[x][y] == player2 && array[x-1][y-1] == openPlay && array[x-2][y-2] == openPlay && array[x-3][y-3] == player2){
                    openWorthPlayer2[x-1][y-1]--;
                    openWorthPlayer2[x-2][y-2]--;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == player2 && array[x-2][y-2] == openPlay && array[x-3][y-3] == player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x-2][y-2]--;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == openPlay && array[x-2][y-2] == player2 && array[x-3][y-3] == player2){
                    openWorthPlayer2[x][y]--;
                    openWorthPlayer2[x-1][y-1]--;
                }
            }
        }

        for(int x=0; x<openWorthPlayer1.length; x++){
            for(int y=0; y<openWorthPlayer1[0].length; y++){
                total += openWorthPlayer1[x][y];
                total += openWorthPlayer2[x][y];
            }
        }

        return total;
    }


    public int threeSet(int [][]array){
        int openPlay = 0;
        int player1 = 1;
        int player2 = 2;
        int total = 0;

        int [][]openWorthPlayer1 = new int[array.length][array[0].length];
        int [][]openWorthPlayer2 = new int[array.length][array[0].length];
        //horizontal
        for(int x=0; x<array.length; x++){
            for(int y=0; y<array[0].length-3; y++){
                if(array[x][y]==player1 && array[x][y+1]==player1 && array[x][y+2]==player1 && array[x][y+3]==openPlay){
                    openWorthPlayer1[x][y+3] = 6;
                }
                else if(array[x][y]==player1 && array[x][y+1]==player1 && array[x][y+2]==openPlay && array[x][y+3]==player1){
                    openWorthPlayer1[x][y+2] = 6;
                }
                else if(array[x][y]==player1 && array[x][y+1]==openPlay && array[x][y+2]==player1 && array[x][y+3]==player1){
                    openWorthPlayer1[x][y+1] = 6;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player1 && array[x][y+2]==player1 && array[x][y+3]==player1){
                    openWorthPlayer1[x][y] = 6;
                }
                else if(array[x][y]==player2 && array[x][y+1]==player2 && array[x][y+2]==player2 && array[x][y+3]==openPlay){
                    openWorthPlayer2[x][y+3] = -6;
                }
                else if(array[x][y]==player2 && array[x][y+1]==player2 && array[x][y+2]==openPlay && array[x][y+3]==player2){
                    openWorthPlayer2[x][y+2] = -6;
                }
                else if(array[x][y]==player2 && array[x][y+1]==openPlay && array[x][y+2]==player2 && array[x][y+3]==player2){
                    openWorthPlayer2[x][y+1] = -6;
                }
                else if(array[x][y]==openPlay && array[x][y+1]==player2 && array[x][y+2]==player2 && array[x][y+3]==player2){
                    openWorthPlayer2[x][y] = -6;
                }

            }
        }

        //vertical
        for(int x=0; x<array.length-3; x++){
            for(int y=0; y<array[0].length; y++){
                if(array[x][y]==player1 && array[x+1][y]==player1 && array[x+2][y]==player1 && array[x+3][y]==openPlay){
                    openWorthPlayer1[x+3][y] = 6;
                }
                else if(array[x][y]==player1 && array[x+1][y]==player1 && array[x+2][y]==openPlay && array[x+3][y]==player1){
                    openWorthPlayer1[x+2][y] = 6;
                }
                else if(array[x][y]==player1 && array[x+1][y]==openPlay && array[x+2][y]==player1 && array[x+3][y]==player1){
                    openWorthPlayer1[x+1][y] = 6;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==player1 && array[x+2][y]==player1 && array[x+3][y]==player1){
                    openWorthPlayer1[x][y] = 6;
                }
                else if(array[x][y]==player2 && array[x+1][y]==player2 && array[x+2][y]==player2 && array[x+3][y]==openPlay){
                    openWorthPlayer2[x+3][y] = -6;
                }
                else if(array[x][y]==player2 && array[x+1][y]==player2 && array[x+2][y]==openPlay && array[x+3][y]==player2){
                    openWorthPlayer2[x+2][y] = -6;
                }
                else if(array[x][y]==player2 && array[x+1][y]==openPlay && array[x+2][y]==player2 && array[x+3][y]==player2){
                    openWorthPlayer2[x+1][y] = -6;
                }
                else if(array[x][y]==openPlay && array[x+1][y]==player2 && array[x+2][y]==player2 && array[x+3][y]==player2){
                    openWorthPlayer2[x][y] = -6;
                }
            }
        }

        //ascending diagonal
        for (int x=3; x<array.length; x++){
            for (int y=0; y<array[0].length-3; y++){
                if (array[x][y] == player1 && array[x-1][y+1] == player1 && array[x-2][y+2] == player1 && array[x-3][y+3] == openPlay){
                    openWorthPlayer1[x-3][y+3] = 6;
                }
                else if (array[x][y] == player1 && array[x-1][y+1] == player1 && array[x-2][y+2] == openPlay && array[x-3][y+3] == player1){
                    openWorthPlayer1[x-2][y+2] = 6;
                }
                else if (array[x][y] == player1 && array[x-1][y+1] == openPlay && array[x-2][y+2] == player1 && array[x-3][y+3] == player1){
                    openWorthPlayer1[x-1][y+1] = 6;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == player1 && array[x-2][y+2] == player1 && array[x-3][y+3] == player1){
                    openWorthPlayer1[x][y] = 6;
                }
                else if (array[x][y] == player2 && array[x-1][y+1] == player2 && array[x-2][y+2] == player2 && array[x-3][y+3] == openPlay){
                    openWorthPlayer2[x-3][y+3] = -6;
                }
                else if (array[x][y] == player2 && array[x-1][y+1] == player2 && array[x-2][y+2] == openPlay && array[x-3][y+3] == player2){
                    openWorthPlayer2[x-2][y+2] = -6;
                }
                else if (array[x][y] == player2 && array[x-1][y+1] == openPlay && array[x-2][y+2] == player2 && array[x-3][y+3] == player2){
                    openWorthPlayer2[x-1][y+1] = -6;
                }
                else if (array[x][y] == openPlay && array[x-1][y+1] == player2 && array[x-2][y+2] == player2 && array[x-3][y+3] == player2){
                    openWorthPlayer2[x][y] = -6;
                }
            }
        }

        //descending diagonal
        for (int x=3; x<array.length; x++){
            for (int y=3; y<array[0].length; y++){
                if (array[x][y] == player1 && array[x-1][y-1] == player1 && array[x-2][y-2] == player1 && array[x-3][y-3] == openPlay){
                    openWorthPlayer1[x-3][y-3] = 6;
                }
                else if (array[x][y] == player1 && array[x-1][y-1] == player1 && array[x-2][y-2] == openPlay && array[x-3][y-3] == player1){
                    openWorthPlayer1[x-2][y-2] = 6;
                }
                else if (array[x][y] == player1 && array[x-1][y-1] == openPlay && array[x-2][y-2] == player1 && array[x-3][y-3] == player1){
                    openWorthPlayer1[x-1][y-1] = 6;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == player1 && array[x-2][y-2] == player1 && array[x-3][y-3] == player1){
                    openWorthPlayer1[x][y] = 6;
                }
                else if (array[x][y] == player2 && array[x-1][y-1] == player2 && array[x-2][y-2] == player2 && array[x-3][y-3] == openPlay){
                    openWorthPlayer2[x-3][y-3] = -6;
                }
                else if (array[x][y] == player2 && array[x-1][y-1] == player2 && array[x-2][y-2] == openPlay && array[x-3][y-3] == player2){
                    openWorthPlayer2[x-2][y-2] = -6;
                }
                else if (array[x][y] == player2 && array[x-1][y-1] == openPlay && array[x-2][y-2] == player2 && array[x-3][y-3] == player2){
                    openWorthPlayer2[x-1][y-1] = -6;
                }
                else if (array[x][y] == openPlay && array[x-1][y-1] == player2 && array[x-2][y-2] == player2 && array[x-3][y-3] == player2){
                    openWorthPlayer2[x][y] = -6;
                }
            }
        }

        for(int x=0; x<openWorthPlayer1.length; x++){
            for(int y=0; y<openWorthPlayer1[0].length; y++){
                total += openWorthPlayer1[x][y];
                total += openWorthPlayer2[x][y];
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
