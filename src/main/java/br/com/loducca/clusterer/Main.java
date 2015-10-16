package br.com.loducca.clusterer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class Main {

	public static void main(String[] args) throws IOException {
		double distance = 5; //kms

		double lat = -13.235004;
		double lng = -50.92528;
		Rectangle2D r1 = RectangleUtils.getRectangle(lat, lng, distance);

		double lat2 = -13.255004;
		double lng2 = -50.95528;
		Rectangle2D r2 = RectangleUtils.getRectangle(lat2, lng2, distance);

		double lat3 = -13.0;
		double lng3 = -50.0;
		Rectangle2D r3 = RectangleUtils.getRectangle(lat3, lng3, distance);

		double lat4 = -12.255004;
		double lng4 = -51.95528;
		Rectangle2D r4 = RectangleUtils.getRectangle(lat4, lng4, distance);

		double lat5 = -11.755004;
		double lng5 = -50.95528;
		Rectangle2D r5 = RectangleUtils.getRectangle(lat5, lng5, distance);

		List<Rectangle2D> rects = new ArrayList<Rectangle2D>();
		rects.add(r2);
		rects.add(r3);
		rects.add(r4);
		rects.add(r5);

		for (Rectangle2D r : rects) {
			System.out.println(RectangleUtils.intersects(r1, r).toString());
		}
	}
}
