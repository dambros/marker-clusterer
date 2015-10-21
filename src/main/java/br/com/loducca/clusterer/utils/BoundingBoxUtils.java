package br.com.loducca.clusterer.utils;

import br.com.loducca.clusterer.model.BoundingBox;
import br.com.loducca.clusterer.model.Marker;

import static java.lang.Math.asin;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class BoundingBoxUtils {

	private static final double TOP_RIGHT_ANGLE = 45;
	private static final double BOTTOM_RIGHT_ANGLE = 135;
	private static final double BOTTOM_LEFT_ANGLE = 225;
	private static final double TOP_LEFT_ANGLE = 315;
	private static final double RADIUS = 6371; // KMs

	//calculate a square boundingbox
	public static BoundingBox calculate(Marker centerMarker, double distance) {
		Marker tempTopRight = getBoundaryMarker(centerMarker, distance, TOP_RIGHT_ANGLE);
		Marker tempBottomRight = getBoundaryMarker(centerMarker, distance, BOTTOM_RIGHT_ANGLE);
		Marker tempTopLeft = getBoundaryMarker(centerMarker, distance, TOP_LEFT_ANGLE);
		Marker tempBottomLeft = getBoundaryMarker(centerMarker, distance, BOTTOM_LEFT_ANGLE);
//
		double minLat = tempTopLeft.getLat() < tempBottomLeft.getLat() ? tempTopLeft.getLat() : tempBottomLeft.getLat();
		double maxLat = tempTopLeft.getLat() > tempBottomLeft.getLat() ? tempTopLeft.getLat() : tempBottomLeft.getLat();
		double minLng = tempTopLeft.getLng() < tempTopRight.getLng() ? tempTopLeft.getLng() : tempTopRight.getLng();
		double maxLng = tempTopLeft.getLng() > tempTopRight.getLng() ? tempTopLeft.getLng() : tempTopRight.getLng();

		Marker topRight = new Marker(maxLat, maxLng);
		Marker bottomRight = new Marker(minLat, maxLng);
		Marker topLeft = new Marker(maxLat, minLng);
		Marker bottomLeft = new Marker(minLat, minLng);


//		double latDistance = distance / 111.2;
//		double lngDistance = 111.320 * cos(latDistance);

//		Marker bottomLeft = new Marker((centerMarker.getLat() - latDistance / 2), (centerMarker.getLng() - latDistance / 2));
//		Marker bottomRight = new Marker((centerMarker.getLat() - latDistance / 2), (centerMarker.getLng() + latDistance / 2));
//		Marker topRight = new Marker((centerMarker.getLat() + latDistance / 2), (centerMarker.getLng() + latDistance / 2));
//		Marker topLeft = new Marker((centerMarker.getLat() + latDistance / 2), (centerMarker.getLng() - latDistance / 2));


//		double latDistance = distance / 110.54;
//		double lngDistance = 111.320 * cos(centerMarker.getLat());
//
//		Marker topRight = new Marker(centerMarker.getLat() + latDistance, centerMarker.getLng() + lngDistance);
//		Marker topLeft = new Marker(centerMarker.getLat() + latDistance, centerMarker.getLng() - lngDistance);
//		Marker bottomRight = new Marker(centerMarker.getLat() - latDistance, centerMarker.getLng() + lngDistance);
//		Marker bottomLeft = new Marker(centerMarker.getLat() - latDistance, centerMarker.getLng() - lngDistance);

//		return new BoundingBox(tempTopRight, tempBottomRight, tempTopLeft, tempBottomLeft);
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
		double d = sqrt((distance * distance) + (distance * distance));
		double lat = getLatitude(d, centerMarker.getLat(), angle);
		double lng = getLongitude(d, centerMarker.getLat(), centerMarker.getLng(), angle);
		return new Marker(lat, lng);
	}
}
