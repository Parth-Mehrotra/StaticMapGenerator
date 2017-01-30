# StaticMapGenerator

A library that makes dealing with the [Math of the Mercator Projection](https://en.wikipedia.org/wiki/Mercator_projection#Mathematics_of_the_Mercator_projection) and the Google's [Static Maps Library](https://en.wikipedia.org/wiki/Mercator_projection#Mathematics_of_the_Mercator_projection) painless.

## Some nifty uses:

Simply converting points between Global and Mercator space:

```
Location home = new Location(102, 50);
Location mercator = Mercator.globeToMercator(home);
```

Finding the midpoint between two places as it would appear on a mercator map:

```
Location center = Mercator.mercatorMidpoint(new Location(0, 0), new Location(85, 0));
// center != (42.5, 0);
```

Getting a map from Google Static Maps Library with the maximum level of zoom which includes 2 given points:

```
Location home = new Location(0, 0);
Location work = new Location(80, 0); // Killer comute

StaticMap map = new StaticMap(home, work, KEY);
showBufferedImage(map.getMapImage()); // hypothetical function
```

Finding where a point would occur on a given StaticMap:

```
Location home = new Location(0, 0);
Location work = new Location(80, 0); // Killer comute

StaticMap map = new StaticMap(home, work, KEY);
showBufferedImage(map.getMapImage()); // hypothetical function

Location testPoint = map.getLocationInImage(new Location(40, 0)); // Won't be in the center of the image!
plot(map.getMapImage(), testPoint); // hypothetical function
```

## Usage

Project is pending addition to maven central. In the meantime you can use the following bintray maven repository:

Add the following to your `<repositories>`

```
<repository>
	<id>bintray-parth-mehrotra-staticmaps</id>
	<name>bintray</name>
	<url>http://dl.bintray.com/parth-mehrotra/staticmaps</url>
</repository>
```

and the following to your `<dependencies>`

```
<dependency>
	<groupId>com.parth.geo</groupId>
	<artifactId>StaticMaps</artifactId>
	<version>1.0</version>
</dependency>
```
