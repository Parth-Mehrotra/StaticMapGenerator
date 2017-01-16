package com.parth.geo;

public class Mercator {
	public static double mercatorDistance(Location p1, Location p2) {
		Location mp1 = globeToMercator(p1);
		Location mp2 = globeToMercator(p2);

		return mp1.distance(mp2);
	}

	public static Location mercatorMidpoint(Location l1, Location l2) {
		Location mp1 = globeToMercator(l1);
		Location mp2 = globeToMercator(l2);

		return mp1.midpoint(mp2);
	}

	public static Location globeToMercator(Location globe) {
		return new Location(lonToMercatorX(globe.getX()), latToMercatorY(globe.getY()));
	}

	private static double lonToMercatorX(double lon) {
		return (lon + 180d) / 360d;
	}

	private static double latToMercatorY(double lat) {
		// Operations are to be conducted in Radians, lat is [-90, 90]
		double rad = Math.toRadians(lat);

		// Apply main projection
		double proj = Math.log(
			(1 + Math.sin(rad)) /
			(1 - Math.sin(rad))
		);

		// Scale projection (R/2), R = 1/(2PI), if map circumference = 1
		double projScaled = proj / (4 * Math.PI);

		// Translate periodic function to the left so our range of values are from [0, 1] rather than [-0.5, 0.5]. 
		double translateLeft = projScaled - 0.5;

		// Invert values because top = 0, and bottom is 1 (quadrant 3)
		return translateLeft * -1d;
	}

}
