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
    localStorage.clear();
  }
}

