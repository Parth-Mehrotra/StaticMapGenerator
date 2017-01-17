package com.parth.geo;

import java.net.URL;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StaticMap {
	private BufferedImage image;
	private Location center;
	private int zoomLevel;
	private URL url;
	private static final int SCALE = 2;
	private static final int MAP_TILE_SIZE = 2;
	
	public StaticMap(Location l, int zoom) throws MalformedURLException, IOException {
		this.zoomLevel = zoom;
		this.center = l;
		this.url = getImage(this.center.getY(), this.center.getX(), this.zoomLevel);
		this.image = ImageIO.read(this.url);
	}

	public StaticMap(Location l1, Location l2) throws MalformedURLException, IOException {
		this.center = Mercator.mercatorToGlobe(Mercator.mercatorMidpoint(l1, l2));
		this.zoomLevel = calcZoom(l1, l2);
		this.url = getImage(this.center.getY(), this.center.getX(), this.zoomLevel);
		this.image = ImageIO.read(this.url);
	}

	public Location getLocationInImage(Location lngLat) {
		double mapSizeAtThisZoom = MAP_TILE_SIZE * Math.pow(2, this.zoomLevel);
		Location mercatorCenter = Mercator.globeToMercator(center).scale(mapSizeAtThisZoom);

		double localOriginX = mapSizeAtThisZoom - mercatorCenter.getX() - (MAP_TILE_SIZE / 2);
		double localOriginY = mapSizeAtThisZoom - mercatorCenter.getY() - (MAP_TILE_SIZE / 2);
		Location mercatorOrigin = new Location(localOriginX, localOriginY);

		Location mercator = Mercator.globeToMercator(lngLat).scale(mapSizeAtThisZoom);
		Location onImage = new Location(mercator.getX() - mercatorOrigin.getX(), mercator.getY() - mercatorOrigin.getY()).scale(SCALE);
		if (onImage.getX() < 0) {
			onImage.setX(0);
		}

		if (onImage.getY() < 0) {
			onImage.setY(0);
		}

		if (onImage.getX() > MAP_TILE_SIZE * SCALE) {
			onImage.setX(MAP_TILE_SIZE * SCALE);
		}

		if (onImage.getX() > MAP_TILE_SIZE * SCALE) { 
			onImage.setY(MAP_TILE_SIZE * SCALE);
		}

		return onImage;
		
	}

	private static URL getImage(double lat, double lon, int zoom) throws MalformedURLException, IOException {
		String request = baseUrl()
			+ (center(lat, lon))
			+ (zoom(zoom))
			+ (size(MAP_TILE_SIZE, MAP_TILE_SIZE))
			+ (scale(SCALE))
			+ (apiKey());

		return new URL(request);
	}

	private static int calcZoom(Location l1, Location l2) {
		Location l1M = Mercator.globeToMercator(l1);
		Location l2M = Mercator.globeToMercator(l2);
		double xDistance = l1M.getX() - l2M.getX();
		double yDistance = l1M.getY() - l2M.getY();

		double distance = Math.max(xDistance, yDistance);
		return (int) (-Math.floor(Math.log10(distance)/Math.log10(2))) - 1;
	}

	private static final String baseUrl() {
		return "https://maps.googleapis.com/maps/api/staticmap?";
	}

	private static final String center(double lat, double lon) {
		return "center=" + lat + "," + lon;
	}

	private static final String zoom(int z) {
		return "&zoom=" + z;
	}

	private static final String size(int width, int length) {
		return "&size=" + width + "x" + length;
	}

	private static final String scale(int n) {
		return "&scale=" + n;
	}

	private static final String apiKey() {
		return "&key=AIzaSyDN_rivWEZ8sTpUO8efQjZk_8dJ8d8qXuY";
	}

	public String toString() {
		return "[" + this.center + ", " + zoomLevel + "]";
	}
}
