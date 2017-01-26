import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException
	{
		
		BufferedReader input = new BufferedReader(new FileReader("input.txt")); //use the input txt file
        String line = input.readLine(); //read the first line (dimensions of Mars)
        int width = Integer.valueOf(line.substring(0, 1));
        int height = Integer.valueOf(line.substring(2, 3));
        MarsMap marsMap = new MarsMap(width,height);

        int xCo = 0;
        int yCo = 0;
        String orientation = "";
        String instruction = "";
        while(line!=null) //read the rest of the file
        {
        	line = input.readLine(); //contains the Robot's starting position and orientation
        
        	if(line==null)
        	{
        		break;
        	}
        	if(line.length()>0) //disregards empty lines (e.g used when separating one Robot's starting position and instruction from another)
        	{
        		xCo = Integer.valueOf(line.substring(0, 1));
            	yCo = Integer.valueOf(line.substring(2, 3));
            	orientation = line.substring(line.length()-1);
            	
            	instruction = input.readLine();
        		Robot robot = new Robot(new Position(xCo, yCo),orientation,instruction,marsMap);
        		
        		robot.robotInstructions(instruction); //robot executes its instructions
        		System.out.println(robot); //print the Robot's details
        	}
        	
        }
		
		input.close();

	}

}