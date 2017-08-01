package com.cheer.dao;

import java.sql.SQLException;
import java.util.List;

import com.cheer.domain.Exam;
import com.cheer.domain.ExamineeQuestion;

public interface ExamineeQuestionDao
{
	void save(ExamineeQuestion examineeQuestion) throws SQLException;

	void save(List<ExamineeQuestion> examineeQuestions) throws SQLException;
	
	void list(Exam exam) throws SQLException;
}
