public abstract class Ring
{


	
	
	private String description;

	private String name;
	private int durability;
	private boolean isBroken;
	
	
	public Ring()
	{
		this.durability = 100;
		this.isBroken = false;
	}
	
	protected void setStuff(String n, String d)
	{
		this.description = d;
		
		this.name = n;
	}
	
	protected void use()
	{
		this.durability -= 1;
		if(this.durability == 0)
		{
			this.isBroken = true;
		}
	}
	
	public boolean getBroken()
	{
		return this.isBroken;
	}
		
	public abstract void Spell(Player self, Person enemy);
	

	
	public String getDescription()
	{
		return this.description;
	}
	public String toString()
	{
		return this.name;
	}
	
}
