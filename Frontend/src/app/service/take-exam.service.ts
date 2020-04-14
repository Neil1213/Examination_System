import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class TakeExamService {
  exam:any
  constructor(
    private httpClient:HttpClient
  ) { }
  getTest(id:number){
    return this.httpClient.get<any>("http://localhost:8080/system/getTest/id=" + id)
  }
  getUserId(username:string){
    return this.httpClient.get<any>('http://localhost:8080/system/getUser/username='+username)
  }
  submitExam(responses){
    return this.httpClient.post<any>('http://localhost:8080/system/submitExam', responses)
  }
}
