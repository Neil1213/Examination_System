import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../auth/token-storage.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  paginationControl = false;
  username:string
  role:string


  constructor(
    private tokenService:TokenStorageService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.username = this.tokenService.getUsername()
    this.role = this.tokenService.getAuthorities()[0]
  }

  logout(){
    this.tokenService.signOut()
    this.router.navigate([""])
  }

}
