import java.util.*;
import java.io.*;
import javax.sound.sampled.*;

public abstract class Entity
{
	boolean dead;
	
	Vector<String> sounds = new Vector<String>();

	World world;
	char sprite;
	double pos;
	double vel;
	
	//every entity needs these things
	
	abstract public void update();
	abstract public void destroy();
	
	//every entity needs these things and will probably use the same code for them
	
	public void draw()
	{
		world.screen.setChar((int)pos, sprite);
	}
	
	public double getPos()
	{
		return pos;
	}
	
	public boolean collides(Entity me, double pos)
	{
		//if there is nothing there OR there is something there but it is just me there is no collision
		return world.getEntsAt(pos).size()==0||(world.getEntsAt(pos).size()==1&&world.getEntsAt(pos).contains(me));
	}
	
	
	//overloaded function used to exlude a type of entity from detection of a collision
	public boolean collides(Entity me, Class type, double pos)
	{
		Vector<Entity> ents = world.getEntsAt(pos);
		for (Entity ent : ents)
		{
			if(ent.getClass()!=type)
			{
				if(ent!=me)
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	//this is reasonable because each entity will only have one type of sound to play
	//this bit of code is extremely inefficient in terms of memory because streams are not explicitly disposed,
	//rather they are left for the garbage collector to take care of.
	//a relatively easy fix would be to use threads for every sound played so that the streams can be closed when no longer used
	public void playSound()
	{
		Random generator = new Random();
		
		try
		{
			InputStream audioSrc = getClass().getResourceAsStream(sounds.get(generator.nextInt(sounds.size())));
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);
			AudioFormat audioFormat = audioInputStream.getFormat();
			DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat);
			Clip clip = (Clip) AudioSystem.getLine(dataLineInfo);
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}