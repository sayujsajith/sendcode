package com.schoolmgnt.service;

import com.schoolmgnt.dto.TeacherDTO;
import com.schoolmgnt.entity.Teacher;

public interface TeacherService 
{
	Teacher insertData(TeacherDTO teacherDTO);
	
	Teacher getDetailsById(int teacherId);
	
	//Teacher fetchDetalis();
	
	String deleteTeacherDetails(int teacherId);
	
	Teacher updateTeacherDetails(Teacher teacher,int teacherId);

}
