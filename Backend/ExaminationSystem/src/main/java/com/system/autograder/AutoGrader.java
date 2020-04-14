package com.system.autograder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.model.Question;
import com.system.model.StudentResponse;
import com.system.model.StudentTestFeedback;
import com.system.model.StudentTestGrades;
import com.system.model.Test;
import com.system.model.Testcases;
import com.system.service.LoggingService;
import com.system.service.QuestionService;
import com.system.service.StudentResponseService;
import com.system.service.StudentTestFeedbackService;
import com.system.service.StudentTestGradesService;
import com.system.service.TestService;

@Service
public class AutoGrader {
	@Autowired private StudentResponseService SRservice;
	@Autowired private TestService Tservice;
	@Autowired private QuestionService Qservice;
	@Autowired private StudentTestFeedbackService STFservice;
	@Autowired private StudentTestGradesService STGservice;
	@Autowired private LoggingService logger;
	private final String className = AutoGrader.class.getName().toString();
	private final boolean matches(String regex, String string) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(string);
		if(matcher.find()) {
			return true;
		}
		return false;
	}
	private final Test getTest(int testId) {
		return Tservice.getTest(testId);
	}
	private final List<Question> getTestCases(int testId){
		Test test = getTest(testId);
		List<Question> questions;
		ArrayList<Question> tempObj = new ArrayList<Question>();
		test.getQuestions().forEach((question) -> {
			tempObj.add(Qservice.getQuestion(question.getQuestionId()));
		});
		questions = tempObj;
		return questions;
	}
	private final void createFile() throws IOException{
		File newFile = new File("python.py");
		newFile.createNewFile();
	}
	private final void writeFile(String studentResponse) throws IOException {
		FileWriter myWriter =  new FileWriter("python.py");
		myWriter.write(studentResponse);
		myWriter.close();
	}
	private final void appendFile(Question question, String studentResponse) throws IOException {
		FileWriter myWriter = new FileWriter("python.py", true);
		if(matches("print", studentResponse)) {
			for(Testcases testcase:question.getTestcases()) {
				myWriter.write("\n");
				myWriter.write(testcase.getTestcase());
				
			}
			myWriter.close();
		}
		if(matches("return", studentResponse)) {
			myWriter.write("\n");
			for(Testcases testcase:question.getTestcases()) {
				myWriter.write("\n");
				myWriter.write("print(");
				myWriter.write(testcase.getTestcase());
				myWriter.write(")");
				System.err.println(testcase.getTestcase());
			}
			myWriter.close();
		}
	}
	private final List<String> compileMe() throws IOException{
		ArrayList<String> tempOutput = new ArrayList<String>();
		String[] cmd = new String[2];
		cmd[0] = "python";
		cmd[1] = "python.py";
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getErrorStream()));
		String line = "";
		while((line = bfr.readLine()) != null) {
			tempOutput.add(line);
		}
		List<String> output = tempOutput;
		return output;
	}
	private final List<String> compileTestCases() throws IOException{
		ArrayList<String> tempOutput = new ArrayList<String>();
		String[] cmd = new String[2];
		cmd[0] = "python";
		cmd[1] = "python.py";
		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmd);
		BufferedReader bfr = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		String line = "";
		while((line = bfr.readLine()) != null) {
			tempOutput.add(line);
		}
		List<String> output = tempOutput;
		return output;
	}
	private final StudentTestFeedback gradeMe(int studentId, int examId, int stage, int maxPoints, String response, Question question, String methodName) throws IOException {
		double points = 0.0;
		double score = 0.0;
		StudentTestFeedback feedback = new StudentTestFeedback();
		String modifiedAnswer = "";
		StringBuilder buildFeedback = new StringBuilder();
		switch(stage) {
		case 0:
			createFile();
			writeFile(response);
			if(compileMe().size() == 0 && response != null) {
				points++;
				buildFeedback.append("Your program compiled!\t====> " + (1.0/10.0)*maxPoints + " pts.\n");
				modifiedAnswer = response;
				score += ((1.0/10.0)*maxPoints);
			}
			else if(matches("(def\\s*)(\\w*\\s*)(\\((\\s*\\w*\\s*,?)*\\))(:)", response) == false) {
				String a=":";
				buildFeedback.append("Missing colon.\t====> 0 pts.\n");
				modifiedAnswer = response.replaceAll("(def\\s*)(\\w*\\s*)(\\((\\s*\\w*\\s*,?)*\\))","$1$2$3"+a);
				writeFile(modifiedAnswer);
			}
			else if(matches("(if\\s*)(\\((\\s*\\w*\\s*>?<?(<=)?(>=)?=?(!=)?)*\\))(:)", response) == false) {
				String a=":";
				buildFeedback.append("Missing colon.\t====> 0 pts.\n");
				modifiedAnswer = response.replaceAll("(if\\s*)(\\((\\s*\\w*\\s*>?<?(<=)?(>=)?=?(!=)?)*\\))","$1$2$3"+a);
				writeFile(modifiedAnswer);

			}else if(matches("(elif\\s*)(\\((\\s*\\w*\\s*>?<?(<=)?(>=)?=?(!=)?)*\\))(:)", response) == false) {
				String a=":";
				buildFeedback.append("Missing colon.\t====> 0 pts.\n");
				modifiedAnswer = response.replaceAll("(if\\s*)(\\((\\s*\\w*\\s*>?<?(<=)?(>=)?=?(!=)?)*\\))","$1$2$3"+a);
				writeFile(modifiedAnswer);
			}
			
			else {
				buildFeedback.append("Your program failed to compile.\t====> 0 pts.\n");
				compileMe().forEach((line) -> {
					buildFeedback.append("\n\t");
					buildFeedback.append(line);
				});
				break;
			}
		case 1:
			if(matches(methodName+"\\s*\\(", response) == false) {
				buildFeedback.append("\nThe function name does not match the requirements.\t====> 0 pts. \n");
				modifiedAnswer = modifiedAnswer.replaceAll("(def\\s*)(\\w*\\s*)(\\(\\s*\\w*\\s*,?\\s*\\w*\\s*\\))", "$1"+methodName+"$3");
				writeFile(modifiedAnswer);
			}
			else {
				points++;
				buildFeedback.append("\nThe function name matches!\t====> " + (1.0/10.0)*maxPoints+" pts.\n");
				modifiedAnswer = response;
				score += ((1.0/10.0)*maxPoints);
			}
		case 3:
			appendFile(question, response);
			List<String> output = compileTestCases();
			double ratio = 0.0;
			double caseRatio = 1.0 / (double)(question.getTestcases().size());
			List<Testcases> testcases = question.getTestcases();
			for(int i = 0; i < testcases.size(); i++) {
				if(output.get(i).equals(testcases.get(i).getAnswer())) {
					buildFeedback.append("\nYour output for " + testcases.get(i).getTestcase() + ": " +
					output.get(i) + "\nCorrect output: " + testcases.get(i).getAnswer() + "\t====> " + 
					(caseRatio * 7.0 / 10.0) * maxPoints*(1.0) + "pts.\n");
					ratio++;
					score += ((caseRatio * 7.0 / 10.0) * maxPoints*(1.0));
				}
				else {
					buildFeedback.append("\nYour output for " + testcases.get(i).getTestcase() + ": " +
					output.get(i) + "\nCorrect output: " + testcases.get(i).getAnswer() + "\t====> 0 pts.\n");
					continue;
				}
			}
			ratio = ratio / (double)output.size();
			points += 7 * ratio;
		}
		points = points / 10.0;
		feedback.setFeedback(buildFeedback.toString());
		feedback.setPoints(score);
		feedback.setQuestionId(question.getId());
		feedback.setStudentId(studentId);
		feedback.setTestId(examId);
		return feedback;
	}
	public final String grader(List<StudentResponse> studentResponse) throws SQLException, IOException{
		logger.log("info", "Autograder initialized", this.className);
		StudentTestGrades grades = new StudentTestGrades();
		double examScore = 0.0;
		logger.log("info", "Getting exam id", this.className);
		int examId = studentResponse.get(0).getTestId();
		logger.log("info", "Getting student id", this.className);
		int studentId = studentResponse.get(0).getStudentId();
		Test test = getTest(examId);
		logger.log("info", "Getting testcases for questions", this.className);
		List<Question> questions = getTestCases(examId);
		ArrayList<StudentTestFeedback> studentFeedback = new ArrayList<StudentTestFeedback>(); 
		for(int i = 0; i < studentResponse.size(); i++) {
			System.out.println("Inside reponses for loop");
			System.out.println("Response id:" + i);
			//maxPoints += test.getPoints();
			StudentTestFeedback feedback = new StudentTestFeedback();
			Question question = questions.get(i);
			logger.log("info", "Grading question", this.className);
			feedback = gradeMe(studentId, examId, 0, test.getQuestions().get(i).getPoints(), studentResponse.get(i).getResponse(), question, question.getMethodName());
			logger.log("info", "Storing the feedback", this.className);
			studentFeedback.add(feedback);
			logger.log("info", "Updating the exam score", this.className);
			examScore += feedback.getPoints();
		}
		List<StudentTestFeedback> studentTestFeedback = studentFeedback;
		STFservice.saveFeedback(studentTestFeedback);
		grades.setUserId(studentId);
		grades.setTestId(examId);
		grades.setGrade(examScore);
		STGservice.insertGrades(grades);
		logger.log("info", "Successfully graded exam", this.className);
		return "Successfully graded exam";
	}
}

