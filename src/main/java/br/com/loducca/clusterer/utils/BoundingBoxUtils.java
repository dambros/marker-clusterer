package br.com.loducca.clusterer.utils;

import br.com.loducca.clusterer.model.BoundingBox;
import br.com.loducca.clusterer.model.Marker;
import br.com.loducca.clusterer.model.MarkerType;

import static java.lang.Math.*;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class BoundingBoxUtils {

	private static final double TOP_RIGHT_ANGLE = 45;
	private static final double BOTTOM_RIGHT_ANGLE = 135;
	private static final double BOTTOM_LEFT_ANGLE = 225;
	private static final double TOP_LEFT_ANGLE = 315;
	private static final double RADIUS = 6378.1; // KMs

	//calculate a square boundingbox
	public static BoundingBox calculate(Marker centerMarker, double distance) {
		Marker topRight = getBoundaryMarker(centerMarker, distance, TOP_RIGHT_ANGLE);
		Marker bottomRight = getBoundaryMarker(centerMarker, distance, BOTTOM_RIGHT_ANGLE);
		Marker topLeft = getBoundaryMarker(centerMarker, distance, TOP_LEFT_ANGLE);
		Marker bottomLeft = getBoundaryMarker(centerMarker, distance, BOTTOM_LEFT_ANGLE);
		return new BoundingBox(topRight, bottomRight, topLeft, bottomLeft);
	}

	private static double getLatitude(double distance, double lat, double angle) {
		return toDegrees(asin(sin(toRadians(lat)) * cos(distance / RADIUS) + cos(toRadians(lat)) * sin(distance / RADIUS) * cos(toRadians(angle))));
	}

	private static double getLongitude(double distance, double lat, double lng, double angle) {
		double newLat = getLatitude(distance, lat, angle);
		return toDegrees(toRadians(lng) + atan2(sin(toRadians(angle)) * sin(distance / RADIUS) * cos(toRadians(lat)), cos(distance / RADIUS) - sin(toRadians(lat)) * sin(toRadians(newLat))));
	}

	private static Marker getBoundaryMarker(Marker centerMarker, double distance, double angle) {
		double lat = getLatitude(distance, centerMarker.getLat(), angle);
		double lng = getLongitude(distance, centerMarker.getLat(), centerMarker.getLng(), angle);
		return new Marker(lat, lng, MarkerType.BOUNDARY);
	}
}
