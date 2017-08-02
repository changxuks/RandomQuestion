package com.cheer.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cheer.domain.Exam;

public interface ExamService
{
	public Exam getExam(int id) throws SQLException, IOException;

	public int getExamSize();
	
	public List<Exam> getAllExams();
} 
