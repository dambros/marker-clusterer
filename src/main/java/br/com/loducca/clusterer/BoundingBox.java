package br.com.loducca.clusterer;

/**
 * Created by: dambros
 * Date: 10/15/2015
 */
public class BoundingBox {

	private Marker topRight;
	private Marker bottomRight;
	private Marker topLeft;
	private Marker bottomLeft;

	public BoundingBox(Marker topRight, Marker bottomRight, Marker topLeft, Marker bottomLeft) {
		this.topRight = topRight;
		this.bottomRight = bottomRight;
		this.topLeft = topLeft;
		this.bottomLeft = bottomLeft;
	}

	public Marker getTopRight() {
		return topRight;
	}

	public void setTopRight(Marker topRight) {
		this.topRight = topRight;
	}

	public Marker getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(Marker bottomRight) {
		this.bottomRight = bottomRight;
	}

	public Marker getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(Marker topLeft) {
		this.topLeft = topLeft;
	}

	public Marker getBottomLeft() {
		return bottomLeft;
	}

	public void setBottomLeft(Marker bottomLeft) {
		this.bottomLeft = bottomLeft;
	}

	@Override
	public String toString() {
		return String.format("[{lat: %s, lng: %s}, \n {lat: %s, lng: %s}, \n {lat: %s, lng: %s}, \n {lat: %s, lng: %s}],",
				getBottomRight().getLat(), getBottomRight().getLng(),
				getTopRight().getLat(), getTopRight().getLng(),
				getTopLeft().getLat(), getTopLeft().getLng(),
				getBottomLeft().getLat(), getBottomLeft().getLng());
	}
}
