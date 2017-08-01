package com.cheer.dao.impl;

import java.sql.Connection;

import com.cheer.dao.AdminDao;
import com.cheer.util.DbHelper;

public class AdminDaoImpl implements AdminDao
{
	DbHelper dbHelper = DbHelper.getDBHelper();
	Connection conn = dbHelper.getConnection();
	
}
