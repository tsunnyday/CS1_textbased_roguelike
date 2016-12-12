public abstract class Person
{
	protected int health;
	protected int max_health;
	protected int strength;
	protected String name;
	protected boolean poisoned;
	protected boolean isAlive;
	
	public Person()
	{
		this.health = 0;
		this.max_health = 0;
		this.strength = 0;
		this.poisoned = false;
		this.isAlive = true;
	}
	public int getStrength()
	{
		return this.strength;
	}
	public int getMaxHealth()
	{
		return this.max_health;
	}
	
	public void restore()
	{
		this.health = this.max_health;
		this.poisoned = false;
		
	}
	
	public void trueHeal(int d)
	{
		this.max_health += d;
		this.health += d;
		
	}
	
	
	public void harm(int d)
	{
		this.health -= d;
	}
	public void heal(int d)
	{
		this.health += d;
		if(this.health > this.max_health)
		{
			this.health = this.max_health;
		}
	}
	public boolean getAlive()
	{
		return this.isAlive;
	}
	public int getHealth()
	{
		return this.health;
		
	}
	
	
	
	public abstract void updateField();
	public abstract void updateCombat(Person other);
	public abstract String getBattleStatus();
	

}
