import java.util.*;

public class Smoke extends Entity
{
	char[] spriteMap = {'*','#','+'};

	int decayTime;
	double nervousness;
	
	public Smoke(World w, double p, double v, int d, double n)
	{
		world = w;
		pos = p;
		vel = v;
		decayTime = d;
		nervousness = n;
	}
	
	public void update()
	{
		Random generator = new Random();
		
		//give smoke random sprite
		sprite = spriteMap[generator.nextInt(spriteMap.length)];
		
		//apply nervousness factor to motion
		vel += generator.nextDouble()*nervousness-nervousness/2;
		
		//kill when too old
		if(decayTime<=0)
		{
			destroy();
		} else {
			decayTime--;
		}
		
		//handle collisions with world
		if(pos >= world.getSize()-1 || pos <= 0)
		{
			destroy();
		}
		
		//smoke should always move
		pos += vel;
	}
	
	public void destroy()
	{
		dead = true;
	}
}