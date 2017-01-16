package com.parth.geo;

public class Location {
	public double x; 
	public double y;

	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double distance(Location l) {
		double px = l.getX() - this.getX();
		double py = l.getY() - this.getY();
		return Math.sqrt(px * px + py * py);
	}

	public String toString() {
		return "(" + this.getX() + ", " + this.getY() + ")";
	}

	public Location midpoint(Location l) {
		double x = (this.getX() + l.getX()) / 2d;
		double y = (this.getY() + l.getY()) / 2d;

		return new Location(x, y);
	}

	public static Location midpoint(Location l1, Location l2) {
		return l1.midpoint(l2);
	}
}
