package com.parth.geo;

import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;

public class App {
	public static void main( String[] args) throws Exception {
		URL url = StaticMap.getImage(new Location(0, 0), 1);
		ImageIO.write(ImageIO.read(url), "png", new File("saved.png"));
		Mercator.mercatorDistance(new Location(180, 0), new Location(0, 0));
	}
}
