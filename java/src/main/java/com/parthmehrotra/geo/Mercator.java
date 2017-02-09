package com.parthmehrotra.geo;

/**
 * Class used to do opperations that relate to the Mercator Projcetion. "Globe" is defined as Locations on a globe as Longitude and Latitude with the values x: [-180, 180], y: [-90, 90]. "Mercator" refers to the Locations that have been projected onto a Mercator map of the size 1x1, so values take the ranges of [0, 1].
 */
public class Mercator {
	/**
	 * Used for finding out how far two Locations will be in the mercator projection. Simply taking the euclidean distance, not only doesn't account for the curvature of the earth, it doesn't account for the distortions caused by the mercator projection. This function converts both points to mercator projections first, then computes their distance on a 1x1 Mercator Map.
	 * @param p1 first Location in Longitude, and Latitude space. ([-180, 180], [-90, 90])
	 * @param p2 second Location in Longitude, and Latitude space. ([-180, 180], [-90, 90])
	 * @return the distance in Meractor space ([0, 1], [0, 1])
	 */
	public static double mercatorDistance(Location p1, Location p2) {
		Location mp1 = globeToMercator(p1);
		Location mp2 = globeToMercator(p2);

		return mp1.distance(mp2);
	}

	/**
	 * Used for finding what the midpoint of two Locations will be when they are projected onto a Mercator map. Simply calculating the midpoint in euclidean space of the following points: (lng, lat) (0, 0) and (0, 85) would evalute to (0, 42.5). When we plot these three points, we would see that the midpoint is actually not in the center of these two points (due to the distortions caused by the mercator projection). So, this function, places both points into "mercator space", computes a midpoint, then brings that point back into "globe space".
	 * @param l1 first point
	 * @param l2 second point
	 * @return the midpoint of l1 and l2 in mercator space, projected into globe space.
	 */
	public static Location mercatorMidpoint(Location l1, Location l2) {
		Location mp1 = globeToMercator(l1);
		Location mp2 = globeToMercator(l2);

		return mercatorToGlobe(mp1.midpoint(mp2));
	}
	
	/** 
	 * Given a point in globe space (long, lat), project it into mercator space
	 * @param globe Location where getX() == long and getY() == lat
	 * @return point in mercator space ([0, 1], [0, 1])
	 */
	public static Location globeToMercator(Location globe) {
		return new Location(lonToMercatorX(globe.getX()), latToMercatorY(globe.getY()));
	}

	/**
	 * Given a point in mercator space ([0, 1], [0, 1]) project it into mercator space
	 * @param mercator Location on a mercator map
	 * @return that location on a globe
	 */
	public static Location mercatorToGlobe(Location mercator) {
		return new Location(mercatorXToLon(mercator.getX()), mercatorYToLat(mercator.getY()));
	}

	private static double mercatorXToLon(double x) {
		return (x*360) - 180d;
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

	private static double mercatorYToLat(double mercatorY) {
		double y = 0.5 - mercatorY;
		return 90 - 360 * Math.atan(Math.exp(-y * (2 * Math.PI))) / Math.PI;
	}

}
