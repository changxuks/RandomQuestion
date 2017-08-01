package com.cheer.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DbHelper
{
	private static final DbHelper INSTANCE = new DbHelper();
	private static final Logger LOGGER = LogManager.getLogger(DbHelper.class);

	private DbHelper()
	{

	}
	public static DbHelper getDBHelper()
	{
		return INSTANCE;
	}

	public Properties getDbConfig()
	{
		InputStream is = null;
		Properties props = new Properties();
		try
		{
			is = DbHelper.class.getClassLoader().getResourceAsStream("dbProperties/db.properties");
			props.load(is);
			LOGGER.debug("成功读取配置文件！");
		} catch (FileNotFoundException e)
		{
			LOGGER.warn("找不到配置文件！");
			return null;
		} catch (IOException e)
		{
			LOGGER.warn("读取配置文件失败");
			return null;
		} finally
		{
			if (null != is)
			{
				try
				{
					is.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return props;
	}

	// 获取数据库连接
	public  Connection getConnection()
	{		
		Properties props = this.getDbConfig();

		if (null == props)
		{
			return null;
		}

		Connection conn = null;
		String url = props.getProperty("url");
		String userName = props.getProperty("username");
		String password = props.getProperty("password");
		try
		{
			LOGGER.debug("开始建立数据库连接...");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, userName, password);
			LOGGER.debug("连接成功！");
		} catch (ClassNotFoundException e)
		{
			LOGGER.warn("没有找到驱动类：oracle.jdbc.driver.OracleDriver！");
			return null;
		} catch (SQLException e)
		{
			LOGGER.warn("连接失败！");
			return null;
		}
		return conn;
	}

}
