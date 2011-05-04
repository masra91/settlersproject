package Server;

import java.util.ArrayList;
import java.util.Iterator;

public class TileNode implements Node {
	
	private boolean robber;
	private byte type, portType, rollValue;
	private ArrayList<TileNode> tileNeighbors;
	private ArrayList<BuildNode> buildNeighbors;
	private ArrayList<RoadNode> roadNeighbors;
	private int i;
	private int j;
	
	public TileNode(byte type, byte rollValue, byte portType, int i, int j) {
		this.i = i;
		this.j = j;
		this.type = type;
		this.portType = portType;
		this.rollValue = rollValue;
		tileNeighbors = new ArrayList<TileNode>(6);
		buildNeighbors = new ArrayList<BuildNode>(6);
		roadNeighbors = new ArrayList<RoadNode>(6);
		if (type == 1)
			this.robber = true;
		else
			this.robber = false;
	}
	
	public Iterator<TileNode> getTileNeighbors() {
		return tileNeighbors.iterator();
	}
	
	public Iterator<BuildNode> getBuildNeighbors() {
		return buildNeighbors.iterator();
	}
	
	public Iterator<RoadNode> getRoadNeighbors() {
		return roadNeighbors.iterator();
	}
	
	// Methods on robber
	public void setRobber() {
		this.robber = true;
	}
	public void removeRobber() {
		this.robber = false;
	}
	public boolean hasRobber() {
		return this.robber;
	}
	
	// Value accessors
	public byte type() {
		return this.type;
	}
	public byte rollValue() {
		return this.rollValue;
	}
	public byte portType() {
		return this.portType;
	}
	
	// Used to add neighbors
	public void addTileNeighbor(TileNode tileNeighbor) {
		tileNeighbors.add(tileNeighbor);
	}
	public void addBuildNeighbor(BuildNode buildNeighbor) {
		buildNeighbors.add(buildNeighbor);
	}
	public void addRoadNeighbor(RoadNode roadNeighbor) {
		roadNeighbors.add(roadNeighbor);
		roadNeighbor.addTileNeighbor(this);
	}
	
	// Used to check if the node has neighbors
	public boolean hasTileNeighbor(TileNode tileNeighbor) {
		return tileNeighbors.contains(tileNeighbor);
	}
	public boolean hasBuildNeighbor(BuildNode buildNeighbor) {
		return buildNeighbors.contains(buildNeighbor);
	}
	public boolean hasRoadNeighbor(RoadNode roadNeighbor) {
		return roadNeighbors.contains(roadNeighbor);
	}
	
	public String toString() {
		return "(" + i + ", " + j + ")";
	}
	
	

}
