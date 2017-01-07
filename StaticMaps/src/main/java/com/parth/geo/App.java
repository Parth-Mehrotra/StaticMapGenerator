package com.parth.geo;

import java.io.*;
import javax.imageio.ImageIO;

public class App {
	public static void main( String[] args) throws Exception {
		ImageIO.write(ImageIO.read(StaticMap.getImage(0, 0, 1)), "png", new File("saved.png"));
	}
}
