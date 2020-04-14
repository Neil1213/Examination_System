import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 
export class Testcase{
  constructor(
    public testcase:string,
    public answer:string
  ){}
}
 export class Question {
   category:string
   difficulty:string
   methodName:string
   question:string
   testcases:Testcase[]
  constructor(
    category:string,
    difficulty:string,
    methodName:string,
    question:string,
    testcases:Testcase[]
  ){
    this.category = category
    this.difficulty = difficulty
    this.methodName = methodName
    this.question = question
    this.testcases = testcases
  }
}

@Injectable({
  providedIn: 'root'
})

export class QuestionServiceService {
  constructor(
    private httpClient:HttpClient
    
  ){}
   insertQuestion(question:Question){
    return this.httpClient.post<Question>("http://localhost:8080/system/insertQuestion", question)
  }
   getQuestionBank(){
    return this.httpClient.get<Question[]>("http://localhost:8080/system/showQuestionBank")
  }
}
