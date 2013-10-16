import java.io.*;

public class Ship extends Entity
{
	DataInputStream keyboard = new DataInputStream(System.in);

	public Ship(World w, double p)
	{
		world = w;
		pos = p;
	}
	
	public void update()
	{
		char key = 0;
		
		try
		{
			key = (char)keyboard.read();
		} catch(Exception e)
		{
			System.out.println(e.toString());
		}
		
		System.out.println(key);
	}
	
	public void destroy()
	{
	
	}
}