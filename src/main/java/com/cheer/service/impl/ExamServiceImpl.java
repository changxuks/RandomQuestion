package com.cheer.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
		return examDao.list().get(id - 1);
	}

	@Override
	public int getExamSize()
	{
		int examSize = 0;
		try
		{
			examSize =examDao.list().size();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return examSize;
	}

	@Override
	public List<Exam> getAllExams()
	{
		List<Exam> exams = null;
		try
		{
			 exams = examDao.list();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exams;
	}

}
