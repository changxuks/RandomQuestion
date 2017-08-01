package com.cheer.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cheer.domain.Exam;

public interface ExamDao
{
	void save(Exam exam) throws SQLException;

	void save(List<Exam> exams) throws SQLException;
	
	List<Exam> list() throws SQLException, IOException;
	
}
