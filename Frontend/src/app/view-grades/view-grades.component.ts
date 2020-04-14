import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PaginationService } from '../service/pagination.service'
import { TokenStorageService } from '../auth/token-storage.service'
import { ViewGradesService } from '../service/view-grades.service';
import { TakeExamService } from '../service/take-exam.service'
export class StudentGrades{

}
@Component({
  selector: 'app-view-grades',
  templateUrl: './view-grades.component.html',
  styleUrls: ['./view-grades.component.css']
})
export class ViewGradesComponent implements OnInit {
  grade:any[] =[]
  feedback:any[] = []
  response:any[] = []
  userId:number
  username:string 
  role:string
  examId:number
  Exam: any
  questions:any[] = []
  pager: any = {}
  pagedItems: any[] = []
  cPage:number = 0
  constructor(
    private router:Router,
    private tokenService:TokenStorageService,
    private pagerService:PaginationService,
    private route: ActivatedRoute,
    private viewGrades:ViewGradesService,
    private takeExamService:TakeExamService
  ){}
  ngOnInit(): void {
    this.username = this.tokenService.getUsername()
    this.route.queryParams.subscribe(params =>{
      this.examId = parseInt(params.examId)
    })
    this.takeExamService.getUserId(this.username).subscribe(
      response => this.getId(response.id)
    )
    
  }
  getId(response){
    this.userId = response
    console.log(this.userId)
    this.takeExamService.getTest(this.examId).subscribe(
      response => this.getExam(response)
    )
    this.viewGrades.getResponse(this.examId, this.userId).subscribe(
      response => this.saveResponse(response)
    )
    this.viewGrades.getFeedback(this.examId, this.userId).subscribe(
      response => this.saveFeedback(response)
    )
    this.viewGrades.getGrade(this.examId, this.userId).subscribe(
      response => this.saveGrade(response)
    )
  }
  saveResponse(response){
    this.response = response
    console.log(this.response)
  }
  saveFeedback(response){
    this.feedback = response
    console.log(this.feedback)
  }
  saveGrade(response){
    this.grade = response
    console.log(this.grade)
  }
  getExam(response){
    this.Exam = response
    this.questions = this.Exam.questions 
    console.log(this.Exam)
    this.setPage(1)
  }
  setPage(page: number) {
    // get pager object from service
    this.pager = this.pagerService.getPager(this.questions.length, page);
    this.cPage = this.pager.currentPage - 1
    // get current page of items
    this.pagedItems = this.questions.slice(this.pager.startIndex, this.pager.endIndex + 1);
  
 }
 navigate(){
   this.router.navigate(["student/dashboard"])
 }
}
