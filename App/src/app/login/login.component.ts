import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  authenticateUser(username, password) {
    // When you get success response from API:  Navigate to welcome
    localStorage.setItem('userid', username);
    // else .. navigate to login page ~ issue alert login failed!
  }
}
