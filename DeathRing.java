import java.util.Random;

public class DeathRing extends Ring
{
	public DeathRing()
	{
		super.setStuff("Death Ring", "20% chance of a one-hit KO");
	}
	
	public void Spell(Player self, Person enemy)
	{
		Random rand_gen = new Random();
		if(rand_gen.nextDouble() < .2)
		{
			int h = 99999999;
			System.out.printf("Dealt %d damage!", h);
			enemy.harm(h);
			return;
		}
		System.out.println("Death failed!");
		super.use();
	}
	
	
}
