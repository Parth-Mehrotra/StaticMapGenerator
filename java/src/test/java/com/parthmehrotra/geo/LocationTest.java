package com.parthmehrotra.geo;

import org.hamcrest.Matcher;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class LocationTest {
	@Test
	public void testEquality() {
		Location l1 = new Location(1, 2);
		Location l2 = new Location(1, 2);
		Location l3 = new Location(2, 2);

		assertThat(l1, is(l2));
		assertThat(l1, not(l3));
	}

	@Test
	public void testDistance() {
		Location l1 = new Location(0, 3);
		Location l2 = new Location(4, 0);
		
		assertThat(l1.distance(l2), is(5d));
	}

	@Test
	public void testScale() {
		Location l1 = new Location(1.3, 1.4).multiply(2);

		assertThat(l1, is(new Location(2.6, 2.8)));
	}

	@Test
	public void testMidpoint() {
		Location l1 = new Location(1.3, 1.4);
		Location l2 = new Location(-1.3, -1.4);

		assertThat(l1.midpoint(l2), is(new Location(0, 0)));
	}
}
