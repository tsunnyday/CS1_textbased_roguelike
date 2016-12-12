public class ThiefRing extends Ring
{
	public ThiefRing()
	{
		super.setStuff("Thief Ring", "Guaranteed flee success");
	}
	
	public void Spell(Player self, Person enemy)
	{
		self.doFlee();
		super.use();
	}

}
