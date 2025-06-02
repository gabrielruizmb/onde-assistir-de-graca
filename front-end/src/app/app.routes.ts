import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/layout/login/login.component';
import { UserProfileComponent } from './components/user/user-profile/user-profile.component';
import { routesGuard } from './guards/routes.guard';
import { FilmDetailsComponent } from './components/films/film-details/film-details.component';
import { FilmFormComponent } from './components/films/film-form/film-form.component';
import { ChannelFormComponent } from './components/channels/channel-form/channel-form.component';
import { CategoryFormComponent } from './components/categories/category-form/category-form.component';

export const routes: Routes = [
    {path: "login", component: LoginComponent},
    {path: "", redirectTo: "home", pathMatch: 'full'},
    {path: "home", component: HomeComponent},
    {path: "film-details/:id", component: FilmDetailsComponent},
    {path: "film-form/:action/:id", component: FilmFormComponent, canActivate: [routesGuard]},
    {path: "user-profile", component: UserProfileComponent, canActivate: [routesGuard]},
    {path: "channel-form/:action/:id", component: ChannelFormComponent, canActivate: [routesGuard]},
    {path: "category-form/:action/:id", component: CategoryFormComponent, canActivate: [routesGuard]}
];
