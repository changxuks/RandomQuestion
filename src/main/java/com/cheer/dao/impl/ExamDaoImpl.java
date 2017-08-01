package com.cheer.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cheer.dao.ExamDao;
import com.cheer.domain.Exam;
import com.cheer.util.DbHelper;

// 题库dao 映射 数据库 的表 exam
public class ExamDaoImpl implements ExamDao
{

	private static final Logger LOGGER = LogManager.getLogger(ExamDaoImpl.class);

	DbHelper dbHelper = DbHelper.getDBHelper();
	Connection conn = dbHelper.getConnection();

	private static final ExamDaoImpl INSTANCE = new ExamDaoImpl();

	private ExamDaoImpl()
	{

	}

	public static ExamDaoImpl getInstance()
	{
		return INSTANCE;
	}

	// 把题库导入数据库
	@Override
	public void save(Exam exam) throws SQLException
	{
		PreparedStatement stat = null;

		String sql = "insert into exam values(?,?,?,?,?,?,?)";
		stat = conn.prepareStatement(sql);
		stat.setInt(1, exam.getId());
		stat.setString(2, exam.getTitle());
		stat.setString(3, exam.getResultA());
		stat.setString(4, exam.getResultB());
		stat.setString(5, exam.getResultC());
		stat.setString(6, exam.getResultD());
		stat.setString(7, exam.getAnswer());
		stat.executeUpdate();
	}

	@Override
	public void save(List<Exam> exams) throws SQLException
	{
		for (Exam exam : exams)
		{
			this.save(exam);
		}
	}

	// 查询出题库所有题目
	@Override
	public List<Exam> list() throws SQLException, IOException
	{
		ResultSetHandler<List<Exam>> handler = new BeanListHandler<Exam>(Exam.class);
		QueryRunner queryRunnery = new QueryRunner();
		String sql = "select * from exam";
		List<Exam> exams = queryRunnery.query(conn, sql, handler);
		LOGGER.debug("共查询到{}条数据", exams.size());
		return exams;
	}

	// @Override
	// public void list() throws SQLException, IOException
	// {
	// QueryRunner queryRunner = new QueryRunner();
	// JSONArray jsonArray = new JSONArray();
	// JSONObject obj=new JSONObject();
	// File file = new
	// File("D:"+SP+"Users"+SP+"Administrator"+SP+"workspace"+SP+"RandomQuestion"+SP+"src"+SP+"main"+SP+"resources"+SP+"doc"+SP+"content.json");
	// FileWriter out = new FileWriter(file);
	//
	// List<Object[]> arraylist = queryRunner.query(conn,
	// "select title,resulta,resultb,resultc,resultd,answer from (select * from
	// exam order by sys_guid()) where rownum < =20",
	// new ArrayListHandler());
	//
	// for (Iterator<Object[]> itr = arraylist.iterator(); itr.hasNext();)
	// {
	// Object[] a = itr.next();
	// System.out.println("--------------");
	// for (int i = 0; i < a.length; i++)
	// {
	// obj.put("i",a[i]);
	// array.put(obj);
	// out.write(array.toString().toCharArray());
	// out.flush();
	// }
	// }
	// LOGGER.trace("数据写入成功");
	// out.close();
	// }
}
