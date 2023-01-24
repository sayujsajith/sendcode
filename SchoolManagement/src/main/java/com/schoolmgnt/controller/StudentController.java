package com.schoolmgnt.controller;

import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.schoolmgnt.dto.StudentDTO;
import com.schoolmgnt.entity.Student;
import com.schoolmgnt.exception.StudentNotFoundException;
import com.schoolmgnt.serviceImp.StudentServiceImp;

@RestController
public class StudentController 
{
	@Autowired
	StudentServiceImp studentSerImp;
	
	@PostMapping("/student")
	public ResponseEntity<Student> insertStudent(@RequestBody @Valid StudentDTO studentDTO)
	{
		try {
				Student std=studentSerImp.insertStudent(studentDTO);
				if (std!=null)
				{
					return new ResponseEntity<>(studentSerImp.insertStudent(studentDTO),HttpStatus.CREATED);
				}
		}
		catch(Exception e)
		{
			throw new StudentNotFoundException("Student data not inserted ! please enter the valid input");
			
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/student/details/{studentId}")
	public ResponseEntity<Student> getDetailsById(@PathVariable("studentId")int studentId )
	{
		try
		{
			Student i=studentSerImp.getDetailsById(studentId);
			if(i!=null)
			{
				return new ResponseEntity<>(studentSerImp.getDetailsById(studentId),HttpStatus.FOUND);
			}
		}
		catch(NoSuchElementException e)
		{
			throw new NoSuchElementException("Student details not Found ! please enter the valid input");
		}
//		catch(StudentNotFoundException e)
//		{
//			throw new StudentNotFoundException("Student data not Found ! please enter the valid input");
//		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@DeleteMapping("/student/delete/{studentId}")
	public ResponseEntity<Student> deleteId(@PathVariable("studentId") int studentId) 
	{
		try
		{
			Student stud=studentSerImp.deleteId(studentId);
			if(stud != null)
			{
				studentSerImp.deleteId(studentId);
			}
		}
		catch(Exception e)
		{
			//throw new UserNotFoundException("Student data not Deleted ! please enter the valid input");
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("student/update/{studentId}")
	public ResponseEntity<Student> updateDetails(@RequestBody Student student,@PathVariable int studentId)
	{
		try
		{
			Student i=studentSerImp.updateDetails(student, studentId);
			if(i!=null)
			
				return new ResponseEntity<>(studentSerImp.updateDetails(student, studentId),HttpStatus.OK);
		}
		catch(Exception e)
		{
			throw new StudentNotFoundException("Student data not Modified ! please enter the valid input");

		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
}


