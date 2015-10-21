package br.com.loducca.clusterer;

import br.com.loducca.clusterer.model.Cluster;
import br.com.loducca.clusterer.model.Marker;
import br.com.loducca.clusterer.model.MarkerType;
import br.com.loducca.clusterer.model.ZoomLevel;
import br.com.loducca.clusterer.utils.PropertiesHelper;
import br.com.loducca.clusterer.utils.RectangleUtils;
import ch.hsr.geohash.GeoHash;

import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class Main {

	private static final String DB_PATH;
	private static final String CSV_SPLIT = ",";

	static {
		DB_PATH = PropertiesHelper.getProperties("db.dump.path");
	}

	public static void main(String[] args) {

		//TODO LIST
		// remover montueira de código do main;
		// cluster não precisa de lista de markers, apenas do Marker inicial (posicao do cluster/pin) e da quantidade de marcadores;
		// se cluster size > 1 tipo = CLUSTER, senao PIN

		List<Cluster> clusters = new ArrayList<Cluster>();

		try {
			for (ZoomLevel level : ZoomLevel.values()) {

				String line;
				BufferedReader br = new BufferedReader(new FileReader(DB_PATH));
				List<Integer> linesRemoved = new ArrayList<Integer>();
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
					List<Marker> markers = new ArrayList<Marker>();

					//add current pin to the list of marker for the current cluster
					markers.add(new Marker(Double.parseDouble(pin[0]), Double.parseDouble(pin[1]), MarkerType.PIN, Long.parseLong(pin[2])));
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
							markers.add(new Marker(Double.parseDouble(tempPin[0]), Double.parseDouble(tempPin[1]), MarkerType.PIN, Long.parseLong(tempPin[2])));
							linesRemoved.add(tempLineNumber);
						}
						tempLineNumber++;
					}
					newReader.close();

					GeoHash hash = GeoHash.withCharacterPrecision(markers.get(0).getLat(), markers.get(0).getLng(), 1);
					clusters.add(new Cluster(level, markers, hash.toBase32()));
					lineNumber++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
