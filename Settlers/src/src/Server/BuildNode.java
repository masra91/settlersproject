package Server;

import java.util.ArrayList;

public class BuildNode implements Node {
	
	private byte owner;
	private byte type;
	private ArrayList<TileNode> tileNeighbors;
	private ArrayList<BuildNode> buildNeighbors;
	private ArrayList<RoadNode> roadNeighbors;
	
	public BuildNode() {
		tileNeighbors = new ArrayList<TileNode>(3);
		buildNeighbors = new ArrayList<BuildNode>(3);
		roadNeighbors = new ArrayList<RoadNode>(3);
		this.owner = 0;
		this.type = 0;
	}
	
	// Methods on owner
	public void setOwner(byte owner) {
		this.owner = owner;
	}
	public byte owner()	{
		return this.owner;
	}
	
	// Accessors
	public byte type() {
		return this.type;
	}
	
	// Builds
	public boolean build() {
		if (type >= 2){
			return false;
		}
		else {
			type++;
			return true;
		}
	}
	
	// Used to add neighbors.
	public void addTileNeighbor(TileNode tileNeighbor) {
		tileNeighbors.add(tileNeighbor);
	}
	public void addBuildNeighbor(BuildNode buildNeighbor) {
		buildNeighbors.add(buildNeighbor);
	}
	public void addRoadNeighbor(RoadNode roadNeighbor) {
		roadNeighbors.add(roadNeighbor);
	}
	
	// Used to check if the node has neighbors.
	public boolean hasTileNeighbor(TileNode tileNeighbor) {
		return tileNeighbors.contains(tileNeighbor);
	}
	public boolean hasBuildNeighbor(BuildNode buildNeighbor) {
		return buildNeighbors.contains(buildNeighbor);
	}
	public boolean hasRoadNeighbor(RoadNode roadNeighbor) {
		return roadNeighbors.contains(roadNeighbor);
	}
	
}
