public class BulkRing extends Ring 
{
	public BulkRing()
	{
		super.setStuff("Bulk Ring", "Increases health by 10%");
	}
	
	public void Spell(Player self, Person enemy)
	{
		int h = (int)(self.getLevel() * 5);
		self.trueHeal(h);
		System.out.printf("Increased health by %d!%n", h);
		
		super.use();
	}
	
	
}
