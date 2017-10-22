import { Component, OnInit } from '@angular/core';
import {userInfo} from 'os';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  deAuthenticateUser() {
    // When you get success response from API:  Navigate to welcome
    localStorage.clear();
    // else .. navigate to login page ~ issue alert login failed!
  }
}

