package com.cheer.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class DownloadServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	// 上传存储目录-定义成常量
	private static final String UPLOAD_DIRECTOR = "upload";

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;// 3M
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;

	public DownloadServlet()
	{

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		// 监察是否为多媒体文件
		if (!ServletFileUpload.isMultipartContent(request))
		{
			PrintWriter pw = response.getWriter();
			pw.print("Error:form标签必须添加enctype=multipart/form-data属性");
			pw.flush();
			return;
		}

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 设置内存临界值
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File("E:\\上传文件"));
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
		upload.setHeaderEncoding("utf-8");

		String uploadPath = this.getServletContext().getRealPath("./") + File.separator + UPLOAD_DIRECTOR;
		request.setAttribute("message", "文件上传成功");
		// 如果目录不存在则创建
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists())
		{
			uploadDir.mkdir();
		}

		try
		{
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0)
			{
				// 迭代表单数据
				for (FileItem item : formItems)
				{
					// 处理不在表单中的字段
					if (!item.isFormField())
					{
						String fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + fileName;
						File storeFile = new File(filePath);
						// 在控制台输出文件的上传路径
						System.out.println(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						request.setAttribute("message", "文件上传成功!");
						request.setAttribute("filePath", filePath);
					}
				}
			}
		} catch (Exception ex)
		{
			request.setAttribute("message", "错误信息: " + ex.getMessage());
		}
		//request.getRequestDispatcher("/message.jsp").forward(request, response);
		request.getRequestDispatcher("/servlet/analysis").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
