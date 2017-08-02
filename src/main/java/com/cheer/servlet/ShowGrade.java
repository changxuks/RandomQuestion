package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheer.domain.Exam;

public class ShowGrade extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		Enumeration<String> names = session.getAttributeNames();
		while (names.hasMoreElements())
		{
			String name = names.nextElement();
			Object value = session.getAttribute(name);

			System.out.println(name + "=" + value);
		}
		System.out.println(names);
		Map<Integer, String> checked = (Map<Integer, String>) request.getSession().getAttribute("checked");

		List<Exam> exams = (List<Exam>) request.getSession().getAttribute("exams");

		System.out.println(exams);
		int count = 0;
		for (int i = 1; i <= exams.size(); i++)
		{
			String check = checked.get(i);
			String answer = exams.get(i - 1).getAnswer();
			if (answer.equals(check))
			{
				count++;
			}
			System.out.println(check + "==" + answer);
		}
		double grade = count * 2;
		PrintWriter out = response.getWriter();
		out.println(grade);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

}
