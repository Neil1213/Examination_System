import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
export class TestQuestion{
  constructor(
    public points:number,
    public questionId:number
  ){}
}
export class Exam {
  name:string
  points:number
  questions:TestQuestion[]
  constructor(
    name:string,
    points:number,
    questions:TestQuestion[]
  ){
    this.name = name
    this.points = points
    this.questions = questions
  }
}

@Injectable({
  providedIn: 'root'
})
export class CreateExamService {
  constructor(
    private httpClient:HttpClient
  ) {}
  createExam(exam:Exam){
    return this.httpClient.post<Exam>("http://localhost:8080/system/createTest", exam)
  }
}
