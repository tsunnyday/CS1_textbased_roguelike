import java.util.Scanner;
import java.util.Random;


public class Player extends Person
{
	private int exp;
	private int level;
	private Ring left_ring;
	private Ring right_ring;
	private int num_of_hp_pots;
	private boolean flee;
	
	
	private static Scanner input = new Scanner(System.in);
	
	public Player()
	{
			super();
			super.name = "You";
			this.level = 1;
			this.distributeStats(Constants.STARTING_STAT_POINTS);
			this.num_of_hp_pots = 0;
			this.flee = false;
			this.left_ring = new FireRing();
	}
	
	public String getBattleStatus()
	{
		return String.format("%s:%nHP:%d/%d%nLeft:%s%nRight:%s%n", super.name, super.health, super.max_health, this.left_ring, this.right_ring);
	}
	
	public void findHealthPot()
	{
		System.out.println("Health potion get!");
		this.num_of_hp_pots += 1;
	}
	
	
	
	public void levelUp()
	{
		System.out.println("Level up!");
		this.level += 1;
		this.distributeStats(Constants.LEVEL_STAT_POINTS);
	}
	
	public void findRing(Ring r)
	{
		System.out.printf("Found a %s!%n%s%n", r.toString(), r.getDescription());
		if(this.left_ring == null)
		{
			this.left_ring = r;
			return;
		}
		if(this.right_ring == null)
		{
			this.right_ring = r;
			return;
		}
		System.out.println("Would you like to replace this with one of your rings?");
		System.out.printf("1) Switch with Left Ring (%s)%n", this.left_ring.toString());
		System.out.printf("2) Switch with Right Ring (%s)%n", this.right_ring.toString());
		System.out.println("3) Don't want it.");
		
		int choice; 
		while(true)
		{
			while(true)
			{
				if(input.hasNextInt())
				{
					choice = input.nextInt();
					break;
				}
				else
				{
					System.out.println("Please enter a number.");
					input.next();
				}
			}
			
			
			if(choice == 1)
			{
				this.left_ring = r;
				return;
			}
			if(choice == 2)
			{
				this.right_ring = r;
				return;
			}	
			if(choice == 3)
			{
				return;
			}
			System.out.println("BAD INPUT");
			
			
		}
		
		
		
	}
	
	public void giveExp(int xp)
	{
		this.exp += xp;
	}
	public int getExp()
	{
		return this.exp;
	}
	public int getLevel()
	{
		return this.level;
	}
	
	public void updateField()
	{
		if(this.left_ring != null)
		{
			if(this.left_ring.getBroken())
			{
				System.out.printf("%s broke!%n", left_ring.toString());
				this.left_ring = null;
			}
		}
		if(this.right_ring != null)
		{
			if(this.right_ring.getBroken())
			{
				System.out.printf("%s broke!%n", right_ring.toString());
				this.right_ring = null;
			}
		}
		
		this.flee = false;
		if(this.exp >= 100)
		{
			this.exp -= 100;
			this.levelUp();
		}
		
		
		if(super.poisoned)
		{
			super.harm(Constants.POISON_DAMAGE);
		}
		
		if(super.health <= 0)
		{
			super.isAlive = false;
		}
	}
	
	public void updateCombat(Person other)
	{
		if(this.left_ring != null)
		{
			if(this.left_ring.getBroken())
			{
				System.out.printf("%s broke!%n", left_ring.toString());
				this.left_ring = null;
			}
		}
		if(this.right_ring != null)
		{
			if(this.right_ring.getBroken())
			{
				System.out.printf("%s broke!%n", right_ring.toString());
				this.right_ring = null;
			}
		}
		
		this.flee = false;
		if(super.health <= 0)
		{
			super.isAlive = false;
			return;
		}
		
		System.out.println("1) Attack");
		System.out.println("2) Left Magic");	
		System.out.println("3) Right Magic");
		System.out.printf("4) Health Potion (%d)%n", this.num_of_hp_pots);
		System.out.println("5) Flee");
		int choice;
		
		while(true)
		{
		
			if(input.hasNextInt())
			{
				choice = input.nextInt();
				break;
			}
			else
			{
				System.out.println("Please enter a number.");
				input.next();
			}
			
		}
		
		if(choice == 1)
		{
			other.harm(super.strength);
			System.out.printf("Player did %d damage!%n", super.strength);
		}
		else if(choice == 2)
		{
			if(this.left_ring == null)
			{
				System.out.println("Not wearing a ring there");
				this.updateCombat(other);
			}
			else
			{
				this.left_ring.Spell(this, other);
			}
		}
		else if(choice == 3)
		{
			if(this.right_ring == null)
			{
				System.out.println("Not wearing a ring there");
				this.updateCombat(other);
			}
			else
			{
				this.right_ring.Spell(this, other);
			}
		}
		else if(choice == 4)
		{
			if(this.num_of_hp_pots > 0)
			{
				super.heal(10 * this.level);
				System.out.printf("Healed %d!%n", 10*this.level);
				this.num_of_hp_pots -= 1;
			}
			else
			{
				System.out.println("No health potions.");
				this.updateCombat(other);
			}
		}
		else if(choice == 5)
		{
			Random rand = new Random();
			if(rand.nextDouble() < Constants.CHANCE_OF_FLEE)
			{
				this.doFlee();
			}
			else
			{
				System.out.println("Could not flee!");
				
			}
			
		}
		else
		{
			System.out.println("Bad Input");
			this.updateCombat(other);
		}
		
		
	}
	
	public void doFlee()
	{
		System.out.println("Fled.");
		this.flee = true;
	}
	
	public boolean getFlee()
	{
		return this.flee;
	}
	
	private void distributeStats(int points) 
	{
		
		while(points > 0)
		{
			System.out.println("Points Left:" + points);
			System.out.println("Cur Max HP:" + super.max_health);
			System.out.println("Cur Str:" + super.strength);
			System.out.printf("1) +%d HP%n", Constants.HEALTH_BOOST_PER_POINT);
			System.out.printf("2) +%d Str%n", Constants.STR_BOOST_PER_POINT);
			
			int choice;
			
			while(true)
			{
				if(input.hasNextInt())
				{
					choice = input.nextInt();
					break;
				}
				else
				{
					System.out.println("Please enter a number.");
					input.next();
				}
			}
			
		
			if(choice == 1)
			{
				super.max_health += Constants.HEALTH_BOOST_PER_POINT;
				super.health += Constants.HEALTH_BOOST_PER_POINT;
				points -= 1;
			}
			else if(choice == 2)
			{
				super.strength += Constants.STR_BOOST_PER_POINT;
				points -= 1;
			}
			else
			{
				System.out.println("BAD INPUT");
			}
			
		}
		return;
	}
	
	
	
	
}
