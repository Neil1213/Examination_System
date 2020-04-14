import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class StudentsService {
  constructor(
    private httpClient:HttpClient
  ) { }
  getTests(){
    return this.httpClient.get<any>("http://localhost:8080/system/getTests")
  }
  getStudents(examId:number){
    return this.httpClient.get<any>("http://localhost:8080/system/getStudents/examId=" + examId)
  }
}
