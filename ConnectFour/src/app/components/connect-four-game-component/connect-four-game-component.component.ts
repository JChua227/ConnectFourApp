import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-connect-four-game-component',
  templateUrl: './connect-four-game-component.component.html',
  styleUrls: ['./connect-four-game-component.component.scss'],
})
export class ConnectFourGameComponentComponent implements OnInit {

  private board:number[][];
  private row:number;
  private column:number;

  constructor(private cookieService:CookieService) { }

  ngOnInit() {
    
    this.row = parseInt(this.cookieService.get('row'));
    this.column = parseInt(this.cookieService.get('column'));
  }

}
