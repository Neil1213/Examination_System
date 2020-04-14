import { Component, OnInit } from '@angular/core';
import { QuestionServiceService, Question, Testcase} from '../service/question-service.service'
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-question-bank',
  templateUrl: './question-bank.component.html',
  styleUrls: ['./question-bank.component.css']
})
export class QuestionBankComponent implements OnInit {
  q: any = {}  
  t: any = [{},{}]
  private question:Question
  questionBank:Question[];
  testCases1;
  testCases2;
  expectedResults1
  expectedResults2
  private notifier:NotifierService
  
  constructor(
    private questionService:QuestionServiceService,
    notifier:NotifierService
  ){
    this.notifier = notifier
  }

  medium(dif){
     this.q.difficulty = dif;
  }
  easy(es){
    this.q.difficulty = es;
  }

  hard(hd){
     this.q.difficulty = hd;
  }
  showNotification(type:string, message:string):void{
    this.notifier.notify(type, message)
  }

  ngOnInit() {
    this.questionService.getQuestionBank().subscribe(
      response => this.handleResponse(response) 
    );
  }  
  createQuestion(): void {
    let testcase = [{'testcase': this.testCases1, 'answer': this.expectedResults1}, 
                      {'testcase': this.testCases1, 'answer': this.expectedResults1}];

    this.question = new Question(this.q.category, this.q.difficulty, this.q.methodName, this.q.question, testcase);
    this.questionService.insertQuestion(this.question).subscribe()
    this.showNotification("success", "Successfully created question!")
  }
  handleResponse(resp){
    this.questionBank = resp;
    console.log(this.questionBank);
  }
}
