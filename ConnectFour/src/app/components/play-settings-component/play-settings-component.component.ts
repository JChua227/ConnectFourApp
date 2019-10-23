import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

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
    this.router.navigate(['/connect-four-game']);
  } 
}
