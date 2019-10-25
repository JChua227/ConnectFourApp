import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormControl } from '@angular/forms';


@Component({
  selector: 'app-play-settings-component',
  templateUrl: './play-settings-component.component.html',
  styleUrls: ['./play-settings-component.component.scss']
})
export class PlaySettingsComponentComponent implements OnInit {

  private level:number;
  private disableSelect = new FormControl(false);
  private row:number;
  private column:number;
  private standardGame:boolean = false;
  private player:number = 1;

  constructor(private router:Router) { }

  ngOnInit() {}

  public setStandardGame(){
    this.standardGame = !this.standardGame;
    if(!this.standardGame){
      this.row=undefined;
      this.column=undefined;
    }
  }

  public generateGame(){
    if(this.level!==undefined){
      if(this.standardGame){
      this.row = 6;
      this.column = 7;
      }
    }
    if(this.level!==undefined && this.row!==undefined && this.column!==undefined){
      this.router.navigate(['/connect-four-game']);
    }
  } 

  public switchPlayer(player){
    this.player = player;
  }
}
