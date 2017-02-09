package com.parthmehrotra.geo;

/**
 * Used to represent a location with double precision
 */ 
public class Location {
	private double x; 
	private double y;

	/** 
	 * Used to create a new "location"
	 * @param x longitudinal/x-axis component of the location. 
	 * @param y latitudinal/y-axis component of the location.
	 */
	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get x value
	 * @return x value
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get the y component of the location
	 * @return y value
	 */
	public double getY() {
		return y;
	}

	/**
	 * Set the x value of the location
	 * @param x value
	 * @return this
	 */ 
	public Location setX(double x) {
		this.x = x;
		return this;
	}

	/**
	 * Set the y value of the location
	 * @param y value
	 * @return this
	 */ 
	public Location setY(double y) {
		this.y = y;
		return this;
	}

	/** 
	 * Multiply the x and y components by a scalar
	 * @param d scalar to multiply location by
	 * @return this
	 */ 
	public Location multiply(double d) {
		this.x *= d;
		this.y *= d;
		return this;
	}

	/**
	 * Calculates the euclidean distance between "this" location, and a given location
	 * @param l other Location to be compared
	 * @return the distance between them in euclidean space
	 */
	public double distance(Location l) {
		double px = l.getX() - this.getX();
		double py = l.getY() - this.getY();
		return Math.sqrt(px * px + py * py);
	}

	/**
	 * A string representation of the location
	 * @return String with the format (x, y)
	 */
	public String toString() {
		return "(" + this.getX() + ", " + this.getY() + ")";
	}

	/**
	 * Calculates the midpoint between "this" location and a given location
	 * @param l other location for midpoint
	 * @return the new midpoint between the two locations in euclidean space
	 */
	public Location midpoint(Location l) {
		double x = (this.getX() + l.getX()) / 2d;
		double y = (this.getY() + l.getY()) / 2d;

		return new Location(x, y);
	}

	/**
	 * Calculate the distance between 2 locations
	 * @param l1 first location
	 * @param l2 second location 
	 * @return the distance between the two locations in euclidean space
	 */
	public static double distance(Location l1, Location l2) {
		return l1.distance(l2);
	}

	/**
	 * Calculates the midpoint between 2 locations
	 * @param l1 first location
	 * @param l2 second location 
	 * @return the midpoint between 2 locations in euclidean space
	 */
	public static Location midpoint(Location l1, Location l2) {
		return l1.midpoint(l2);
	}

	/**
	 * Determines wether two Locations are equal
	 * @param location to be compared to this one
	 * @return true iff getX() and getY() of both objects are the same
	 */
	@Override
	public boolean equals(Object location) {
		Location l = (Location) location;
		return l.getX() == this.getX() && l.getY() == this.getY();
	}
}
