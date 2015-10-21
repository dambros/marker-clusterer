package br.com.loducca.clusterer.model;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class Marker {

	private double lat;
	private double lng;
	private Long projectId;

	public Marker(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}

	public Marker(double lat, double lng, Long projectId) {
		this.lat = lat;
		this.lng = lng;
		this.projectId = projectId;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public Long getProjectId() {
		return projectId;
	}
}
