import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { NavbarModule } from '../../components/navbar/navbar.module';

import { IonicModule } from '@ionic/angular';

import { PlaySettingsPage } from './play-settings.page';
import { PlaySettingsComponentComponent } from '../../components/play-settings-component/play-settings-component.component';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { ReactiveFormsModule } from '@angular/forms';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';

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
    MatButtonModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatCheckboxModule,
    MatRadioModule,
    RouterModule.forChild(routes)
  ],
  declarations: [PlaySettingsPage,PlaySettingsComponentComponent]
})
export class PlaySettingsPageModule {}
