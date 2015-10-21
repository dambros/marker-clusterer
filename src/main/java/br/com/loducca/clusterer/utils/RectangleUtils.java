package br.com.loducca.clusterer.utils;

import br.com.loducca.clusterer.model.BoundingBox;
import br.com.loducca.clusterer.model.Marker;

import java.awt.geom.Rectangle2D;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class RectangleUtils {

	public static Rectangle2D getRectangle(double lat, double lng, double distance) {
		Marker m = new Marker(lat, lng);
		BoundingBox box = BoundingBoxUtils.calculate(m, distance);

		double minLat = box.getTopLeft().getLat() < box.getBottomLeft().getLat() ? box.getTopLeft().getLat() : box.getBottomLeft().getLat();
		double minLng = box.getTopLeft().getLng() < box.getTopRight().getLng() ? box.getTopLeft().getLng() : box.getTopRight().getLng();
		double width = box.getTopRight().getLng() - box.getTopLeft().getLng();
		double height = box.getTopRight().getLat() - box.getBottomRight().getLat();

		return new Rectangle2D.Double(minLat, minLng, width, height);
	}
}
