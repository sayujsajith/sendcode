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

import com.schoolmgnt.dto.TeacherDTO;
import com.schoolmgnt.entity.Teacher;
import com.schoolmgnt.exception.StudentNotFoundException;
import com.schoolmgnt.exception.TeacherNotFoundException;
import com.schoolmgnt.serviceImp.TeacherServiceImp;

@RestController
public class TeacherController 
{
	@Autowired
	TeacherServiceImp teacherSerImp;
	
	@PostMapping("/teacher")
	public ResponseEntity<Teacher> insertData(@RequestBody @Valid TeacherDTO teacherDto)
	{
		try
		{
			Teacher teach= teacherSerImp.insertData(teacherDto);
			if(teach!=null)
			{
				return new ResponseEntity<>(teacherSerImp.insertData(teacherDto),HttpStatus.CREATED);
			}
		}
			catch(Exception e)
			{
				throw new StudentNotFoundException("Student data not inserted ! please enter the valid input");
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/teacher/details/{teacherId}")
	public ResponseEntity<Teacher> getDetailsById(@PathVariable ("teacherId")int teacherId)
	{
		try
		{
			Teacher teach=teacherSerImp.getDetailsById(teacherId);
			if(teach != null)
			{
			 return new ResponseEntity<>(teacherSerImp.getDetailsById(teacherId),HttpStatus.FOUND);
			}
		}
		catch(Exception e)
		{
			throw new NoSuchElementException("Teacher data not Found ! please enter the valid input");
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/teacher/delete/{teacherId}")
	public ResponseEntity<Teacher> deleteTeacherDetails(@PathVariable("teacherId") int teacherId)
	{
		try
		{
			Teacher teach=null;//=teacherSerImp.deleteTeacherDetails(teacherId);
			if(teach == teacherSerImp.teachId)
			{
				 teacherSerImp.deleteTeacherDetails(teacherId);
			}
		}
		catch(Exception e)
		{
			throw new TeacherNotFoundException("Teacher data not Deleted ! please enter the valid input");

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/teacher/update/{teacherId}")
	 public ResponseEntity<Teacher> updateTeacherDetails(@RequestBody Teacher teacher,@PathVariable int teacherId) 
	{
		try
		{
			Teacher teach=teacherSerImp.updateTeacherDetails(teacher, teacherId);
			if(teach != null)
			{
				teacherSerImp.updateTeacherDetails(teacher, teacherId);
			}
		}
		catch(Exception e)
		{
			throw new TeacherNotFoundException("Teacher data not Modified ! please enter the valid input");
		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	

}
