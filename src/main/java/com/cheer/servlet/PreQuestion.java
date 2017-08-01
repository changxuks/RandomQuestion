package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheer.domain.Exam;
import com.cheer.service.ExamService;
import com.cheer.service.impl.ExamServiceImpl;
import com.google.gson.Gson;

@SuppressWarnings("serial")
// 渲染答题页面的时候处理的servlet
public class PreQuestion extends HttpServlet
{

	private ExamService examService = ExamServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// 获取index
		HttpSession session = req.getSession();
		Integer index = (Integer) session.getAttribute("index");
		
		// 如果题目索引已经到达第一页，则直接返回
		if (index == 1)
		{
			resp.setCharacterEncoding("utf-8");
			resp.setContentType("application/json;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.println("{\"message\":\"已经是第一题！\"}");
			out.flush();
			out.close();
			return;
		}

		// 根据index获取题目
		String examJson = null;
		try
		{
			Exam exam = examService.getExam(index - 1);
			Gson gson = new Gson();
			examJson = gson.toJson(exam);

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		// 把题目内容响应到浏览器
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(examJson);
		out.flush();
		out.close();

		session.setAttribute("index", index - 1);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		this.doGet(req, resp);
	}
}
