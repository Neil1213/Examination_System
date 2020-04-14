import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { StudentComponent } from './student/student.component'
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { QuestionServiceService} from '../app/service/question-service.service'
import { httpInterceptorProviders } from './auth/auth.interceptor';
import { HeaderComponent } from './header/header.component';
import { Routes, RouterModule } from '@angular/router';
import { QuestionBankComponent } from './question-bank/question-bank.component';
import { AdminComponent } from './admin/admin.component';
import { DashbordComponent } from './dashbord/dashbord.component';
import { NotifierModule, NotifierOptions } from 'angular-notifier';
import { CreateExamComponent } from './create-exam/create-exam.component';
import { AssignExamComponent } from './assign-exam/assign-exam.component';
import { TakeExamComponent } from './take-exam/take-exam.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { StudentsComponent } from './students/students.component';
import { ViewGradesComponent } from './view-grades/view-grades.component'

const customNotifierOptions: NotifierOptions = {
  position:{
    horizontal:{
      position: 'right',
      distance: 12
    },
    vertical:{
      position: 'top',
      distance: 100,
      gap: 10
    }
  },
  theme: 'material',
  behaviour:{
    autoHide: 5000,
    onClick: 'hide',
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4
  },
  animations:{
    enabled: true,
    show:{
      preset: 'slide',
      speed: 300,
      easing: 'ease'
    },
    hide:{
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50
    },
    shift:{
      speed: 300,
      easing: 'ease'
    },
    overlap: 150
  }
}

const routes: Routes = [
   {path: '', redirectTo:"login", pathMatch:"full"},
   {path: 'login', component:LoginComponent},
   {path: 'signup', component: RegisterComponent},
   {path: 'instructor', component: AdminComponent, 
   children: [
       {path: '', redirectTo:"dashboard", pathMatch: "full"},
       {path: 'create-question', component:QuestionBankComponent},
       {path: 'create-exam' , component:CreateExamComponent},
       {path: 'assign-exam', component:AssignExamComponent},
       {path: 'dashboard', component:DashbordComponent},
       {path: 'students', component:StudentsComponent}
   ]
  },
  {path: 'student', component: StudentComponent,
  children:[
      {path: '', redirectTo:"dashboard", pathMatch:"full"},
      {path: 'dashboard', component:DashbordComponent},
      {path: 'takeExam', component:TakeExamComponent},
      {path: 'viewGrades', component:ViewGradesComponent}   
  ] 
  },
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    QuestionBankComponent,
    AdminComponent,
    DashbordComponent,
    CreateExamComponent,
    AssignExamComponent,
    StudentComponent,
    TakeExamComponent,
    StudentsComponent,
    ViewGradesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    NotifierModule.withConfig(customNotifierOptions),
    NgxPaginationModule
  ],
  providers: [httpInterceptorProviders, QuestionServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
