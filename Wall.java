public class Wall extends Entity
{
	public Wall(World w, int p)
	{
		sprite = '|';
		
		world = w;
		pos = p;
	}
	
	public void update() {}
	
	public void destroy() {}
}