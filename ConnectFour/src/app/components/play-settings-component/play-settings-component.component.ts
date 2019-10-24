import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';


@Component({
  selector: 'app-play-settings-component',
  templateUrl: './play-settings-component.component.html',
  styleUrls: ['./play-settings-component.component.scss']
})
export class PlaySettingsComponentComponent implements OnInit {

  private level:number;

  constructor(private router:Router) { }

  ngOnInit() {}


  public generateGame(){
    console.log(this.level);
    if(this.level!==undefined){
      this.router.navigate(['/connect-four-game']);
    }
  } 
}
