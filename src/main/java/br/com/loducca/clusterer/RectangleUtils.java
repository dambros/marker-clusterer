package br.com.loducca.clusterer;

import java.awt.geom.Rectangle2D;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class RectangleUtils {

	public static Rectangle2D getRectangle(double lat, double lng, double distance) {
		Marker m = new Marker(lat, lng, MarkerType.PIN);
		BoundingBox box = BoundingBoxUtils.calculate(m, distance);

		double minLat = box.getTopLeft().getLat() < box.getBottomLeft().getLat() ? box.getTopLeft().getLat() : box.getBottomLeft().getLat();
		double minLng = box.getTopLeft().getLng() < box.getTopRight().getLng() ? box.getTopLeft().getLng() : box.getTopRight().getLng();
		double width = box.getTopRight().getLng() - box.getTopLeft().getLng();
		double height = box.getTopRight().getLat() - box.getBottomRight().getLat();

		System.out.println(box);

		return new Rectangle2D.Double(minLat, minLng, width, height);
	}

	public static Boolean intersects(Rectangle2D r1, Rectangle2D r2) {
		return r1.intersects(r2);
	}
}
