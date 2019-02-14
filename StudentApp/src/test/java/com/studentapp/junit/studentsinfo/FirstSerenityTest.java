package com.studentapp.junit.studentsinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Manual;
import net.thucydides.core.annotations.Pending;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
public class FirstSerenityTest {

	@Before
	public void initURI() {
		
		RestAssured.baseURI = "http://localhost:8080/student";
	
	}

	@Test
	public void getAllStudents() {

	
		SerenityRest.given().contentType(ContentType.JSON)
		.when()
		.get("/list")
		.then()
		.log()
		.status();
		
	}
	
	@Test
	public void failingTest(){
		SerenityRest.given().contentType(ContentType.JSON)
		.when()
		.get("/list")
		.then()
		.log().all()
		.statusCode(500);
	}
	
	@Pending
	@Test
	public void pendingTest(){
		
	}
	
	@Ignore
	@Test
	public void ignoreTest(){
		
	}
	
	@Test
	public void errorTest(){
		System.out.println("This is an error"+(5/0));
	}
	
	@Test
	public void fileDoesNotExistCompromisedTest() throws FileNotFoundException{
		File file=new File("C:\\Programs\\file.txt");
		FileReader fr=new FileReader(file);
		
	}
	
	@Manual
	@Test
	public void manualTest(){
		
	}
	
	@Title("Test for Getting list of all students")
	@Test
	public void test01() {

		
		SerenityRest.given().contentType(ContentType.JSON)
		.when()
		.get("/list")
		.then()
		.log()
		.status();
		
	}
	
	
}
