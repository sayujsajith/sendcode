package com.schoolmgnt.service;

import com.schoolmgnt.dto.StudentDTO;
import com.schoolmgnt.entity.Student;

public interface StudentService 
{
	Student insertStudent(StudentDTO studentDTO);
	
	Student getDetailsById(int studentId);
	
	//Student getDetails( );
	
	 Student deleteId(int studentId);
	
	//Student deleteAll( );
	
	Student updateDetails(Student student,int studentId);
	
}
