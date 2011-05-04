package Server;

import java.util.ArrayList;
import java.util.Iterator;

public class GraphTester {
	
	private static Graph g;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		g = new Graph();
		//testTiles();
		//testBuilds();
		//testRoads();
		testValues();
	}
	
	private static void testTiles() {
		
		for (ArrayList<TileNode> list : g.tiles) {
			for (TileNode node : list) {
				System.out.println("On Tile " + node.toString() + ":");
				
				Iterator<TileNode> tileIt = node.getTileNeighbors();
				System.out.print("Tiles ");
				while(tileIt.hasNext()) {
					System.out.print(tileIt.next().toString() + " ");
				}
				System.out.println();
				
				Iterator<BuildNode> buildIt = node.getBuildNeighbors();
				System.out.print("Builds ");
				while(buildIt.hasNext()) {
					System.out.print(buildIt.next().toString() + " ");
				}
				System.out.println();
				
				Iterator<RoadNode> nodeIt = node.getRoadNeighbors();
				System.out.print("Roads ");
				while (nodeIt.hasNext()) {
					System.out.print(nodeIt.next().toString() + " ");
				}
				System.out.println();
				System.out.println();
				
			}
		}
	
	}
	
	private static void testBuilds() {
		
		for (ArrayList<BuildNode> buildList : g.builds) {
			for (BuildNode build : buildList) {
				System.out.println("On build " + build.toString() + ":");
				
				Iterator<TileNode> tileIt = build.getTileNeighbors();
				System.out.print("Tiles ");
				while (tileIt.hasNext()) {
					System.out.print(tileIt.next().toString() + " ");
				}
				System.out.println();
				
				Iterator<BuildNode> buildIt = build.getBuildNeighbors();	
				System.out.print("Builds ");
				while (buildIt.hasNext()) {
					System.out.print(buildIt.next().toString() + " ");
				}
				System.out.println();
				
				Iterator<RoadNode> roadIt = build.getRoadNeighbors();
				System.out.print("Roads ");
				while (roadIt.hasNext()) {
					System.out.print(roadIt.next() + " ");
				}
				System.out.println();
				System.out.println();
				
				
			}
		}
		
	}
	
	private static void testRoads() {
		
		for (ArrayList<RoadNode> roadList : g.roads) {
			for (RoadNode road : roadList) {
				System.out.println("On Road " + road.toString());
				
				Iterator<TileNode> tileIt = road.getTileNeighbors();
				System.out.print("Tiles ");
				while (tileIt.hasNext()) {
					System.out.print(tileIt.next().toString() + " ");
				}
				System.out.println();
				
				Iterator<BuildNode> buildIt = road.getBuildNeighbors();
				System.out.print("Builds ");
				while (buildIt.hasNext()) {
					System.out.print(buildIt.next().toString() + " ");
				}
				System.out.println();
				System.out.println();
				
			}
		}
		
	}
	
	private static void testValues() {
	
		for (int i = 0; i < 10; i++) {
			int[] tileTypes = new int[7];
			int[] rollValues = new int[13];
			for (int j = 0; j < tileTypes.length; j++) {
				tileTypes[j] = 0;
			}
			for (int j = 0; j < rollValues.length; j++) {
				rollValues[j] = 0;
			}
			Graph graph = new Graph();
			for (ArrayList<TileNode> tileList : graph.tiles) {
				for (TileNode tile : tileList) {
					tileTypes[tile.type()]++;
					rollValues[tile.rollValue()]++;
				}
			}
			for (int j = 0; j < tileTypes.length; j++) {
				System.out.print(tileTypes[j] + " ");
			}
			System.out.println();
			for (int j = 0; j < rollValues.length; j++) {
				System.out.print(rollValues[j] + " ");
			}
			System.out.println();
			System.out.println();
		}
		
	}
	
	/* Old Code
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
	*/
	
}
