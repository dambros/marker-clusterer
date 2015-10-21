package br.com.loducca.clusterer.model;

import java.util.List;

/**
 * Created by: dambros
 * Date: 10/20/2015
 */
public class Cluster {

	private ZoomLevel zoomLevel;
	private List<Marker> markers;
	private String geohash;

	public Cluster(ZoomLevel zoomLevel, List<Marker> markers, String geohash) {
		this.zoomLevel = zoomLevel;
		this.markers = markers;
		this.geohash = geohash;
	}

	public ZoomLevel getZoomLevel() {
		return zoomLevel;
	}

	public List<Marker> getMarkers() {
		return markers;
	}

	public String getGeohash() {
		return geohash;
	}
}
