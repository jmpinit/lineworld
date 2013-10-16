import java.util.*;

public class Bird extends Entity
{
	char[] spriteMap = {'W','M'};
	int frame = 0;
	
	double maxAccel = 0.5;
	
	int nervousness;

	public Bird(World w, double p, int n)
	{
		sprite = spriteMap[0];
	
		world = w;
		pos = p;
		nervousness = n;
	}
	
	public void update()
	{
		Random generator = new Random();
		
		//increment the animation
		if(frame<spriteMap.length-1)
		{
			frame++;
		} else {
			frame = 0;
		}
		
		sprite = spriteMap[frame];
		
		//apply nervousness factor to motion
		vel = generator.nextDouble()*nervousness-nervousness/2;
		
		//handle collisions with world
		if(pos >= world.getSize()-1)
		{
			pos = world.getSize()-1;
			vel *= -1;
		} else if(pos <= 0)
		{
			pos = 0;
			vel *= -1;
		}
		
		//handle collisions with other entities
		if(!collides(this, Smoke.class, pos+vel))
		{
			pos += vel;
		} else {
			vel *= -1;
		}
	}
	
	public void destroy()
	{
		dead = true;
	}
}