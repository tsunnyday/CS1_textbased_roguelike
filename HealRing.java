class HealRing extends Ring
{
	public HealRing()
	{
		super.setStuff("Heal Ring", "Heals 30% of health");		
	}
	
	public void Spell(Player self, Person enemy)
	{
		int h = (int)(self.getHealth()*.3);
		self.heal(h);
		System.out.printf("Healed %d!%n", h);
		super.use();
	}	
}
