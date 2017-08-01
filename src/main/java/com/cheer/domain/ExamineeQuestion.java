package com.cheer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ExamineeQuestion
{
	private int id;
	private String title;
	private String resultA;
	private String resultB;
	private String resultC;
	private String resultD;
	private String answer;
	
	public int getId()
	{
		return id;
	}
	public void setId(int s)
	{
		this.id = s;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getResultA()
	{
		return resultA;
	}
	public void setResultA(String resultA)
	{
		this.resultA = resultA;
	}
	public String getResultB()
	{
		return resultB;
	}
	public void setResultB(String resultB)
	{
		this.resultB = resultB;
	}
	public String getResultC()
	{
		return resultC;
	}
	public void setResultC(String resultC)
	{
		this.resultC = resultC;
	}
	public String getResultD()
	{
		return resultD;
	}
	public void setResultD(String resultD)
	{
		this.resultD = resultD;
	}
	public String getAnswer()
	{
		return answer;
	}
	public void setAnswer(String answer)
	{
		this.answer = answer;
	}
	public ExamineeQuestion()
	{
		super();
	}
	public ExamineeQuestion(int id, String title, String resultA, String resultB, String resultC, String resultD, String answer)
	{
		super();
		this.id = id;
		this.title = title;
		this.resultA = resultA;
		this.resultB = resultB;
		this.resultC = resultC;
		this.resultD = resultD;
		this.answer = answer;
	}
	
	public String tostring()
	{
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}	
}
