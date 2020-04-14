import { Component, OnInit } from '@angular/core';
import { QuestionServiceService, Question } from '../service/question-service.service' 
import { ExamQuestion } from './exam-question'
import { NotifierService } from 'angular-notifier'
import { CreateExamService, Exam, TestQuestion } from '../service/create-exam.service'
@Component({
  selector: 'app-create-exam',
  templateUrl: './create-exam.component.html',
  styleUrls: ['./create-exam.component.css']
})
export class CreateExamComponent implements OnInit {
  scoreStatus = "section__score section__score--red"
  isDisabled = true
  examName:string = "New Exam"
  questionBank:Question[]
  questionSelected
  question:ExamQuestion
  exam: ExamQuestion[] = []
  examPoints:number = 0
  Exam:Exam
  private notifier:NotifierService
  constructor(
    private questionBankService:QuestionServiceService,
    private createExamService:CreateExamService,
    notifier:NotifierService
  ) { 
    this.notifier = notifier
  }

  ngOnInit(): void {
    this.questionBankService.getQuestionBank().subscribe(
      response => this.handleResponse(response)
    )
  }
  handleResponse(resp){
    this.questionBank = resp
  }
  checkStatus(){
    let result = false
    this.exam.forEach(function(question){
      if(question.points <= 0){
        result = true
      }
    })
    return result
  }
  updateQuestionPoints(e, question){
    let sum = 0
    this.exam.forEach(function (value){
      if(question.id == value.id){
        value.points = question.points
      }
    }) 
    this.exam.forEach(question=>sum += question.points)
    this.examPoints = sum
    let status = this.checkStatus()
    if(status){
      this.isDisabled = status
      this.scoreStatus = "section__score section__score--red"
    }
    else{
      this.isDisabled = status
      this.scoreStatus = "section__score section__score--green"
    }
  }
  showNotification(type:string, message:string):void{
    this.notifier.notify(type, message)
  }
  createExam(){
    let tQuestion:TestQuestion[] = [] 
    this.exam.forEach(function(question){
      tQuestion.push(new TestQuestion(question.points, question.id))
    })
    this.Exam = new Exam(this.examName, this.examPoints, tQuestion)
    this.createExamService.createExam(this.Exam).subscribe()
    this.showNotification("success", "Successfully created")
  }
  getQuestion(question){
    this.question = new ExamQuestion(question.id, question.category, question.difficulty, question.methodName, question.question,0)
    this.exam.push(this.question)    
  }
}
