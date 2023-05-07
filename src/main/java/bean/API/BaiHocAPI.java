package bean.API;

import java.util.List;

import bean.BaiHoc;

public class BaiHocAPI {
	private String result;
	private String message;
	private List<BaiHoc> lessons;
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the lessons
	 */
	public List<BaiHoc> getLessons() {
		return lessons;
	}
	/**
	 * @param lessons the lessons to set
	 */
	public void setLessons(List<BaiHoc> lessons) {
		this.lessons = lessons;
	}
	
}
