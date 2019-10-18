import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponentComponent } from '../../components/about-component/about-component.component';
import { NavbarModule } from '../../components/navbar/navbar.module';


import { IonicModule } from '@ionic/angular';

import { AboutPage } from './about.page';

const routes: Routes = [
  {
    path: '',
    component: AboutPage
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
  declarations: [AboutPage,AboutComponentComponent]
})
export class AboutPageModule {}
