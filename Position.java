
public class Position {

	private int xCo;
	private int yCo;
	
	//constructor
	public Position(int x, int y)
	{
		xCo = x;
		yCo = y;
	}
	
	//accessors
	public int getX()
	{
		return xCo;
	}
	
	public int getY()
	{
		return yCo;
	}
	
	//mutators
	public void setX(int x)
	{
		xCo = x;
	}
	
	public void setY(int y)
	{
		yCo = y;
	}
	
	//method to compare positions
	public boolean equals(Position checkPosition)
	{
		if(this.xCo == checkPosition.getX() && this.yCo == checkPosition.getY())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		return this.xCo + " " + this.yCo;
	}
	
	
	
	
	
}
