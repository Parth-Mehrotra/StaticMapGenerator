package com.parth.geo;

import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;

public class App {
	public static void main( String[] args) throws Exception {
		URL url = StaticMap.getImage(new Location(-73.982253, 40.753182), new Location(-74.558208, 40.501208));
		//URL url = StaticMap.getImage(new Location(-10, 0), new Location(180, 0));
		ImageIO.write(ImageIO.read(url), "png", new File("saved.png"));
	}
}
