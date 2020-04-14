import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier'
import { AuthService } from '../auth/auth.service';
import { SignUpInfo } from '../auth/signup-info';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {};
  signupInfo: SignUpInfo;
  isSignedUp = false;
  isSignUpFailed = false;
  errorMessage = '';
  private notifier:NotifierService
  constructor(
    private authService: AuthService,
    notifier:NotifierService
  ) { 
    this.notifier = notifier
  }

  ngOnInit() { }

  showNotification(type:string, message:string):void{
    this.notifier.notify(type, message)
  }

  onSubmit() {
    console.log(this.form);

    this.signupInfo = new SignUpInfo(
      this.form.username,
      this.form.email,
      this.form.password,
      this.form.role)

    this.authService.signUp(this.signupInfo).subscribe(
      data => {
        console.log(data);
        this.isSignedUp = true;
        this.isSignUpFailed = false;
        this.showNotification("success", "Successfully registered user")
      },
      error => {
        console.log(error);
        this.errorMessage = error.error.message;
        this.isSignUpFailed = true;
        this.showNotification("failure", "User not created")
      }
    );
  }
}

