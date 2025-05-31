import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { UserService } from '../services/user.service';

export const routesGuard: CanActivateFn = (route, state) => {

  let userService = inject(UserService);
  let myRouter = inject(Router);

  if (state.url == '/user-profile' || 
    state.url == '/film-form/post/new' || 
    state.url == '/channel-form/post/new') {
    
    if (!userService.getToken()) {
      myRouter.navigate(['/login']);
      return false;
    } 

    let payload = userService.getTokenPayload();
    
    if (payload.exp) {
      const expirationDate = new Date(payload.exp * 1000);
      const now = new Date();
      
      console.log(now);
      console.log(expirationDate);

      if (now > expirationDate) {
        myRouter.navigate(['/login']);
        return false;
      }
    }
  
  }

  return true;
}
