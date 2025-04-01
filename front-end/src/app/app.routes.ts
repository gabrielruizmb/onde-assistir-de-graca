import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/layout/login/login.component';

export const routes: Routes = [
    {path: "login", component: LoginComponent},
    {path: "", redirectTo: "home", pathMatch: 'full'},
    {path: "home", component: HomeComponent}
];
