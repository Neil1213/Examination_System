import { Component, OnInit, ChangeDetectionStrategy, Input } from '@angular/core';
import {  ActivatedRoute, Router } from '@angular/router';
import { TakeExamService } from '../service/take-exam.service';
import { PaginationService } from '../service/pagination.service'
import { TokenStorageService } from '../auth/token-storage.service'
export class StudentResponse{
  response:string
  questionId:number
  testId:number
  studentId:number
  constructor(
    response:string,
    questionId:number,
    testId:number,
    studentId:number
  ){
    this.response = response
    this.questionId = questionId
    this.testId = testId
    this.studentId = studentId
  }
}

@Component({
  selector: 'app-take-exam',
  templateUrl: './take-exam.component.html',
  styleUrls: ['./take-exam.component.css'],
})
export class TakeExamComponent implements OnInit {
  response:string
  display:boolean
  userId:number
  username:string 
  role:string
  examId:number
  Exam: any
  questions:any[] = []
  pager: any = {}
  pagedItems: any[] = []
  responses: any[] = []
  cPage:number = 0
  studentResponse:StudentResponse[] = []
  constructor(
    private router:Router,
    private tokenService:TokenStorageService,
    private pagerService:PaginationService,
    private route: ActivatedRoute,
    private takeExamService: TakeExamService
  ) {
  
   }

  ngOnInit(): void {
    this.username = this.tokenService.getUsername()
    this.route.queryParams.subscribe(params =>{
      this.examId = parseInt(params.examId)
    })
    this.takeExamService.getTest(this.examId).subscribe(
      response => this.getExam(response)
    )
    this.takeExamService.getUserId(this.username).subscribe(
      response => this.getId(response.id)
    )
   
  }
  getId(response){
    this.userId = response
  }
  getExam(response){
    this.Exam = response
    this.questions = this.Exam.questions 
    this.setPage(1)
    console.log(this.questions)
  }
  setPage(page: number) {
    // get pager object from service
    this.pager = this.pagerService.getPager(this.questions.length, page);

    // get current page of items
    this.pagedItems = this.questions.slice(this.pager.startIndex, this.pager.endIndex + 1);
    this.cPage = this.pager.currentPage - 1
    if(this.pager.currentPage === this.pager.totalPages){
      this.display=false
    }
    else{
      this.display=true
    }
 }
 addResponses(){
   this.responses.push(this.pagedItems)

 }
 submitExam(){
   for(let data of this.questions){
     this.studentResponse.push(new StudentResponse(data.response, data.questionId, this.Exam.id, this.userId))
   }
   console.log(this.studentResponse)
   this.takeExamService.submitExam(this.studentResponse).subscribe(
     response => this.submitSuccess(response)
   )
   this.router.navigate([])
 }
 submitSuccess(response){
   console.log("success")
   this.router.navigate(["../dashboard"])
 }
}