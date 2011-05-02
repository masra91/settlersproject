package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {
	
	ArrayList<ArrayList<TileNode>> tiles;
	ArrayList<ArrayList<BuildNode>> builds;
	ArrayList<ArrayList<RoadNode>> roads;
	
	public Graph() {
		makeTiles();
		makeBuilds();
		makeRoads();
		linkTiles();
		linkBuilds();
		linkRoads();
	}
	
	public TileNode getTileNode(int i, int j) {
		try {
			return tiles.get(i).get(j);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public BuildNode getBuildNode(int i, int j) {
		try {
			return builds.get(i).get(j);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public RoadNode getRoadNode(int i, int j) {
		try {
			return roads.get(i).get(j);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private void makeTiles() {
		// These lists serve to randomize the tile values.
		LinkedList<Integer> tileTypes = buildTileTypes();
		LinkedList<Integer> rollValues = buildRollValues();
		LinkedList<Integer> portTypes = buildPortTypes();
		tiles = new ArrayList<ArrayList<TileNode>>(4);
		tiles.add(new ArrayList<TileNode>(1));
		tiles.add(new ArrayList<TileNode>(6));
		tiles.add(new ArrayList<TileNode>(12));
		tiles.add(new ArrayList<TileNode>(18));
		tiles.get(0).add(new TileNode(tileTypes.pop().byteValue(), rollValues.pop().byteValue(), (byte)0, 0, 0));
		for (int i = 0; i < 6; i++) {
			tiles.get(1).add(new TileNode(tileTypes.pop().byteValue(), rollValues.pop().byteValue(), (byte)0, 1, i));
		}
		for (int i = 0; i < 12; i++	) {
			tiles.get(2).add(new TileNode(tileTypes.pop().byteValue(), rollValues.pop().byteValue(), (byte)0, 2, i));
		}
		for (int i = 0; i < 18; i++) {
			if (i%2 != 0)
				tiles.get(3).add(new TileNode((byte)0, (byte)0, portTypes.pop().byteValue(), 3, i));
			else
				tiles.get(3).add(new TileNode((byte)0, (byte)0, (byte)0, 3, i));
		}
	}
	
	private void makeBuilds() {
		builds = new ArrayList<ArrayList<BuildNode>>(3);
		builds.add(new ArrayList<BuildNode>(6));
		builds.add(new ArrayList<BuildNode>(18));
		builds.add(new ArrayList<BuildNode>(30));
		for (int i = 0; i < 6; i++) {
			builds.get(0).add(new BuildNode(0, i));
		}
		for (int i = 0; i < 18; i++) {
			builds.get(1).add(new BuildNode(1, i));
		}
		for (int i = 0; i < 30; i++) {
			builds.get(2).add(new BuildNode(2, i));
		}
	}
	
	private void makeRoads() {
		roads = new ArrayList<ArrayList<RoadNode>>(5);
		roads.add(new ArrayList<RoadNode>(6));
		roads.add(new ArrayList<RoadNode>(6));
		roads.add(new ArrayList<RoadNode>(18));
		roads.add(new ArrayList<RoadNode>(12));
		roads.add(new ArrayList<RoadNode>(30));
		for(int i = 0; i < 6; i++) {
			roads.get(0).add(new RoadNode(0, i));
			roads.get(1).add(new RoadNode(1, i));
		}
		for (int i = 0; i < 18; i++) {
			roads.get(2).add(new RoadNode(2, i));
		}
		for (int i = 0; i < 12; i++) {
			roads.get(3).add(new RoadNode(3, i));
		}
		for(int i = 0; i < 30; i++) {
			roads.get(4).add(new RoadNode(4, i));
		}
	}
	
	// Iterates over every tile node and adds pointers to the appropriate nodes.
	private void linkTiles() {
		
		// 0th line of tiles...
		for (int i = 0; i < 6; i++) {
			// Tiles from the 1st line
			tiles.get(0).get(0).addTileNeighbor(tiles.get(1).get(i));
			// Builds from the 0th line
			tiles.get(0).get(0).addBuildNeighbor(builds.get(0).get(i));
			// Roads from 0th line
			tiles.get(0).get(0).addRoadNeighbor(roads.get(0).get(i));
		}
		
		// 1st line of tiles...
		int secondRoadLineCount = -1;
		int secondTileLineCount = -1;
		int zerothBuildLineCount = 0;
		int firstBuildLineCount = 0;
		for (int i = 0; i < 6; i++) {
			// Tiles from the 0th line
			tiles.get(1).get(i).addTileNeighbor(tiles.get(0).get(0));
			// Tiles from the 1st line
			tiles.get(1).get(i).addTileNeighbor(tiles.get(1).get((i+5)%6));
			tiles.get(1).get(i).addTileNeighbor(tiles.get(1).get((i+7)%6));
			// Tiles from the 2nd line
			tiles.get(1).get(i).addTileNeighbor(tiles.get(2).get(((secondTileLineCount++)+12)%12));
			tiles.get(1).get(i).addTileNeighbor(tiles.get(2).get(((secondTileLineCount++)+12)%12));
			tiles.get(1).get(i).addTileNeighbor(tiles.get(2).get(((secondTileLineCount++)+12)%12));
			secondTileLineCount--;
			
			// Builds from the 0th line
			tiles.get(1).get(i).addBuildNeighbor(builds.get(0).get(((zerothBuildLineCount++)+6)%6));
			tiles.get(1).get(i).addBuildNeighbor(builds.get(0).get((zerothBuildLineCount+6)%6));
			// Builds from the 1st line
			tiles.get(1).get(i).addBuildNeighbor(builds.get(1).get(((firstBuildLineCount++)+18)%18));
			tiles.get(1).get(i).addBuildNeighbor(builds.get(1).get(((firstBuildLineCount++)+18)%18));
			tiles.get(1).get(i).addBuildNeighbor(builds.get(1).get(((firstBuildLineCount++)+18)%18));
			tiles.get(1).get(i).addBuildNeighbor(builds.get(1).get((firstBuildLineCount+18)%18));
			
			// Roads from 0th line
			tiles.get(1).get(i).addRoadNeighbor(roads.get(0).get(i));
			// Roads from 1st line
			tiles.get(1).get(i).addRoadNeighbor(roads.get(1).get(i));
			tiles.get(1).get(i).addRoadNeighbor(roads.get(1).get((i+7)%6));
			// Roads from 2nd line
			tiles.get(1).get(i).addRoadNeighbor(roads.get(2).get(((secondRoadLineCount++)+18)%18));
			tiles.get(1).get(i).addRoadNeighbor(roads.get(2).get(((secondRoadLineCount++)+18)%18));
			tiles.get(1).get(i).addRoadNeighbor(roads.get(2).get(((secondRoadLineCount++)+18)%18));
		}
		
		// 2nd line of tiles...
		int secondLineOffset = 0;
		int fourthRoadLineCount = -1;
		int firstTileLineCount = 0;
		firstBuildLineCount = 1;
		int secondBuildLineCount = 0;
		for (int i = 0; i < 12; i++) {
			// Tiles from the 1st line
			if (i%2 == 0) {
				tiles.get(2).get(i).addTileNeighbor(tiles.get(1).get(firstTileLineCount));
			} else {
				tiles.get(2).get(i).addTileNeighbor(tiles.get(1).get(firstTileLineCount++));
				tiles.get(2).get(i).addTileNeighbor(tiles.get(1).get((firstTileLineCount+6)%6));
			}
			// Tiles from the 2nd line
			tiles.get(2).get(i).addTileNeighbor(tiles.get(2).get((i+11)%12));
			tiles.get(2).get(i).addTileNeighbor(tiles.get(2).get((i+13)%12));
			
			// Builds from the 1st line
			if (i%2 == 0) {
				tiles.get(2).get(i).addBuildNeighbor(builds.get(1).get(((firstBuildLineCount++)+18)%18));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(1).get((firstBuildLineCount+18)%18));
			} else {
				tiles.get(2).get(i).addBuildNeighbor(builds.get(1).get(((firstBuildLineCount++)+18)%18));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(1).get(((firstBuildLineCount++)+18)%18));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(1).get((firstBuildLineCount+18)%18));
			}
			// Builds from the 2nd line
			if (i%2 == 0) {
				tiles.get(2).get(i).addBuildNeighbor(builds.get(2).get(((secondBuildLineCount++)+30)%30));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(2).get(((secondBuildLineCount++)+30)%30));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(2).get(((secondBuildLineCount++)+30)%30));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(2).get((secondBuildLineCount+30)%30));
			} else {
				tiles.get(2).get(i).addBuildNeighbor(builds.get(2).get(((secondBuildLineCount++)+30)%30));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(2).get(((secondBuildLineCount++)+30)%30));
				tiles.get(2).get(i).addBuildNeighbor(builds.get(2).get((secondBuildLineCount+30)%30));
			}
			
			// Roads from 2nd line
			if (i%2 == 0) {
				tiles.get(2).get(i).addRoadNeighbor(roads.get(2).get(i+secondLineOffset));
			} else {
				tiles.get(2).get(i).addRoadNeighbor(roads.get(2).get(i+secondLineOffset));
				tiles.get(2).get(i).addRoadNeighbor(roads.get(2).get(i+secondLineOffset+1));
				secondLineOffset++;
			}
			// Roads from 3rd line
			tiles.get(2).get(i).addRoadNeighbor(roads.get(3).get(i));
			tiles.get(2).get(i).addRoadNeighbor(roads.get(3).get((i+13)%12));
			// Roads from 4th line
			if (i%2 == 0) {
				tiles.get(2).get(i).addRoadNeighbor(roads.get(4).get(((fourthRoadLineCount++) + 30)%30));
				tiles.get(2).get(i).addRoadNeighbor(roads.get(4).get(((fourthRoadLineCount++) + 30)%30));
				tiles.get(2).get(i).addRoadNeighbor(roads.get(4).get(((fourthRoadLineCount++) + 30)%30));
			} else {
				tiles.get(2).get(i).addRoadNeighbor(roads.get(4).get(fourthRoadLineCount++));
				tiles.get(2).get(i).addRoadNeighbor(roads.get(4).get(fourthRoadLineCount++));
			}
		}
		
	}
	
	// Iterates over every build node and adds pointers to the appropriate nodes.
	private void linkBuilds() {
		
		// 0th line Builds
		int firstLineBuildCount = 0;
		for (int i = 0; i < 6; i++) {
			// 0th line Tile
			builds.get(0).get(i).addTileNeighbor(tiles.get(0).get(0));
			// 1st line Tiles
			builds.get(0).get(i).addTileNeighbor(tiles.get(1).get((i+5)%6));
			builds.get(0).get(i).addTileNeighbor(tiles.get(1).get((i+6)%6));
			
			// 0th line Builds
			builds.get(0).get(i).addBuildNeighbor(builds.get(0).get((i+5)%6));
			builds.get(0).get(i).addBuildNeighbor(builds.get(0).get((i+7)%6));
			// 1st line Builds
			builds.get(0).get(i).addBuildNeighbor(builds.get(1).get(firstLineBuildCount));
			firstLineBuildCount+=3;
			
			// 0th line Roads
			builds.get(0).get(i).addRoadNeighbor(roads.get(0).get((i+5)%6));
			builds.get(0).get(i).addRoadNeighbor(roads.get(0).get(i));
			// 1st line Roads
			builds.get(0).get(i).addRoadNeighbor(roads.get(1).get(i));
		}
		
		// 1st line Builds
		int firstLineTileCount = -1;
		int secondLineTileCount = -1;
		int zerothLineBuildCount = 0;
		firstLineBuildCount = 0;
		boolean buildFlip = true;
		int secondLineBuildCount = 0;
		int firstLineRoadCount = 0;
		int thirdLineRoadCount = 0;
		for (int i = 0; i < 18; i++) {
			// 1st line Tiles
			if (i%3 == 0) {
				builds.get(1).get(i).addTileNeighbor(tiles.get(1).get(((firstLineTileCount++)+6)%6));
				builds.get(1).get(i).addTileNeighbor(tiles.get(1).get(firstLineTileCount));
				builds.get(1).get(i+1).addTileNeighbor(tiles.get(1).get(firstLineTileCount));
				builds.get(1).get(i+2).addTileNeighbor(tiles.get(1).get(firstLineTileCount));
			}
			// 2nd line Tiles
			if (i%3 == 0) {
				builds.get(1).get(i).addTileNeighbor(tiles.get(2).get((secondLineTileCount+12)%12));
			} else {
				builds.get(1).get(i).addTileNeighbor(tiles.get(2).get(((secondLineTileCount++)+12)%12));
				builds.get(1).get(i).addTileNeighbor(tiles.get(2).get((secondLineTileCount+12)%12));
			}
			
			// 0th line Builds
			if (i%3 == 0) {
				builds.get(1).get(i).addBuildNeighbor(builds.get(0).get(zerothLineBuildCount++));
			}
			// 1st line Builds
			builds.get(1).get(i).addBuildNeighbor(builds.get(1).get((firstLineBuildCount+17)%18));
			builds.get(1).get(i).addBuildNeighbor(builds.get(1).get(((firstLineBuildCount++)+19)%18));
			// 2nd line Builds
			if (i%3!=0) {
				if (buildFlip) {
					builds.get(1).get(i).addBuildNeighbor(builds.get(2).get(secondLineBuildCount));
					secondLineBuildCount+=3;
					buildFlip = !buildFlip;
				} else {
					builds.get(1).get(i).addBuildNeighbor(builds.get(2).get(secondLineBuildCount));
					secondLineBuildCount+=2;
					buildFlip = !buildFlip;
				}
			}
			
			// 1st line Roads
			if (i%3==0) {
				builds.get(1).get(i).addRoadNeighbor(roads.get(1).get(firstLineRoadCount++));
			}
			// 2nd line Roads
			builds.get(1).get(i).addRoadNeighbor(roads.get(2).get((i+16)%18));
			builds.get(1).get(i).addRoadNeighbor(roads.get(2).get((i+17)%18));
			// 3rd line Roads
			if (i%3!=0) {
				builds.get(1).get(i).addRoadNeighbor(roads.get(3).get(thirdLineRoadCount++));
			}
		}
		
		// 2nd line Builds
		secondLineTileCount = -1;
		firstLineBuildCount = 1;
		thirdLineRoadCount = 0;
		for (int i = 0; i < 30; i++) {
			// 2nd line Tiles
			if (i%5 == 0) {
				builds.get(2).get(i).addTileNeighbor(tiles.get(2).get(((secondLineTileCount++)+12)%12));
				builds.get(2).get(i).addTileNeighbor(tiles.get(2).get((secondLineTileCount+12)%12));
				builds.get(2).get(i+1).addTileNeighbor(tiles.get(2).get((secondLineTileCount+12)%12));
				builds.get(2).get(i+2).addTileNeighbor(tiles.get(2).get((secondLineTileCount+12)%12));
				builds.get(2).get(i+3).addTileNeighbor(tiles.get(2).get(((secondLineTileCount++)+12)%12));
				builds.get(2).get(i+3).addTileNeighbor(tiles.get(2).get((secondLineTileCount+12)%12));
				builds.get(2).get(i+4).addTileNeighbor(tiles.get(2).get((secondLineTileCount+12)%12));
			}
			
			// 1st line Builds
			if (i%5 == 0) {
				builds.get(2).get(i).addBuildNeighbor(builds.get(1).get(firstLineBuildCount++));
				builds.get(2).get(i+3).addBuildNeighbor(builds.get(1).get(firstLineBuildCount));
				firstLineBuildCount+=2;
			}
			// 2nd line Builds
			builds.get(2).get(i).addBuildNeighbor(builds.get(2).get((i+29)%30));
			builds.get(2).get(i).addBuildNeighbor(builds.get(2).get((i+31)%30));
			
			// 3rd line Roads
			if (i%5 == 0) {
				builds.get(2).get(i).addRoadNeighbor(roads.get(3).get(thirdLineRoadCount++));
				builds.get(2).get(i+3).addRoadNeighbor(roads.get(3).get(thirdLineRoadCount++));
			}
			// 4th line Roads
			builds.get(2).get(i).addRoadNeighbor(roads.get(4).get((i+28)%30));
			builds.get(2).get(i).addRoadNeighbor(roads.get(4).get((i+29)%30));
		}
	}
	
	// Iterates over every road node and adds pointers to the appropriate nodes.
	public void linkRoads()	{
		// 0th line Roads
		for (int i = 0; i < 6; i++) {
			// 0th line Tile
			roads.get(0).get(i).addTileNeighbor(tiles.get(0).get(0));
			// 1st line Tile
			roads.get(0).get(i).addTileNeighbor(tiles.get(1).get(i));
			
			// 0th line Build
			roads.get(0).get(i).addBuildNeighbor(builds.get(0).get((i+6)%6));
			roads.get(0).get(i).addBuildNeighbor(builds.get(0).get((i+7)%6));
		}
		
		// 1st line Roads
		int firstLineBuildCount = 0;
		for (int i = 0; i < 6; i++) {
			// 1st line Tile
			roads.get(1).get(i).addTileNeighbor(tiles.get(1).get((i+5)%6));
			roads.get(1).get(i).addTileNeighbor(tiles.get(1).get((i+6)%6));
			
			// 0th line Build
			roads.get(1).get(i).addBuildNeighbor(builds.get(0).get(i));
			// 1st line Build
			roads.get(1).get(i).addBuildNeighbor(builds.get(1).get(firstLineBuildCount));
			firstLineBuildCount+=3;
		}
		
		// 2nd line Roads
		int firstLineTileCount = 0;
		int secondLineTileCount = 0;
		for (int i = 0; i < 18; i++) {
			// 1st line Tiles
			if (i%3 == 0) {
				roads.get(2).get((i+17)%18).addTileNeighbor(tiles.get(1).get(firstLineTileCount));
				roads.get(2).get((i+18)%18).addTileNeighbor(tiles.get(1).get(firstLineTileCount));
				roads.get(2).get((i+19)%18).addTileNeighbor(tiles.get(1).get(firstLineTileCount++));
			}
			// 2nd line Tiles
			if (i%3 == 0) {
				roads.get(2).get(i).addTileNeighbor(tiles.get(2).get(secondLineTileCount++));
				roads.get(2).get(i+1).addTileNeighbor(tiles.get(2).get(secondLineTileCount));
				roads.get(2).get(i+2).addTileNeighbor(tiles.get(2).get(secondLineTileCount++));
			}
			// 1st line Builds
			roads.get(2).get(i).addBuildNeighbor(builds.get(1).get((i+19)%18));
			roads.get(2).get(i).addBuildNeighbor(builds.get(1).get((i+20)%18));
		}
		
		// 3rd line Roads
		firstLineBuildCount = 1;
		int secondLineBuildCount = 0;
		for (int i = 0; i < 12; i++) {
			// 2nd line Tiles
			roads.get(3).get(i).addTileNeighbor(tiles.get(2).get((i+11)%12));
			roads.get(3).get(i).addTileNeighbor(tiles.get(2).get((i+12)%12));
			
			// 1st line Builds
			if (i%2 == 0) {
				roads.get(3).get(i).addBuildNeighbor(builds.get(1).get(firstLineBuildCount++));
				roads.get(3).get(i+1).addBuildNeighbor(builds.get(1).get(firstLineBuildCount));
				firstLineBuildCount+=2;
			}
			// 2nd line Builds
			if (i%2 == 0) {
				roads.get(3).get(i).addBuildNeighbor(builds.get(2).get(secondLineBuildCount));
				secondLineBuildCount+=3;
				roads.get(3).get(i+1).addBuildNeighbor(builds.get(2).get(secondLineBuildCount));
				secondLineBuildCount+=2;
			}
		}
		
		// 4th line Roads
		secondLineTileCount = 0;
		for (int i = 0; i < 30; i++) {
			// 2nd line Tiles
			if (i%5==0) {
				roads.get(4).get((i+29)%30).addTileNeighbor(tiles.get(2).get(secondLineTileCount));
				roads.get(4).get((i+30)%30).addTileNeighbor(tiles.get(2).get(secondLineTileCount));
				roads.get(4).get((i+31)%30).addTileNeighbor(tiles.get(2).get(secondLineTileCount++));
				roads.get(4).get((i+32)%30).addTileNeighbor(tiles.get(2).get(secondLineTileCount));
				roads.get(4).get((i+33)%30).addTileNeighbor(tiles.get(2).get(secondLineTileCount++));
			}
				
			// 2nd line Builds
			roads.get(4).get(i).addBuildNeighbor(builds.get(2).get((i+31)%30));
			roads.get(4).get(i).addBuildNeighbor(builds.get(2).get((i+32)%30));
		}
		
	}
	
	/*
	 * Builds a list of tile types and shuffles it.
	 * 4 of 1, 2, 3
	 * 3 of 5, 6
	 * 1 of 1
	 */
	private LinkedList<Integer> buildTileTypes() {
		LinkedList<Integer> tileTypes = new LinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			if (i < 3) {
				tileTypes.add(5);
				tileTypes.add(6);
			}
			tileTypes.add(1);
			tileTypes.add(2);
			tileTypes.add(3);
		}
		tileTypes.add(1);
		Collections.shuffle(tileTypes);
		return tileTypes;
	}
	
	/*
	 * Builds a list of roll values and shuffles it. 
	 * 1 of 2
	 * 1 of 12
	 * 2 of 3 through 11
	 */
	private LinkedList<Integer> buildRollValues() {
		LinkedList<Integer> rollValues = new LinkedList<Integer>();
		rollValues.add(2);
		rollValues.add(12);
		for (int i = 3; i <= 11; i++) {
			rollValues.add(i);
			rollValues.add(i);
		}
		Collections.shuffle(rollValues);
		return rollValues;
	}
	
	/*
	 * Builds a list of port types and shuffles it.
	 * 4 of 1
	 * 1 of 2 through 6
	 */
	private LinkedList<Integer> buildPortTypes() {
		LinkedList<Integer> portTypes = new LinkedList<Integer>(); 
		for (int i = 0; i < 4; i++) {
			portTypes.add(1);
		}
		portTypes.add(2);
		portTypes.add(3);
		portTypes.add(4);
		portTypes.add(5);
		portTypes.add(6);
		Collections.shuffle(portTypes);
		return portTypes;
		
	}

}
