package br.com.loducca.clusterer;

import br.com.loducca.clusterer.model.Cluster;
import br.com.loducca.clusterer.utils.ClusterUtils;
import br.com.loducca.clusterer.utils.RedisUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class TaskStarter {

	private static Gson gson = new Gson();

	public static void main(String[] args) {

		List<Cluster> clusters = ClusterUtils.getClusters();

		Map<String, List<Object>> map = new HashMap<>();
		for (Cluster c : clusters) {
			String key = c.getZoomLevel().name();
			//ZoomLevel 5 or higher
			if (c.getZoomLevel().getOrdinal() > 3) {
				key = String.format("%s-%s", key, c.getGeohash());
			}

			List<Object> objs;
			if (map.containsKey(key)) {
				objs = map.get(key);
			} else {
				objs = new ArrayList<>();
			}
			objs.add(c);
			map.put(key, objs);
		}

		for (Map.Entry<String, List<Object>> entry : map.entrySet()) {
			String json = gson.toJson(entry.getValue());
			RedisUtils.setPair(entry.getKey(), json);
		}
	}
}
