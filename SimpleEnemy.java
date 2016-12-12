

public class SimpleEnemy extends Person
{
	private static int id = 1;
	
	
	public SimpleEnemy(int hp, int strength)
	{
		super();
		super.health = hp;
		super.max_health = hp;
		super.strength = strength;
		super.name = "Monster" + this.id;
		this.id += 1;
		
	}
	
	public void updateCombat(Person other)
	{
		if(super.health <= 0)
		{
			super.isAlive = false;
			return;
		}
		
		other.harm(super.strength);
		System.out.printf("Monster did %d damage!%n", super.strength);
	}
	
	public String getBattleStatus()
	{
		return String.format("%s:%nHP:%d%n", super.name, super.health);
	}
	
	public void updateField()
	{
		
	}
	
	
	
	
}
