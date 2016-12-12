public class FireRing extends Ring
{
	public FireRing()
	{
		super.setStuff("Fire Ring", "Reduces enemies health by 50%");
	}
	
	public void Spell(Player self, Person enemy)
	{
		int h = (int)(enemy.getHealth()*.5);
		enemy.harm(h);
		System.out.printf("Used Fire for %d damage!%n", h);
		super.use();
		
	}
}
