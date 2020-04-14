export class ExamQuestion {
  constructor(
    public id:number,
    public category:string,
    public difficulty:string,
    public methodName:string,
    public question:string,
    public points:number 
  ){}
  
}
