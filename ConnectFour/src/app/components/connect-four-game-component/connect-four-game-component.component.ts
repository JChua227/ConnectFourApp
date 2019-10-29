import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { templateJitUrl } from '@angular/compiler';

@Component({
  selector: 'app-connect-four-game-component',
  templateUrl: './connect-four-game-component.component.html',
  styleUrls: ['./connect-four-game-component.component.scss'],
})
export class ConnectFourGameComponentComponent implements OnInit {

  private board:number[][];
  private row:number;
  private column:number;
  private playerTurn:boolean = true;

  constructor(private cookieService:CookieService) { 
    
  }

  ngOnInit() {
    this.row = parseInt(this.cookieService.get('row'));
    this.column = parseInt(this.cookieService.get('column'));
    this.fillBoard();
  }

  public fillBoard(){
    let tempBoard = new Array();
    for(let x=0; x<this.row; x++){
      let tempColumn = new Array(this.column);
      for(let x=0; x<this.column; x++){
        tempColumn[x]=0;
      }
      tempBoard.push(tempColumn);
    }
  
    this.board = tempBoard;
  }

  public insertColumn(x:number,y:number){
    console.log(x + " " + y);
    
    let counter=this.board.length-1;
    for(let row=this.board.length-1; row>-1; row--){
      counter=row;
      if(this.board[row][y]==0){
        break;
      }
    }

    if(this.board[counter][y]!=0){
      return;
    }
    
    if(this.playerTurn){
      this.board[counter][y] = 1;
      document.getElementById(counter + '-' + y).innerHTML = 1 + '';
      console.log(counter + ' ' + y);
    }
    else{
      this.board[counter][y] = 2;
      document.getElementById(counter + '-' + y).innerHTML = 2 + '';
      console.log(counter + ' ' + y);
    }

    console.log(this.board);
    this.playerTurn = !this.playerTurn;
  }

}
