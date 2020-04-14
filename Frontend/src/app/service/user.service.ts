import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
export class User {
  id:number
  username:string
  password:string
  email:string
  role: string[]
  constructor(
    id:number,
    username:string,
    password:string,
    email:string,
    role: string[]
  ){
    this.id = id
    this.username = username
    this.password = password
    this.email = email
    this.role = role
  }
}
@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(
    private httpClient:HttpClient
  ) {}
  getStudents(){
    return this.httpClient.get<User[]>('http://localhost:8080/system/getUsers/role=ROLE_STUDENT')
  }
}
