package bean.API;

public class VideoBaiHocAPI {
	private String result;
	private String video;
	private String description;
	private String tenBH;
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
	 * @return the video
	 */
	public String getVideo() {
		return video;
	}
	/**
	 * @param video the video to set
	 */
	public void setVideo(String video) {
		this.video = video;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTenBH() {
		return tenBH;
	}
	
	public void setTenBH(String tenBH) {
		// TODO Auto-generated method stub
		this.tenBH = tenBH;
		
	}
	
	@Override
	public String toString() {
		return "VideoBaiHocAPI [result=" + result + ", video=" + video + ", description=" + description + ",tenBH="+tenBH+"]";
	}
	
}
