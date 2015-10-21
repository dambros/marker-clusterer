package br.com.loducca.clusterer.model;

/**
 * Created by: dambros
 * Date: 10/20/2015
 */
public class ProjectLocation {

	private float latitude;
	private float longitude;
	private Long projectId;

	public ProjectLocation(float latitude, float longitude, Long projectId) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.projectId = projectId;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
