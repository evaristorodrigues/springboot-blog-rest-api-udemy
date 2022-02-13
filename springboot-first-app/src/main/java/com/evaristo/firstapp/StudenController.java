/**
 * 
 */
package com.evaristo.firstapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author evari
 *
 */

@RestController
public class StudenController {

	
	//Return a student
	@GetMapping("/student")
	public Student getStudent() {
		return new Student("Ramesh", "Fadatare");
	}
	
	//Return a list of Students
	@GetMapping("/students")
	public List<Student> getStudents() {
		List<Student> students = new ArrayList<>();

		students.add(new Student("Student 1", "LastName 1"));
		students.add(new Student("Student 2", "LastName 2"));
		students.add(new Student("Student 3", "LastName 3"));
		students.add(new Student("Student 4", "LastName 4"));
		students.add(new Student("Student 5", "LastName 5"));
		students.add(new Student("Student 6", "LastName 6"));
		students.add(new Student("Student 7", "LastName 7"));
		students.add(new Student("Student 8", "LastName 8"));
		return students;
	}
	
	//handle Path Variables
	@GetMapping("/student/{firstName}/{lastName}")
	public Student getStudentPathVariable(@PathVariable(value="firstName") String firstName, 
			                              @PathVariable(value="lastName")String lastName) {
		return new Student(firstName, lastName);
	}
	
	//handle query parameters
	@GetMapping("/student/search")
	public Student studenrQueryParam(@RequestParam(name="firstName")String firstName,
									 @RequestParam(name="lastName")String lastName) {
		return new Student(firstName,lastName);
	}

}
