import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/layout/login/login.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { routesGuard } from './services/routes.guard';

export const routes: Routes = [
    {path: "login", component: LoginComponent},
    {path: "", redirectTo: "home", pathMatch: 'full'},
    {path: "home", component: HomeComponent},
    {path: "user-profile", component: UserProfileComponent, canActivate: [routesGuard]}
];
