package br.com.loducca.clusterer.model;

/**
 * Created by: dambros
 * Date: 10/20/2015
 */
public enum ZoomLevel {
	Z1(5000),
	Z2(2500),
	Z3(1250),
	Z4(625),
	Z5(312),
	Z6(156),
	Z7(78),
	Z8(40),
	Z9(20),
	Z10(10),
	Z11(5),
	Z12(2.5f),
	Z13(1.25f),
	Z14(1),
	Z15(0.5f),
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