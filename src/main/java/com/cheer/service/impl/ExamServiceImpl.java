package com.cheer.service.impl;

import java.io.IOException;
import java.sql.SQLException;

import com.cheer.dao.ExamDao;
import com.cheer.dao.impl.ExamDaoImpl;
import com.cheer.domain.Exam;
import com.cheer.service.ExamService;

public class ExamServiceImpl implements ExamService
{
	private static final ExamServiceImpl INSTANCE = new ExamServiceImpl();

	private ExamDao examDao = ExamDaoImpl.getInstance();

	private ExamServiceImpl()
	{
	}

	public static ExamServiceImpl getInstance()
	{
		return INSTANCE;
	}

	@Override
	public Exam getExam(int id) throws SQLException, IOException
	{
		return examDao.list().get(id);
	}

}
