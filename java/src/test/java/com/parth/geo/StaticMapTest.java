package com.parth.geo;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import java.awt.image.BufferedImage;

public class StaticMapTest {

	@Test
	public void testEquality() throws Exception {
		Location l1 = new Location(0.25, 0.25);
		Location l2 = new Location(0.49, 0.49);
		
		l1 = Mercator.mercatorToGlobe(l1);
		l2 = Mercator.mercatorToGlobe(l2);

		StaticMap map = new StaticMap(l2, l1);
		StaticMap map2 = new StaticMap(l1, l2);

		assertThat(map, is(map2));
	}

	@Test
	public void centerTest() throws Exception {
		Location l1 = new Location(0.25, 0.25);
		Location l2 = new Location(0.49, 0.49);

		Location expectedMidpoint = Mercator.mercatorToGlobe(l1.midpoint(l2));

		l1 = Mercator.mercatorToGlobe(l1);
		l2 = Mercator.mercatorToGlobe(l2);

		StaticMap map = new StaticMap(l1, l2);
		assertThat(map.getCenter(), is(expectedMidpoint));
		assertThat(map.getZoomLevel(), is(2));
	}

	@Test
	public void testGetLocation() throws Exception {
		Location l1 = Mercator.mercatorToGlobe(new Location(0.3, 0.3));
		Location l2 = Mercator.mercatorToGlobe(new Location(0.2, 0.2));

		Location mid = Mercator.mercatorMidpoint(l1, l2);

		StaticMap map = new StaticMap(l1, l2);
		BufferedImage bf = map.getMapImage();

		assertThat(map.getLocationInImage(mid), is(new Location(bf.getWidth() / 2, bf.getHeight()/2)));

		Location l3 = Mercator.mercatorToGlobe(new Location(0.1, 0.1));
		assertThat(map.getLocationInImage(l3), is(new Location(0, 0)));

		Location l4 = Mercator.mercatorToGlobe(new Location(0.4, 0.1));
		assertThat(map.getLocationInImage(l4), is(new Location(bf.getWidth(), 0)));

		Location l5 = Mercator.mercatorToGlobe(new Location(0.5, 0.5));
		assertThat(map.getLocationInImage(l5), is(new Location(bf.getWidth(), bf.getHeight())));

		Location l6 = Mercator.mercatorToGlobe(new Location(0.1, 0.4));
		assertThat(map.getLocationInImage(l6), is(new Location(0, bf.getHeight())));
	}
}
