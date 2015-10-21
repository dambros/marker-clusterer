package br.com.loducca.clusterer.model;

/**
 * Created by: dambros
 * Date: 10/20/2015
 */
public class Cluster {

	private ZoomLevel zoomLevel;
	private Marker marker;
	private Integer clusterSize;
	private String geohash;
	private MarkerType type;

	public Cluster(ZoomLevel zoomLevel, Marker marker, Integer clusterSize, String geohash, MarkerType type) {
		this.zoomLevel = zoomLevel;
		this.marker = marker;
		this.clusterSize = clusterSize;
		this.geohash = geohash;
		this.type = type;
	}

	public ZoomLevel getZoomLevel() {
		return zoomLevel;
	}

	public Marker getMarker() {
		return marker;
	}

	public Integer getClusterSize() {
		return clusterSize;
	}

	public String getGeohash() {
		return geohash;
	}

	public MarkerType getType() {
		return type;
	}
}
