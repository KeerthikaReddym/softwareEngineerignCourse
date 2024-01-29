package com.homework.crossCountry.model;

public class Result {
	private String TeamName;
	private int score;
	private int positionSix;
	public int getPositionSix() {
		return positionSix;
	}
	public void setPositionSix(int positionSix) {
		this.positionSix = positionSix;
	}
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Result(String teamName, int score, int positionSix) {
		super();
		TeamName = teamName;
		this.score = score;
		this.positionSix = positionSix;
	}
	public Result() {
		
	}
	

}
