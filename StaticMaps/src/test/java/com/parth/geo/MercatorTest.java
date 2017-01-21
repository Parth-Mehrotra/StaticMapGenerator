package com.parth.geo;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class MercatorTest {

	@Test
	public void testMercatorToGlobe() {
		Location l1 = new Location(1, 1);
		assertThat(Mercator.mercatorToGlobe(l1).getX(), is(180d));
		assertEquals(Mercator.mercatorToGlobe(l1).getY(), -85, 1);

		l1.setX(0).setY(0);
		assertThat(Mercator.mercatorToGlobe(l1).getX(), is(-180d));
		assertEquals(Mercator.mercatorToGlobe(l1).getY(), 85, 1);

		assertThat(Mercator.mercatorToGlobe(new Location(0.5, 0.5)), is(new Location(0, 0)));
	}

	@Test
	public void testGlobeToMercator() {
		Location l1 = new Location(180, -85);
		assertEquals(Mercator.globeToMercator(l1).getX(), 1, 0.01);
		assertEquals(Mercator.globeToMercator(l1).getY(), 1, 0.01);

		l1.setX(0).setY(0);
		assertEquals(Mercator.globeToMercator(l1).getX(), 0.5, 0.01);
		assertEquals(Mercator.globeToMercator(l1).getY(), 0.5, 0.01);

		l1.setX(-180).setY(85);
		assertEquals(Mercator.globeToMercator(l1).getX(), 0, 0.01);
		assertEquals(Mercator.globeToMercator(l1).getY(), 0, 0.01);
	}

	@Test
	public void testInverseness() {
		for (double x = 0; x <= 1; x+= 0.001) {
			for (double y = 0; y <= 1; y += 0.001) {
				Location l1 = new Location(x, y);
				Location l2 = Mercator.mercatorToGlobe(l1);
				Location l3 = Mercator.globeToMercator(l2);

				assertEquals(l1.getX(), l3.getX(), 0.00001);
				assertEquals(l1.getY(), l3.getY(), 0.00001);
			}
		}
	}

	@Test
	public void testMercatorMidpoint() {
		Location l1 = new Location(0, 0);
		Location l2 = new Location(0, 85);
		
		Location l3 = Mercator.mercatorMidpoint(l1, l2);
		assertEquals(Mercator.globeToMercator(l3).getX(), 0.5, 0.001);
		assertEquals(Mercator.globeToMercator(l3).getY(), 0.25, 0.001);
	}

	@Test
	public void testMercatorDistance() {
		Location l1 = new Location(0, 0);
		Location l2 = new Location(0.01, 0.01);
		double distance = l1.distance(l2);

		l1 = Mercator.mercatorToGlobe(l1);
		l2 = Mercator.mercatorToGlobe(l2);

		assertEquals(Mercator.mercatorDistance(l1, l2), distance, 0.001);
	}
}
