import java.util.*;

public class Fragment extends Entity
{
	char[] spriteMap = {'.',',','`','~','-','\'','"',';',':'};
	
	int decayTime;
	int tumbleRate;
	
	int frameCount = 0;

	public Fragment(World w, double p, double v, int d, int t)
	{
		sprite = spriteMap[0];
		
		sounds.add("./sfx/burn1.wav");
		sounds.add("./sfx/burn2.wav");
		sounds.add("./sfx/burn3.wav");
		sounds.add("./sfx/burn4.wav");
		sounds.add("./sfx/burn5.wav");
	
		world = w;
		pos = p;
		vel = v;
		decayTime = d;
		tumbleRate = t;
	}
	
	public void update()
	{
		//kill when too old
		if(decayTime<=0)
		{
			destroy();
		} else {
			decayTime--;
		}
		
		//animate the fragement so it appears to tumble
		if(frameCount>=tumbleRate)
		{
			Random generator = new Random();
			
			sprite = spriteMap[generator.nextInt(spriteMap.length)];
		} else {
			frameCount++;
		}
		
		//handle collisions with the world
		if(pos >= world.getSize()-1 || pos <= 0)
		{
			destroy();
		}
		
		//handle collisions with other entities
		if(!collides(this, Smoke.class, pos+vel))
		{
			pos += vel;
		} else {
			destroy();
		}
	}
	
	public void destroy()
	{
		playSound();
		
		dead = true;
	}
}