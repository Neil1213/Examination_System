import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Exam } from '../service/create-exam.service'

@Injectable({
  providedIn: 'root'
})
export class AssignExamService {
  constructor(
    private httpClient:HttpClient
  ) { }
  getReleasedExams(){
    return this.httpClient.get<Exam[]>("http://localhost:8080/system/getReleasedExams")
  }
}

