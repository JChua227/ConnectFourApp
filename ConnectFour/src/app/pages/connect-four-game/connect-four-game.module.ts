import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { ConnectFourGameComponentComponent } from '../../components/connect-four-game-component/connect-four-game-component.component';
import { NavbarModule } from '../../components/navbar/navbar.module';

import { IonicModule } from '@ionic/angular';

import { ConnectFourGamePage } from './connect-four-game.page';

const routes: Routes = [
  {
    path: '',
    component: ConnectFourGamePage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    NavbarModule,
    RouterModule.forChild(routes)
  ],
  declarations: [ConnectFourGamePage,ConnectFourGameComponentComponent]
})
export class ConnectFourGamePageModule {}
