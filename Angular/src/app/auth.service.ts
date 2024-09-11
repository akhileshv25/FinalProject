import { Injectable } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private userEmail: string | null = null;

  constructor(  private platformId: Object) {
    // Initialize email from localStorage if available
    if (isPlatformBrowser(this.platformId)) {
      this.userEmail = localStorage.getItem('sessionUser');
    }
  }

  setEmail(email: string) {
    this.userEmail = email;
    if (isPlatformBrowser(this.platformId)) {
      localStorage.setItem('sessionUser', email);
    }
  }

  getEmail(): string | null {
    return this.userEmail;
  }
}
