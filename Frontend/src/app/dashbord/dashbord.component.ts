import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier'
import { DashboardService, AvailableExam, pastExam } from '../service/dashboard.service'
import { Exam } from '../service/create-exam.service'
import { TokenStorageService } from '../auth/token-storage.service'
import { ViewGradesService } from '../service/view-grades.service'
import { TakeExamService } from '../service/take-exam.service'
@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.css']
})

export class DashbordComponent implements OnInit {
  grade:any[] = []
  unreleasedExams:Exam[] = []
  releasedExams:Exam[] = []
  availableExams:AvailableExam[] = []
  pastExams:AvailableExam[] = []
  instructor:boolean
  student:boolean
  username:string
  userId:number
  private notifier:NotifierService
  constructor(
    private takeExam:TakeExamService,
    private viewGrades:ViewGradesService,
    private dashboardService:DashboardService,
    notifier:NotifierService,
    private tokenService:TokenStorageService
  ) {
    this.notifier = notifier
   }

  ngOnInit(): void {
    this.dashboardService.getReleasedExams().subscribe(
      response => this.showReleasedExams(response)
    )
    this.dashboardService.getUnreleasedExams().subscribe(
      response => this.showUnreleasedExams(response)
    )
    if(this.tokenService.getAuthorities()[0] == "ROLE_STUDENT"){
      this.student = true
    }
    this.dashboardService.getAvailableExams().subscribe(
      response => this.showAvailableExams(response)
    )
    
    this.dashboardService.getPastExams().subscribe(
      response => this.showPastExams(response)
    )
    this.username = this.tokenService.getUsername()
    this.takeExam.getUserId(this.username).subscribe(
      response => this.getUserId(response)
    )
  }
  getUserId(response){
    this.userId = response
  }
  showAvailableExams(response){
    this.availableExams = response
    console.log(this.availableExams)
  }
  showPastExams(response){
    this.pastExams = response
  }
  showReleasedExams(response){
    this.releasedExams = response
  }
  showUnreleasedExams(response){
    this.unreleasedExams = response
  }
  showNotification(type:string, message:string):void{
    this.notifier.notify(type, message)
  }
  releaseExam(exam){
    let examId = exam.id
    this.dashboardService.releaseExam(examId).subscribe()
    window.location.reload()
    this.showNotification("success", "Dashboard view successfully updated")
  }
  unreleaseExam(exam){
    let examId = exam.id
    this.dashboardService.unreleaseExam(examId).subscribe()
    window.location.reload()
    this.showNotification("success", "Dashboard view successfully updated")
  }
}
