package br.com.loducca.clusterer.utils;

import br.com.loducca.clusterer.model.Cluster;
import br.com.loducca.clusterer.model.Marker;
import br.com.loducca.clusterer.model.MarkerType;
import br.com.loducca.clusterer.model.ZoomLevel;
import ch.hsr.geohash.GeoHash;

import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: dambros
 * Date: 10/21/2015
 */
public class ClusterUtils {

	private static final String DB_PATH;
	private static final String CSV_SPLIT = ",";

	static {
		DB_PATH = PropertiesHelper.getProperties("db.dump.path");
	}

	public static List<Cluster> getClusters() {
		List<Cluster> clusters = new ArrayList<>();

		try {
			BufferedReader br = null;
			for (ZoomLevel level : ZoomLevel.values()) {
				String line;
				br = new BufferedReader(new FileReader(DB_PATH));
				List<Integer> linesRemoved = new ArrayList<>();
				int lineNumber = 1;

				while ((line = br.readLine()) != null) {

					//ignore that line if it is marked as removed
					if (linesRemoved.contains(lineNumber)) {
						lineNumber++;
						continue;
					}

					//currentLine shouldn't be read again in the future
					linesRemoved.add(lineNumber);

					String[] pin = line.split(CSV_SPLIT);
					List<Marker> markers = new ArrayList<>();

					//add current pin to the list of marker for the current cluster
					markers.add(new Marker(Double.parseDouble(pin[0]), Double.parseDouble(pin[1]), Long.parseLong(pin[2])));
					Rectangle2D mainRect = RectangleUtils.getRectangle(Double.parseDouble(pin[0]), Double.parseDouble(pin[1]), level.getDistance());

					BufferedReader newReader = new BufferedReader(new FileReader(DB_PATH));
					String newLine;
					int tempLineNumber = 1;
					while ((newLine = newReader.readLine()) != null) {
						//ignore that line if it is marked as removed and all lines previously to the current one
						if (linesRemoved.contains(tempLineNumber) || tempLineNumber <= lineNumber) {
							tempLineNumber++;
							continue;
						}

						String[] tempPin = newLine.split(CSV_SPLIT);
						Rectangle2D rect = RectangleUtils.getRectangle(Double.parseDouble(tempPin[0]), Double.parseDouble(tempPin[1]), level.getDistance());
						if (rect.intersects(mainRect)) {
							markers.add(new Marker(Double.parseDouble(tempPin[0]), Double.parseDouble(tempPin[1]), Long.parseLong(tempPin[2])));
							linesRemoved.add(tempLineNumber);
						}
						tempLineNumber++;
					}
					newReader.close();

					GeoHash hash = GeoHash.withCharacterPrecision(markers.get(0).getLat(), markers.get(0).getLng(), 1);
					MarkerType type = markers.size() > 1 ? MarkerType.CLUSTER : MarkerType.PIN;
					Integer clusterSize = markers.size() <= 1 ? null : markers.size();
					Marker marker = markers.get(0);

					//clusters shouldn't show projectId
					if (type.equals(MarkerType.CLUSTER)) {
						marker.setProjectId(null);
					}

					clusters.add(new Cluster(level, marker, clusterSize, hash.toBase32(), type));
					lineNumber++;
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clusters;
	}
}
