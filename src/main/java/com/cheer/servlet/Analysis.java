package com.cheer.servlet;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cheer.dao.ExamDao;
import com.cheer.dao.impl.ExamDaoImpl;
import com.cheer.domain.Exam;

public class Analysis extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER=LogManager.getLogger(Analysis.class);
	ExamDao examDao = ExamDaoImpl.getInstance();

	public Analysis()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		String FILE1 = request.getAttribute("filePath").toString();

		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(FILE1), Charset.forName("GB2312")));
		try
		{
			String line = null;
			List<Exam> exams = new ArrayList<>();
			Exam exam = null;
			int count = 0;
			
			while (null != (line = br.readLine()))
			{
				count++;
				
				// 处理文件第一行的情况
				if (1 == count)
				{
					exam = new Exam();
					exams.add(exam);
				}
				
				//  处理空行的情况
				if ("".equals(line))
				{
					exam = new Exam();
					exams.add(exam);
				}
				// 处理非空行的情况
				else
				{
					if (line.contains("."))
					{
						String[] s = line.split("\\.", 2);
						if (line.startsWith("A"))
						{
							exam.setResultA(s[1].trim());
						} else if (line.startsWith("B"))
						{
							exam.setResultB(s[1].trim());
						} else if (line.startsWith("C"))
						{
							exam.setResultC(s[1].trim());
						} else if (line.startsWith("D"))
						{
	 						exam.setResultD(s[1].trim());
						} else
						{
							exam.setTitle(s[1].trim());
							exam.setId(Integer.valueOf(s[0]));
						}
					} else
					{
						String str=line.substring(line.length()-1);
						exam.setAnswer(str);
					}
				}
			}	
			
			examDao.save(exams);
						
			System.out.println(exams.size());
			LOGGER.trace("已插入{}条数据"+exams.size());
			System.out.println(exams);
			
			// request.getSession().setAttribute("exams", exams);;
			
			// request.setAttribute("message", "数据存储成功");

			// request.getRequestDispatcher("/examinee.jsp").forward(request, response);
			
			
			request.setAttribute("insertInfo", "插入数据成功");
			request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}

		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}

		catch (IOException e)
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			if (null != br)
			{
				try
				{
					br.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);
	}
}
