package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.TestUtils;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;

@Concurrent(threads="4x")
@UseTestDataFrom("\\testdata\\studentinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateStudentDataDriven extends TestBase{
	
	private String firstName;
	private String lastName;
	private String email;
	private String programme;
	private String course;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName+TestUtils.getRandomValue();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName+TestUtils.getRandomValue();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email+TestUtils.getRandomValue();
	}

	public String getProgramme() {
		return programme;
	}

	public void setProgramme(String programme) {
		this.programme = programme;
	}

	public String getCourses() {
		return course;
	}

	public void setCourses(String course) {
		this.course = course;
	}

	public StudentSerenitySteps getStudentSteps() {
		return studentSteps;
	}

	public void setStudentSteps(StudentSerenitySteps studentSteps) {
		this.studentSteps = studentSteps;
	}
	
	
	@Steps
	StudentSerenitySteps studentSteps;
	
	@Title("Data Driven Test For Adding Multiple Students to the Student App")
	@Test
	public void createMultipleStudents(){
		
		ArrayList<String> courses=new ArrayList<>();
		courses.add(course);
		
		studentSteps.createStudent(firstName, lastName, email, programme, courses)
		.statusCode(201);
	}
}
