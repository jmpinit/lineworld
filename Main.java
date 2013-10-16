//Author: 	Owen Trueblood
//Date:		11/6/10

import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		//create a generator of random numbers
		Random gen = new Random();
	
		//create a new world to place our entities within
		World gameWorld = new World(75);
		
		//our protagonist: the bird
		gameWorld.add(new Bird(gameWorld, gameWorld.getSize()/2, 2));
		
		while(true)
		{
			//medium chance of missiles
			//randomly decide which side to shoot from
			//ensure that no objects are created with zero velocity
			if(gen.nextInt(100)==0)
			{
				if(gen.nextBoolean())
				{
					gameWorld.add(new Missile(gameWorld, 1, gen.nextDouble()*2+0.5, gen.nextInt(10)+5));
				} else {
					gameWorld.add(new Missile(gameWorld, gameWorld.getSize()-2-0.5, gen.nextDouble()*-2, gen.nextInt(10)+5));
				}
			}
			
			//medium chance of smokers
			if(gen.nextInt(100)==0)
			{
				if(gen.nextBoolean())
				{
					gameWorld.add(new Smoker(gameWorld, 1, gen.nextDouble()*2+0.5, gen.nextDouble()*2-1, gen.nextInt(10)+1, gen.nextInt(10)+5));
				} else {
					gameWorld.add(new Smoker(gameWorld, gameWorld.getSize()-2-0.5, gen.nextDouble()*-2, gen.nextDouble()*2-1, gen.nextInt(10)+1, gen.nextInt(10)+5));
				}
			}
			
			//low chance of bouncers
			if(gen.nextInt(500)==0)
			{
				if(gen.nextBoolean())
				{
					gameWorld.add(new Bouncer(gameWorld, 1, gen.nextDouble()*2+0.5, gen.nextInt(100)+10));
				} else {
					gameWorld.add(new Bouncer(gameWorld, gameWorld.getSize()-2-0.5, gen.nextDouble()*-2, gen.nextInt(500)+100));
				}
			}
			
			//add all ents queued for birth
			gameWorld.birth();
			//update all the ents in the world
			gameWorld.update();
			//render the gameworld to the screen
			gameWorld.renderEnts();
			//remove all ents queued for death
			gameWorld.clean();
			
			//throttle the speed
			try
			{
				Thread.sleep(50);
			} catch(Exception e)
			{
				System.out.println(e.toString());
			}
		}
	}
}