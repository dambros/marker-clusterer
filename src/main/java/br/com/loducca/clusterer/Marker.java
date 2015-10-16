package br.com.loducca.clusterer;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class Marker {

	private double lat;
	private double lng;
	private MarkerType type;

	public Marker(double lat, double lng, MarkerType type) {
		this.lat = lat;
		this.lng = lng;
		this.type = type;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public MarkerType getType() {
		return type;
	}

}
