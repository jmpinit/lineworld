import java.util.*;

public class World
{
	public Screen screen;
	private int size;
	private Vector<Entity> myEnts = new Vector<Entity>();
	private Vector<Entity> myBabies = new Vector<Entity>();
	
	public World(int s)
	{
		size = s;
		screen = new Screen(size);	//main rendering object
	}
	
	public void add(Entity ent)
	{
		//myBabies serves as a queue
		myBabies.add(ent);
	}
	
	public void birth()
	{
		myEnts.addAll(myBabies);
		myBabies.removeAllElements();
	}
	
	public void update()
	{
		//if there are no ents on the screen we should exit
		if(myEnts.size()==0) System.exit(0);
		
		for(Entity ent: myEnts)
		{
			ent.update();
		}
	}
	
	public void renderEnts()
	{
		//clear the screen buffer in preparation
		screen.clear();
		
		//make each ent add itself to the screen buffer
		for(Entity ent: myEnts)
		{
			ent.draw();
		}
		
		//print the screen buffer
		screen.render();
	}
	
	public void clean()
	{
		//use an iterator to remove references to dead entities
		//useful here because it can handle things disappearing from
		//its collection as it is moving through the collection
		Iterator itr = myEnts.iterator();
		while(itr.hasNext())
		{
			Entity ent = (Entity)itr.next();
			
			if(ent.dead)
			{
				itr.remove();
			}
		}
	}
	
	//return all entities at location in world
	//mild aliasing effects present because of rounding
	public Vector getEntsAt(double pos)
	{
		Vector<Entity> ents = new Vector<Entity>();
		
		for(Entity ent: myEnts)
		{
			if((int)ent.getPos()==(int)pos)
			{
				ents.add(ent);
			}
		}
		
		return ents;
	}
	
	public int getSize()
	{
		return size;
	}
}