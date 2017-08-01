package com.cheer.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cheer.dao.ExamineeQuestionDao;
import com.cheer.domain.Exam;
import com.cheer.domain.ExamineeQuestion;
import com.cheer.util.DbHelper;

public class ExamineeQuestionDaoImpl implements ExamineeQuestionDao
{
	private static final Logger LOGGER = LogManager.getLogger(ExamineeQuestionDaoImpl.class);
	DbHelper dbHelper = DbHelper.getDBHelper();
	Connection conn = dbHelper.getConnection();

	PreparedStatement stat = null;
	ResultSet rs = null;

	@Override
	public void save(ExamineeQuestion examineeQuestion) throws SQLException
	{

		String sql = "insert into exam  values(?,?,?,?,?,?,?)";
		stat = conn.prepareStatement(sql);
		stat.setInt(1, examineeQuestion.getId());
		stat.setString(2, examineeQuestion.getTitle());
		stat.setString(3, examineeQuestion.getResultA());
		stat.setString(4, examineeQuestion.getResultB());
		stat.setString(5, examineeQuestion.getResultC());
		stat.setString(6, examineeQuestion.getResultD());
		stat.setString(7, examineeQuestion.getAnswer());
		stat.executeUpdate();
	}

	@Override
	public void save(List<ExamineeQuestion> examineeQuestions) throws SQLException
	{
		for (ExamineeQuestion exam : examineeQuestions)
		{
			this.save(exam);
		}

	}

	@Override
	public void list(Exam exam) throws SQLException
	{

	}

}
