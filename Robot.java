
public class Robot {

	//declaring constants to reference orientation
	private final int FACINGNORTH = 0;
	private final int FACINGEAST = 1;
	private final int FACINGSOUTH = 2;
	private final int FACINGWEST = 3;
	
	
	private Position position;
	private int orientation;
	private String instruction;
	private MarsMap marsMap;
	private boolean isLost;
	public Robot(Position position, String orientationString, String instruction, MarsMap marsMap)//needs to have a MarsMap aswell
	{
		this.position = position;
		orientation = adjustOrietation(orientationString);
		this.instruction = instruction;
		this.marsMap = marsMap;
		isLost = false;
	}
	
	//convert letter to it's respective int association
	public int adjustOrietation(String orientationString)
	{		
		if(orientationString.equals("N"))
		{
			return FACINGNORTH;
		}
		else if(orientationString.equals("E"))
		{
			return FACINGEAST;
		}
		else if(orientationString.equals("S"))
		{
			return FACINGSOUTH;
		}
		else if(orientationString.equals("W"))
		{
			return FACINGWEST;
		}
		else
		{
			return -1;
		}
	}
	
	//method to execute instructions for Robot
	public void robotInstructions(String instructions)
	{
		for(int i=0; i<instructions.length(); ++i) //loop through each char of the instruction (L,R,F)
		{
			if(instructions.charAt(i)=='L')
			{
				turnLeft();
			}
			else if(instructions.charAt(i)=='R')
			{
				turnRight();
			}
			else if(instructions.charAt(i)=='F')
			{
				if(isValidForwardMove() == false)
				{
					if(isLost == true) //end execution of the instructions if the Robot is lost
					{
						break;
					}
					else
					{
						continue; //need to skip this iteration
					}
					
				}
			}
		}
	}
	
	public String toString()
	{
		return this.position + " " + orientationToLetter(orientation) + " " + addLostStatement();
	}
	
	//adds "LOST" whether it's lost or not
	public String addLostStatement()
	{
		if(isLost == true)
		{
			return "LOST";
		}
		else
		{
			return "";
		}
	}
	
	//change orientation accordingly 
	public void turnLeft()
	{
		if(orientation == FACINGNORTH)
		{
			orientation = FACINGWEST;
		}
		else if(orientation == FACINGEAST)
		{
			orientation = FACINGNORTH;
		}
		else if(orientation == FACINGSOUTH)
		{
			orientation = FACINGEAST;
		}
		else if(orientation == FACINGWEST)
		{
			orientation = FACINGSOUTH;
		}
		
	}
	
	public void turnRight()
	{
		if(orientation == FACINGNORTH)
		{
			orientation = FACINGEAST;
		}
		else if(orientation == FACINGEAST)
		{
			orientation = FACINGSOUTH;
		}
		else if(orientation == FACINGSOUTH)
		{
			orientation = FACINGWEST;
		}
		else if(orientation == FACINGWEST)
		{
			orientation = FACINGNORTH;
		}
	}
	
	public boolean isValidForwardMove()
	{
		//check if the Robot's Position is a scentPosition
		for(Position scentPosition:marsMap.getScentPositions() )
		{
			if(position.equals(scentPosition)) //it is a scentPosition
			{
				Position newPosition = moveForward(); //moveForward
				if(isOnMap(newPosition) == true) //this newPosition is on the map
				{
					position = newPosition; //update the position of the Robot
					return true;
				}
				else
				{
					return false; //it's a scent position and the move foward causes it to go off the map. Therefore, skip this instruction
				}
			}
		}
		//don't know anything about this Position (it isn't a scentPosition)
		Position newPosition = moveForward();
		if(isOnMap(newPosition)==true)
		{
			position = newPosition;
			return true;
		}
		else //not on the map (therefore it is lost)
		{
			isLost = true;
			marsMap.addScentPosition(position); //add the position it was on to the collect of Scent Positions
			return false;
		}
	}
	
	//moves Robot one unit forward in the correct direction according to its orientation and return its new position
	public Position moveForward()
	{
		Position newPosition = new Position(0,0);
		if(orientation == FACINGNORTH)
		{
			newPosition.setX(position.getX());
			newPosition.setY(position.getY()+1);
		}
		else if(orientation == FACINGEAST)
		{
			newPosition.setX(position.getX()+1);
			newPosition.setY(position.getY());
		}
		else if(orientation == FACINGSOUTH)
		{
			newPosition.setX(position.getX());
			newPosition.setY(position.getY()-1);
		}
		else if(orientation == FACINGWEST)
		{
			newPosition.setX(position.getX()-1);
			newPosition.setY(position.getY());
		}
		return newPosition;
	}
	
	//method to check if the Position is on the map
	public boolean isOnMap(Position checkPosition)
	{
		if(checkPosition.getX()<0 || checkPosition.getX()>marsMap.getWidth() 
			|| checkPosition.getY()<0 || checkPosition.getY()>marsMap.getHeight())
		{
			return false; //not on map
		}
		return true;
	}
	
	//method to get the letter equivalent of the orientation
	public String orientationToLetter(int currentOrientation)
	{
		if(currentOrientation == 0)
		{
			return "N";
		}
		else if(currentOrientation == 1)
		{
			return "E";
		}
		else if(currentOrientation == 2)
		{
			return "S";
		}
		else if(currentOrientation ==3)
		{
			return "W";
		}
		else
		{
			return "";
		}
	}
	
}
