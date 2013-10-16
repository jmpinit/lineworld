import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import javax.sound.sampled.*;

public class Missile extends Entity
{
	int xPower;
	
	public Missile(World w, double p, double v, int x)
	{
		sprite = 'Y';
		
		sounds.add("./sfx/explosion1.wav");
		sounds.add("./sfx/explosion2.wav");
		sounds.add("./sfx/explosion3.wav");
		sounds.add("./sfx/explosion4.wav");
		sounds.add("./sfx/explosion5.wav");
		
		world = w;
		pos = p;
		vel = v;
		xPower = x;
	}
	
	public void update()
	{
		//handle collisions with world
		if(pos >= world.getSize()-1 || pos <= 0)
		{
			vel *= -1;
			destroy();
		}
		
		//handle collisions with other entities
		if(collides(this, Smoke.class, pos+vel))
		{
			vel *= -0.5;
			destroy();
		} else {
			pos += vel;
		}
	}
	
	public void destroy()
	{
		playSound();
		
		//emit gibs (smoke and fragments) randomly when the smoker is destroyed
		Random generator = new Random();
		int numGibs = generator.nextInt(2)+xPower;
		
		for(int i=0; i<numGibs; i++)
		{
			world.add(new Smoke(world, pos, generator.nextInt(4)-2+vel, generator.nextInt(5)+5, generator.nextInt(2)-1));
		}
		
		numGibs = generator.nextInt(2)+xPower;
		
		for(int i=0; i<numGibs; i++)
		{
			world.add(new Fragment(world, pos, generator.nextInt(4)-2+vel, generator.nextInt(5)+5, generator.nextInt(3)+1));
		}
	
		dead = true;
	}
}