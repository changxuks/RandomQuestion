package com.cheer.domain;

public class Examinee
{
	private int no;
	private String name;
	private String password;
	private String state;
	private int score;

	public int getNo()
	{
		return no;
	}

	public void setNo(int no)
	{
		this.no = no;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public Examinee()
	{
		super();
	}

	public Examinee(int no, String name, String password, String state, int score)
	{
		super();
		this.no = no;
		this.name = name;
		this.password = password;
		this.state = state;
		this.score = score;
	}

}
