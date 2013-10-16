import java.util.*;

public class Bouncer extends Entity
{	
	int decayTime;

	public Bouncer(World w, double p, double v, int d)
	{
		sprite = 'V';
		
		sounds.add("./sfx/bounce1.wav");
		sounds.add("./sfx/bounce2.wav");
		sounds.add("./sfx/bounce3.wav");
		
		world = w;
		pos = p;
		vel = v;
		decayTime = d;
	}
	
	public void update()
	{
		Random generator = new Random();
		
		//kill when too old
		if(decayTime<=0)
		{
			destroy();
		} else {
			decayTime--;
		}
		
		//handle collision with edge of world
		if(pos >= world.getSize()-1)
		{
			pos = world.getSize()-1;
			vel *= -1;
			
			playSound();
		} else if(pos <= 0)
		{
			pos = 0;
			vel *= -1;
			
			playSound();
		}
		
		//handle collision with other entities
		if(!collides(this, Smoke.class, pos+vel))
		{
			pos += vel;
		} else {
			playSound();
			vel *= -1;
		}
	}
	
	public void destroy()
	{
		dead = true;
	}
}