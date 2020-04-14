import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier'
import { AssignExamService } from '../service/assign-exam.service'
import { Exam } from '../service/create-exam.service'
import { UserService, User } from '../service/user.service'

@Component({
  selector: 'app-assign-exam',
  templateUrl: './assign-exam.component.html',
  styleUrls: ['./assign-exam.component.css']
})
export class AssignExamComponent implements OnInit {
  releasedExams:Exam[] = []
  students:User[] = []
  exam:Exam
  private notifier: NotifierService
  constructor(
    private assignExamService:AssignExamService,
    private userService:UserService,
    notifier:NotifierService
  ) { 
    this.notifier = notifier
  }

  ngOnInit(): void {
    this.assignExamService.getReleasedExams().subscribe(
      response => this.showReleasedExams(response)
    )
  }
  showReleasedExams(response){
    this.releasedExams = response
  }
  showStudents(response){
    this.students = response
    console.log(this.students)
  }
  getStudents(){
    this.userService.getStudents().subscribe(
      response => this.showStudents(response)
    )
  }
  selectedExam(exam){
    this.exam = exam;
    console.log(this.exam)
  }
}
