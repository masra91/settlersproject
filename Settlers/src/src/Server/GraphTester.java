package Server;

import java.util.Iterator;

public class GraphTester {
	
	private static Graph g;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		g = new Graph();
		testTiles();
		testBuilds();
		testRoads();
	}
	
	private static void testTiles() {
		Iterator<TileNode> tileit;
		Iterator<BuildNode> buildit;
		Iterator<RoadNode> roadit;
		
		// 0th Tile
		tileit = g.getTileNode(0, 0).getTileNeighbors();
		buildit = g.getTileNode(0, 0).getBuildNeighbors();
		roadit = g.getTileNode(0, 0).getRoadNeighbors();
		printTiles(tileit, buildit, roadit, 0, 0);
		
		// 1st Tiles
		for (int i = 0; i < 6; i++) {
			tileit = g.getTileNode(1, i).getTileNeighbors();
			buildit = g.getTileNode(1, i).getBuildNeighbors();
			roadit = g.getTileNode(1, i).getRoadNeighbors();
			printTiles(tileit, buildit, roadit, 1, i);
		}
		
		// 2nd Tiles
		for (int i = 0; i < 12; i++) {
			tileit = g.getTileNode(2, i).getTileNeighbors();
			buildit = g.getTileNode(2, i).getBuildNeighbors();
			roadit = g.getTileNode(2, i).getRoadNeighbors();
			printTiles(tileit, buildit, roadit, 2, i);
		}
	}

	private static void printTiles(Iterator<TileNode> tileit, Iterator<BuildNode> buildit, Iterator<RoadNode> roadit, int i, int j) {
		System.out.println("On Tile " + g.getTileNode(i, j) + ":");
		System.out.print("Tiles: ");
		while(tileit.hasNext()) {
			System.out.print(tileit.next() + " ");
		}
		System.out.println();
		System.out.print("Builds: ");
		while (buildit.hasNext()) {
			System.out.print(buildit.next() + " ");
		}
		System.out.println();
		System.out.print("Roads: ");
		while(roadit.hasNext()) {
			System.out.print(roadit.next() + " ");
		}
		System.out.println();System.out.println();
	}
	
	private static void testBuilds() {
		Iterator<TileNode> tileit;
		Iterator<BuildNode> buildit;
		Iterator<RoadNode> roadit;
		
		// 0th line Builds
		for (int i = 0; i < 6; i++) {
			tileit = g.getBuildNode(0, i).getTileNeighbors();
			buildit = g.getBuildNode(0, i).getBuildNeighbors();
			roadit = g.getBuildNode(0, i).getRoadNeighbors();
			printBuilds(tileit, buildit, roadit, 0, i);
		}
		// 1st line Builds
		for (int i = 0; i < 18; i++) {
			tileit = g.getBuildNode(1, i).getTileNeighbors();
			buildit = g.getBuildNode(1, i).getBuildNeighbors();
			roadit = g.getBuildNode(1, i).getRoadNeighbors();
			printBuilds(tileit, buildit, roadit, 1, i);
		}
		// 2nd line Builds
		for (int i = 0; i < 30; i++) {
			tileit = g.getBuildNode(2, i).getTileNeighbors();
			buildit = g.getBuildNode(2, i).getBuildNeighbors();
			roadit = g.getBuildNode(2, i).getRoadNeighbors();
			printBuilds(tileit, buildit, roadit, 2, i);
		}
	}
	
	private static void printBuilds(Iterator<TileNode> tileit, Iterator<BuildNode> buildit, Iterator<RoadNode> roadit, int i, int j) {
		System.out.println("On Build " + g.getBuildNode(i, j) + ":");
		System.out.print("Tiles: ");
		while (tileit.hasNext()) {
			System.out.print(tileit.next() + " ");
		}
		System.out.println();System.out.print("Builds: ");
		while (buildit.hasNext()) {
			System.out.print(buildit.next() + " ");
		}
		System.out.println();System.out.print("Roads: ");
		while(roadit.hasNext()) {
			System.out.print(roadit.next() + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	private static void testRoads() {
		Iterator<TileNode> tiles;
		Iterator<BuildNode> builds;
		// 0th Roads
		for (int i = 0; i < 6; i++) {
			tiles = g.getRoadNode(0, i).getTileNeighbors();
			builds = g.getRoadNode(0, i).getBuildNeighbors();
			printRoads(tiles, builds, 0, i);
		}
		// 1st Roads
		for (int i = 0; i < 6; i++) {
			tiles = g.getRoadNode(1, i).getTileNeighbors();
			builds = g.getRoadNode(1, i).getBuildNeighbors();
			printRoads(tiles, builds, 1, i);
		}
		// 2nd Roads
		for (int i = 0; i < 18; i++) {
			tiles = g.getRoadNode(2, i).getTileNeighbors();
			builds = g.getRoadNode(2, i).getBuildNeighbors();
			printRoads(tiles, builds, 2, i);
		}
		// 3rd Roads
		for (int i = 0; i < 12; i++) {
			tiles = g.getRoadNode(3, i).getTileNeighbors();
			builds = g.getRoadNode(3, i).getBuildNeighbors();
			printRoads(tiles, builds, 3, i);
		}
		// 4th Roads
		for (int i = 0; i < 30; i++) {
			tiles = g.getRoadNode(4, i).getTileNeighbors();
			builds = g.getRoadNode(4, i).getBuildNeighbors();
			printRoads(tiles, builds, 4, i);
		}
	}
		
	private static void printRoads(Iterator<TileNode> tileit, Iterator<BuildNode> buildit, int i, int j) {
		System.out.println("On Road " + g.getRoadNode(i, j) + ":");
		System.out.print("Tiles: ");
		while(tileit.hasNext()) {
			System.out.print(tileit.next() + " ");
		}
		System.out.println();
		System.out.print("Builds: ");
		while(buildit.hasNext()) {
			System.out.print(buildit.next() + " ");
		}
		System.out.println();System.out.println();
	}
	
}
