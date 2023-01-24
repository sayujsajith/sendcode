package com.schoolmgnt.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.schoolmgnt.dto.StudentDTO;
import com.schoolmgnt.dto.TeacherDTO;
import com.schoolmgnt.entity.Student;
import com.schoolmgnt.entity.Teacher;
import com.schoolmgnt.repositories.StudentRepository;
import com.schoolmgnt.repositories.TeacherReporsitory;
import com.schoolmgnt.service.TeacherService;

@Service
public class TeacherServiceImp implements TeacherService 
{
	@Autowired
	TeacherReporsitory teacherRepo;
	//@Autowired
	//StudentRepository studentRepo;
	public Teacher teachId;
	
	
									//Inserting the Student Details in Student Table.
	@Override
	public Teacher insertData(TeacherDTO teacherDTO) 
	{
		Teacher teacher=Teacher.builder().teacherName(teacherDTO.getTeacherName())
										 .subject(teacherDTO.getSubject())
										 .phoneNumber(teacherDTO.getPhoneNumber())							 
										 .techEmail(teacherDTO.getTechEmail())
										 .address(teacherDTO.getAddress())
										 .student(teacherDTO.getStudent())
										 .build();
		
//		Integer studentId = stud.getStudentId();
//		Student student=studentRepo.findById(studentId).get();
//						teacher.setStudent(student);
		 			
		return  teacherRepo.save(teacher);
	}
	

									//Retrieve the Student Details by given StudentID.
	@Override
	public Teacher getDetailsById(int teacherId)
	{
		return teacherRepo.findById(teacherId).get();
	}

	
									//Delete the Teacher Details By given ID
	@Override
	public String deleteTeacherDetails(int teacherId)
	{
		//Teacher teachId = null;
		if(teachId == teacherRepo.findById(teacherId).get())
		{
			teacherRepo.deleteById(teacherId);
			
			return "Teacher Details deleted sucessfully";
		}
		 else
		{
			return "Teacher Details not deleted";
		}
	}
									//Update the Teacher Details by given ID.
	@Override
	public Teacher updateTeacherDetails(Teacher params, int teacherId) 
	{
		Teacher teach=teacherRepo.findById(teacherId).get();
				teach.setTeacherName  (params.getTeacherName());
				teach.setPhoneNumber  (params.getPhoneNumber());
				teach.setSubject	  (params.getSubject());
				teach.setTechEmail 		  (params.getTechEmail());
				teach.setAddress	  (params.getAddress());
		
		return teacherRepo.save(teach);
	}

}
