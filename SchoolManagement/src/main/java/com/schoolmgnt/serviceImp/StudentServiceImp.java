package com.schoolmgnt.serviceImp;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmgnt.dto.StudentDTO;
import com.schoolmgnt.entity.Student;
import com.schoolmgnt.repositories.StudentRepository;
import com.schoolmgnt.service.StudentService;

@Service
public class StudentServiceImp implements StudentService 
{
	@Autowired
	StudentRepository studentRepo;
	
								//Inserting the Student Details in Student Table.
	@Override
	public Student insertStudent(@Valid StudentDTO studentDTO) 
	{
		Student student=Student.builder()//.studentId(studentDTO.getStudentId())
										 .studentName(studentDTO.getStudentName())
										 .studentDOB(studentDTO.getStudentDOB())
										 .studentEmail(studentDTO.getStudentEmail())
										 .address(studentDTO.getAddress())
										 .phoneNumber(studentDTO.getPhoneNumber())
										 .build();
								
		return studentRepo.save(student);
	}
	
	
								//Retrieve the Student Details by given StudentID.
	@Override
	public Student getDetailsById(int studentId ) 
	{
		return studentRepo.findById(studentId).get();
	}
	
	
	
	/*							//Retrieve the All Student Details
	@Override
	public Student getDetails() 
	{
		
		return (Student) studentRepo.findAll();
	}
	*/
	
								//Delete the Student Details By given ID
	@Override
	public Student  deleteId(int studentId) 
	{
	 studentRepo.deleteById(studentId);
	return null;
	}
	
	/*							//Delete the All Student Details
	@Override
	public Student deleteAll( )
	{
		
		return null;
	}
	*/
	
								//Update the Student Details by given ID.
	@Override
	public  Student updateDetails(Student params,int studentId) 
	{
		Student stud=studentRepo.findById(studentId).get();
		stud.setStudentName (params.getStudentName());
		stud.setPhoneNumber (params.getPhoneNumber());
		stud.setStudentDOB  (params.getStudentDOB());
		stud.setAddress     (params.getAddress());
		
		return studentRepo.save(stud);
	}	
}
