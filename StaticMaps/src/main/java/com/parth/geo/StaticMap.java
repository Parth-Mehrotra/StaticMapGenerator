package com.parth.geo;

import java.net.URL;

import java.net.MalformedURLException;
import java.io.IOException;

public class StaticMap {
	public static URL getImage(double lat, double lon, double zoom) throws MalformedURLException, IOException {
		String request = baseUrl()
			+ (center(lat, lon))
			+ (zoom(zoom))
			+ (size(640, 640))
			+ (apiKey());

		return new URL(request);
	}

	private static final String baseUrl() {
		return "https://maps.googleapis.com/maps/api/staticmap?";
	}

	private static final String center(double lat, double lon) {
		return "center=" + lat + "," + lon;
	}

	private static final String zoom(double z) {
		return "&zoom=" + z;
	}

	private static final String size(int width, int length) {
		return "&size=" + width + "x" + length;
	}

	private static final String apiKey() {
		return "&key=AIzaSyDN_rivWEZ8sTpUO8efQjZk_8dJ8d8qXuY";
	}
}

