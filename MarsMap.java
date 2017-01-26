import java.util.ArrayList;

public class MarsMap {

	private int width;
	private int height;
	private ArrayList<Position> scentPositions; //collection of all Scent Positions
	
	public MarsMap(int width, int height)
	{
		this.width = width;
		this.height = height;
		this.scentPositions = new ArrayList<Position>();
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public ArrayList<Position> getScentPositions()
	{
		return scentPositions;
	}
	
	public void addScentPosition(Position scentPosition)
	{
		scentPositions.add(scentPosition);
	}
	
}
