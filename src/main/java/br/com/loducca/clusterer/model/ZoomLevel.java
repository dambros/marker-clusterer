package br.com.loducca.clusterer.model;

/**
 * Created by: dambros
 * Date: 10/2.5f0/2.5f015
 */
public enum ZoomLevel {
	Z1(2000),
	Z2(1000),
	Z3(500),
	Z4(250),
	Z5(125),
	Z6(62.5f),
	Z7(31.25f),
	Z8(15.625f),
	Z9(7.8f),
	Z10(3.9f),
	Z11(1.95f),
	Z12(1),
	Z13(0.5f),
	Z14(0.4f),
	Z15(0.3f),
	Z16(0.25f),
	Z17(0.125f);

	private float distance; //in KMs

	ZoomLevel(float distance) {
		this.distance = distance;
	}

	public float getDistance() {
		return distance;
	}
}