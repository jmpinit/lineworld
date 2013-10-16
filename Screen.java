public class Screen
{
	private int size;
	private char[] buffer;
	
	public Screen(int s)
	{
		size = s;
		
		clear();
	}
	
	public void clear()
	{
		buffer = new char[size];
		
		for(int i=0; i<size; i++)
		{
			buffer[i] = ' ';
		}
	}
	
	public void render()
	{
		//the |'s represent the bounds of the screen
		System.out.println("|" + new String(buffer) + "|");
	}
	
	public void setChar(int x, char c)
	{
		if(x>=0 && x<buffer.length)
		{
			buffer[x] = c;
		}
	}
}