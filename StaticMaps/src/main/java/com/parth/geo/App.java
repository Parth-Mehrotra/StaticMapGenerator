package com.parth.geo;

import java.io.*;
import javax.imageio.ImageIO;
import java.net.URL;

public class App {
	public static void main( String[] args) throws Exception {
		StaticMap test = new StaticMap(new Location(0, 0), new Location(0, 84));
		System.out.println(test);
	}
}
