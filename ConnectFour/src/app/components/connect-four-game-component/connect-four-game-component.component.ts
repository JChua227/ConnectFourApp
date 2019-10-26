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

  private tempColumn;
  

  constructor(private cookieService:CookieService) { 
    this.board = new Array();
    this.row = parseInt(this.cookieService.get('row'));
    this.column = parseInt(this.cookieService.get('column'));
    this.createBoard();
  }

  ngOnInit() {
    
  }

  public createBoard(){
    this.tempColumn= new Array(this.column).fill(0);

    for(let x=0; x<this.row; x++){
      this.board.push(this.tempColumn);
    }
  }

  public insertColumn(x,y){
    console.log(x + " " + y);
  }

}
