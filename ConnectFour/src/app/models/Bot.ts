import { Move } from './Move';

export class Bot{
    private move:Move;
    private depth:number;

    public Bot(move:Move, depth:number){
        this.move = move;
        this.depth = depth;
    }
}