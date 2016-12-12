import java.util.Scanner;
import java.util.Random;

public class Game
{
	
	public static Scanner input = new Scanner(System.in);
	public static Random rand_gen = new Random();
	public static Room cur_room;
	public static Room prev_room;
	public static Player player;
	public static boolean quit;
	
	
	
	
	public static void main(String[] argv)
	{
		quit = false;
		player = new Player();
		
		while(true)
		{
			getNewFloor();
			exploreLoop();
			if(player.getAlive() == false)
			{
				return;
			}
			if(player.getLevel() >= 100)
			{
				return;
			}
			if(quit)
			{
				return;
			}
		}
	}
	public static void getNewFloor()
	{
		if(player.getLevel() <= 5)
		{
			cur_room = new Dungeon(5).getStartRoom();
		}
		else
		{
			cur_room = new Dungeon(player.getLevel()).getStartRoom();
		}
	}
	
	
	public static void combatLoop(Person enemy)
	{
		while(true)
		{
			if(player.getAlive() == false)
			{
				System.out.println("Player lost");
				return;
			}
			
			System.out.print(enemy.getBattleStatus());
			System.out.print(player.getBattleStatus());
			player.updateCombat(enemy);
			if(player.getFlee())
			{
				cur_room = prev_room;
				return;
			}
				
			enemy.updateCombat(player);
			
			if(enemy.getAlive() == false)
			{
				System.out.println("Player won");
				player.giveExp(enemy.getStrength() + 5);
				System.out.printf("Gained %d exp!%n", enemy.getStrength() + 5);
				System.out.printf("Current EXP: %d%n", player.getExp());
				return;
			}		
			
		}
		
	}
	public static void exploreLoop() 
	{
		
		int cmd;
		
		while(true)
		{	
			if(player.getAlive() == false)
			{
				System.out.println("YOU LOSE!");
				return;
			}
			if(player.getLevel() >= 100)
			{
				System.out.println("YOU WIN!");
				return;
			}
			if(cur_room.getDesc().equals(Constants.EXIT_COLOR))
			{
				System.out.println("Advanced to next floor!");
				player.restore();
				player.giveExp(Constants.EXP_FOR_NEXT_FLOOR);
				System.out.printf("Gained %d exp!%n", Constants.EXP_FOR_NEXT_FLOOR);
				System.out.printf("Current EXP: %d%n", player.getExp());
				return;
			}
			player.updateField();
				
		
			
			System.out.print(cur_room.toString());
			System.out.println("8 to quit.");
			while(true)
			{
				if(input.hasNextInt())
				{
					cmd = input.nextInt();
					break;
				}
				else
				{
					System.out.println("Please enter a number.");
					input.next();
				}
			}
			
			if(cmd == 8)
			{
				System.out.println("YOU QUIT!");
				quit = true;
				return;
			}
			else if(cmd == 2)
			{
				if(cur_room.getRoomAtDir(Constants.NORTH) != null)
				{
					prev_room = cur_room;
					cur_room = cur_room.getRoomAtDir(Constants.NORTH);
					enterRoom();
				}
				else
				{
					System.out.println("Can't go that way.");
				}
			}
			else if(cmd == 4)
			{
				if(cur_room.getRoomAtDir(Constants.SOUTH) != null)
				{
					prev_room = cur_room;
					cur_room = cur_room.getRoomAtDir(Constants.SOUTH);
					enterRoom();
				}
				else
				{
					System.out.println("Can't go that way.");
				}
			}
			else if(cmd == 1)
			{
				if(cur_room.getRoomAtDir(Constants.EAST) != null)
				{
					prev_room = cur_room;
					cur_room = cur_room.getRoomAtDir(Constants.EAST);
					enterRoom();
				}
				else
				{
					System.out.println("Can't go that way.");
				}
			}
			else if(cmd == 3)
			{
				if(cur_room.getRoomAtDir(Constants.WEST) != null)
				{
					prev_room = cur_room;
					cur_room = cur_room.getRoomAtDir(Constants.WEST);
					enterRoom();
				}
				else
				{
					System.out.println("Can't go that way.");
				}
			}
			else
			{
				System.out.println("Invalid cmd.");
			}
		
	
		
		}
	}
	public static void enterRoom()
	{
		if(rand_gen.nextDouble() <= Constants.CHANCE_OF_ENEMY)
		{
			findEnemy();
			if(player.getFlee())
			{
				return;
			}
		}
		if(rand_gen.nextDouble() <= Constants.CHANCE_OF_ITEM)
		{
			findItem();
		}
	}
	
	public static void findItem()
	{
		if(player.getAlive() == false)
		{
			return;
		}
		System.out.println("Found item!");
		
		double i = rand_gen.nextDouble();
		if(i < Constants.CHANCE_OF_INSTAWIN)
		{
			System.out.println("What's this?");
			System.out.println("...");
			while(player.getLevel() != 100)
			{
				player.levelUp();
			}
		}
		else if(i < Constants.CHANCE_OF_RING)
		{
			player.findRing(new FireRing());
		}
		else if(i < 2*Constants.CHANCE_OF_RING)
		{
			player.findRing(new HealRing());
		}
		else if(i < 3*Constants.CHANCE_OF_RING)
		{
			player.findRing(new VampRing());
		}
		else if(i < 4*Constants.CHANCE_OF_RING)
		{
			player.findRing(new DeathRing());
		}
		else if(i < 5*Constants.CHANCE_OF_RING)
		{
			player.findRing(new ThiefRing());
		}
		else if(i < 6*Constants.CHANCE_OF_RING)
		{
			player.findRing(new BulkRing());
		}
		else
		{
			player.findHealthPot();
		}
		
		
	}
	public static void findEnemy()
	{
		System.out.println("An enemy appeared!");
		
		int s = rand_gen.nextInt(10) + (player.getLevel()-1) * 5;
		int h = rand_gen.nextInt(20) + (player.getLevel()-1)*15;
		
		SimpleEnemy e = new SimpleEnemy(h, s);
		combatLoop(e);
	}


}
