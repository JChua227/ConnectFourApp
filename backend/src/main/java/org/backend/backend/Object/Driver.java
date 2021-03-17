public class Driver {
    public static void main(String [] args){

        /* int[][] array = {
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0}}; */

            int[][] array = {
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}};


        Move move = new Move(array);
        Bot bot = new Bot(move,9);

        Move temp = bot.getDecision();
        System.out.println("Move: " + (temp.getY()+1));
        System.out.println("Rating: " + temp.getRating());

    }

    
}
