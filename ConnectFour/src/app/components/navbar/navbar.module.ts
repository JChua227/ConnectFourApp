import { NgModule } from '@angular/core';
import { NavbarComponent } from './navbar.component';
import { MatButtonModule } from '@angular/material/button';


@NgModule({
    declarations: [NavbarComponent],
    exports: [NavbarComponent],
    imports: [MatButtonModule],
})

export class NavbarModule{}