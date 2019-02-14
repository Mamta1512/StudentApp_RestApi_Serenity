package com.studentapp.junit.studentsinfo;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.model.StudentClass;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReuseableSpecifications;
import com.studentapp.utils.TestUtils;

import io.restassured.http.ContentType;
import java.util.*;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCrudTest extends TestBase {

	static String firstName = "Mamta" + TestUtils.getRandomValue();
	static String lastName = "Alane" + TestUtils.getRandomValue();
	static String programme = "ComputerScience";
	static String email = TestUtils.getRandomValue() + "alanemamta@gmail.com";
	static int studentId;

	@Steps
	StudentSerenitySteps steps;

	@Title("This test will create a new student")
	@Test
	public void Test001_createStudent() {

		ArrayList<String> courses = new ArrayList<>();
		courses.add("CPP");
		courses.add("Java");
		/*
		 * StudentClass student = new StudentClass();
		 * student.setFirstName(firstName); student.setLastName(lastName);
		 * student.setProgramme(programme); student.setEmail(email);
		 * student.setCourses(courses);
		 * 
		 * SerenityRest.rest().given().contentType(ContentType.JSON).log().all()
		 * .when().body(student).post().then().log() .all().statusCode(201);
		 */

		steps.createStudent(firstName, lastName, email, programme, courses).statusCode(201)
		.spec(ReuseableSpecifications.getGenericResponseSpec());
	}

	@Title("Student was already added to the application")
	@Test
	public void Test002_getStudent() {

		/*
		 * String p1 = "findAll{it.firstName=='"; String p2 = "'}.get(0)";
		 */

		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		/*
		 * SerenityRest.rest().given().when().get("/list").then().log().all()
		 * .statusCode(200).extract().path(p1 + firstName + p2);
		 */

		// System.out.println("Extracted value is" + value);

		assertThat(value, hasValue(firstName));
		studentId = (Integer) value.get("id");
	}

	@Title("Updated the student information and verified the same information")
	@Test
	public void Test003_updateStudentAndVerifyInfo() {

		String p1 = "findAll{it.firstName=='";
		String p2 = "'}.get(0)";

		ArrayList<String> courses = new ArrayList<>();
		courses.add("CPP");
		courses.add("Java");

		firstName = firstName + "_updated";
		/*
		 * StudentClass student = new StudentClass();
		 * student.setFirstName(firstName); student.setLastName(lastName);
		 * student.setProgramme(programme); student.setEmail(email);
		 * student.setCourses(courses);
		 * 
		 * SerenityRest.rest().given().contentType(ContentType.JSON).log().all()
		 * .when().body(student).put("/" + studentId) .then().log().all();
		 */

		steps.updateStudent(studentId, firstName, lastName, email, programme, courses);

		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		/*
		 * SerenityRest.rest().given().when().get("/list").then().log().all()
		 * .statusCode(200).extract().path(p1 + firstName + p2);
		 */

		// System.out.println("Extracted value is" + value);

		assertThat(value, hasValue(firstName));
		studentId = (Integer) value.get("id");
	}

	@Title("Delete the student and verify that student is deleted")
	@Test
	public void Test004_deleteStudentAndVerifyInfo() {
		// SerenityRest.rest().given().when().delete("/" + studentId);
		// SerenityRest.rest().given().when().get("/" +
		// studentId).then().log().all().statusCode(404);
		steps.deleteStudent(studentId);
		steps.getStudentById(studentId).statusCode(404);
	}
}
