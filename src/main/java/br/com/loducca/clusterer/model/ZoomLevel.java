package br.com.loducca.clusterer.model;

/**
 * Created by: dambros
 * Date: 10/20/2015
 */
public enum ZoomLevel {
	Z1(5000/2),
	Z2(2500/2),
	Z3(1250/2),
	Z4(625/2),
	Z5(312/2),
	Z6(156/2),
	Z7(78/2),
	Z8(40/2),
	Z9(20/2),
	Z10(10/2),
	Z11(5/2),
	Z12(2.5f/2),
	Z13(1.25f/2),
	Z14(1/2),
	Z15(0.5f/2),
	Z16(0.25f/2),
	Z17(0.125f/2);

	private float distance; //in KMs

	ZoomLevel(float distance) {
		this.distance = distance;
	}

	public float getDistance() {
		return distance;
	}
}