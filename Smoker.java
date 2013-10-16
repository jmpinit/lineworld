import java.util.*;
import sun.audio.*;
import java.io.*;

public class Smoker extends Entity
{
	double nervousness;
	int emission;
	int xPower;
	
	public Smoker(World w, double p, double v, double n, int e, int x)
	{
		sprite = 'O';
		
		sounds.add("./sfx/explosion1.wav");
		sounds.add("./sfx/explosion2.wav");
		sounds.add("./sfx/explosion3.wav");
		sounds.add("./sfx/explosion4.wav");
		sounds.add("./sfx/explosion5.wav");
		
		world = w;
		pos = p;
		vel = v;
		emission = e;
		xPower = x;
	}
	
	public void update()
	{
		Random generator = new Random();
		
		//apply nervousness factor to motion
		vel += generator.nextDouble()*nervousness-nervousness/2;
		
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
		if(collides(this, Smoke.class, pos+vel))
		{
			destroy();
		} else {
			pos += vel;
		}
		
		//randomly emit smoke
		if(generator.nextInt(emission)==0)
		{
			world.add(new Smoke(world, pos, generator.nextInt(4)-2, generator.nextInt(10), generator.nextInt(2)-1));
		}
	}
	
	public void destroy()
	{
		playSound();
		
		//emit gibs (smoke and fragments) randomly when the smoker is destroyed
		Random generator = new Random();
		int numGibs = generator.nextInt(5)+5;
		
		for(int i=0; i<numGibs; i++)
		{
			world.add(new Smoke(world, pos, generator.nextInt(4)-2, generator.nextInt(10), generator.nextInt(2)-1));
		}
		
		numGibs = generator.nextInt(5)+5;
		
		for(int i=0; i<numGibs; i++)
		{
			world.add(new Fragment(world, pos, generator.nextInt(4)-2, generator.nextInt(5)+3, generator.nextInt(3)+1));
		}
		
		dead = true;
	}
}