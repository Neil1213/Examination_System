import { Component, OnInit } from '@angular/core';
import { StudentsService } from '../service/students.service'

@Component({
  selector: 'app-students',
  templateUrl: './students.component.html',
  styleUrls: ['./students.component.css']
})
export class StudentsComponent implements OnInit {

  Exams:any[]=[]
  Students:any[]=[]
  constructor(
    private studentsService:StudentsService
  ) { }

  ngOnInit(): void {
    this.studentsService.getTests().subscribe(
      response => this.displayExams(response)
    )
  }
  displayExams(response){
    this.Exams = response
  }
  showStudents(examId:number){
    this.studentsService.getStudents(examId).subscribe(
      response => this.displayStudents(response)
    )
  }
  displayStudents(response){
    this.Students = response;
    console.log(this.Students)
  }
}
