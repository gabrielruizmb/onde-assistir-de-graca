import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserService } from '../services/user.service';

export const routesGuard: CanActivateFn = (route, state) => {

  let userService = inject(UserService);
  let myRouter = inject(Router);

  if (state.url == '/user-profile') {
    if (!userService.getToken()) {
      myRouter.navigate(['/login']);
      return false;
    }
  }

  if (state.url == '/film-form') {
    if (!userService.getToken()) {
      myRouter.navigate(['/login']);
      return false;
    }
  }

  return true;
};
