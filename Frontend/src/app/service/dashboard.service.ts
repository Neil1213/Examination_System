import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Exam } from '../service/create-exam.service'
import { TokenStorageService } from '../auth/token-storage.service';
export class pastExam{

}
export class AvailableExam{
  name:string
  id:number
  points:number
  constructor(
    name:string,
    id:number,
    points:number
  ){
    this.name = name
    this.id = id
    this.points = points
  }
}
@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  username:string
  constructor(
    private httpClient:HttpClient,
    private tokenService:TokenStorageService
  ) { }
  getUnreleasedExams(){
    return this.httpClient.get<Exam[]>("http://localhost:8080/system/getUnreleasedExams")
  }
  getReleasedExams(){
    return this.httpClient.get<Exam[]>("http://localhost:8080/system/getReleasedExams")
  }
  getAvailableExams(){
    return this.httpClient.get<AvailableExam[]>("http://localhost:8080/system/getAvailableExams/username=" + this.getUserName())
  }
  getPastExams(){
    return this.httpClient.get<AvailableExam[]>("http://localhost:8080/system/getPastExams/username=" + this.getUserName())
  }
  releaseExam(examId:number){
    return this.httpClient.get("http://localhost:8080/system/releaseExam/examId="+examId)
  }
  unreleaseExam(examId:number){
    return this.httpClient.get("http://localhost:8080/system/unreleaseExam/examId="+examId)
  }
  getUserName(){
    this.username = this.tokenService.getUsername();
    return this.username
  }
}
