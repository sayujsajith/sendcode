package com.schoolmgnt.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Builder

@Table(name="Teacher_Table")
public class Teacher 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="teacher_id")
	Integer teacherId;
	
	@Column(name="teacher_name")
	String teacherName;
	
	@Column(name="subject_name")
	String subject;
	
	@Column(name="contact_number")
	String phoneNumber;
	String techEmail;
	String address;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "student_Id")
    Student student;
	
	//List<Student> student= new ArrayList<>();
	
	
}






