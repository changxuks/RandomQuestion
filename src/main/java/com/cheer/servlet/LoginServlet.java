package com.cheer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginServlet extends HttpServlet
{
	private static final Logger LOGGER = LogManager.getLogger(LoginServlet.class);

	private static final long serialVersionUID = 1L;

	private String contentType = null;

	public LoginServlet()
	{
		LOGGER.trace("实例化。。。。");
	}

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		LOGGER.trace("初始化。。。。");
		this.contentType = config.getInitParameter("Content-Type");
		LOGGER.trace("Content-Type : {}", this.contentType);
	}

	public void destroy()
	{
		LOGGER.trace("调用销毁方法。。。。");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Thread.currentThread().setName("loginServlet");
		LOGGER.entry("doGet.....");
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType(this.contentType);
		LOGGER.entry("doPost.....");
		PrintWriter pw = response.getWriter();

		// 从cookie获取
		String username = null;
		String password = null;
		
		// 获取客户端Cookie
		Cookie[] cookies = request.getCookies();
		boolean hasCookie = false;
		
		if (cookies != null && cookies.length >= 3)
 		{
			hasCookie = true;
			for (Cookie cookie : cookies)
			{
				LOGGER.trace("cookie {}={}", cookie.getName(), cookie.getValue());
				if ("username".equals(cookie.getName()))
				{
					username = cookie.getValue();
				}

				if ("password".equals(cookie.getName()))
				{
					password = cookie.getValue();
				}
			}
			LOGGER.trace("Cookies 登录！");
		} else
		{
			// 表单获取
			username = request.getParameter("username");			
			password = request.getParameter("password");
			LOGGER.trace("form username={}", username);
			LOGGER.trace("form password={}", password);
			LOGGER.trace("表单 登录！");
		}

		if ("admin".equals(username) && "admin".equals(password))
		{
			if (!hasCookie)
			{
				Cookie cookieUsername = new Cookie("username", username);
				//cookieUsername.setDomain("127.0.0.1");
				cookieUsername.setMaxAge(3600);
				//cookieUsername.setPath("/Servlet01/servlet/loginServlet");
				//cookieUsername.setSecure(true);
				Cookie cookiePassword = new Cookie("password", password);
				cookiePassword.setMaxAge(3600);
				
				// 第一种方法
				response.addCookie(cookieUsername);
				response.addCookie(cookiePassword);

				// 第二种方法
				response.addHeader("Set-Cookie", "isSavePassword=true; Max-Age=3600");
				LOGGER.trace("cookie is saved.");
			}

			request.setAttribute("username", username);
			request.setAttribute("isLogin", true);
			LOGGER.trace("成功转发！----------->/servlet/downloadServlet");
			request.getRequestDispatcher("/servlet/downloadServlet").forward(request, response);
			return;
		} else
		{
			LOGGER.trace("登录失败，跳转到登录页面！----------->/index.html");
			LOGGER.trace("request.getContextPath()={}", request.getContextPath());
			// response.sendRedirect("../index.html");
			response.sendRedirect(request.getContextPath() + "/" + "index.jsp");
		}
	}

}
