public class VampRing extends Ring 
{
	public VampRing()
	{
		super.setStuff("Vamp Ring","Deals half of your normal damage and recovers the same amount");
		
	}
	
	public void Spell(Player self, Person enemy)
	{
		int h = (int)(.5 * self.getStrength());
		System.out.printf("Life stole %d damage!%n", h);
		enemy.harm(h);
		self.heal(h);
		super.use();
	}
	
	
}
