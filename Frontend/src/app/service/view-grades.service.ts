import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ViewGradesService {

  constructor(
    private httpClient:HttpClient
  ) { }
  getResponse(examId:number, userId:number){
    return this.httpClient.get<any>("http://localhost:8080/system/getStudentResponse/examId=" + examId +"/userId="+userId)
  }
  getFeedback(examId:number, userId:number){
    return this.httpClient.get<any>("http://localhost:8080/system/retrieveFeedback/examId="+examId+"/userId="+userId)
  }
  getGrade(examId:number, userId:number){
    return this.httpClient.get<any>("http://localhost:8080/system/getGrade/examId="+examId+"/userId="+userId)
  }
}
