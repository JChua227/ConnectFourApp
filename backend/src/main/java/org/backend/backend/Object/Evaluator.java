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


}
