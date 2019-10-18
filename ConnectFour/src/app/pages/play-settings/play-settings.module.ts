import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { PlaySettingsComponentComponent } from '../../components/play-settings-component/play-settings-component.component';
import { NavbarModule } from '../../components/navbar/navbar.module';

import { IonicModule } from '@ionic/angular';

import { PlaySettingsPage } from './play-settings.page';

const routes: Routes = [
  {
    path: '',
    component: PlaySettingsPage
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
  declarations: [PlaySettingsPage,PlaySettingsComponentComponent]
})
export class PlaySettingsPageModule {}
