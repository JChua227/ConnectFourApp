import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  constructor(private router:Router) {}


  public goToPlaySettings(){
    this.router.navigate(['/play-settings']);
  }

  public goToSettings(){
    this.router.navigate(['/settings']);
  }

  public goToAbout(){
    this.router.navigate(['/about']);
  }
}
