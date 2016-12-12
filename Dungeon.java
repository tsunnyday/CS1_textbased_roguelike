import java.util.Random;

public class Dungeon
{
	private Room[] rooms;
	private Random rand_gen;	
		
	public Dungeon(int num_of_rooms)
	{
		/*so, I want to randomly generate the dungeons again, because replayability. I'm going to do 
		 * it a bit differently this time though.  In the old one, the dungeons were basically grids, with occasional holes in them.  
		 * I don't want to write the linking code for that again though, so this will be more like a maze and less like a dungeon, in that 
		 * connections will be more like a web.  I'll explain the algorithm.
		 * This will come about because of how I'll generate rooms.
		 * There are two vars, cur_room and cur_index. And an array of rooms.  How this'll work, is cur_index holds the next available 
		 * slot in the array.  It starts at 1, cuz room 0, the start room, already exists.  cur_room holds the index of the room that we're potentially
		 * adding a branch room to. We take our cur_room, and for each dir that doesn't already have a room, we do some random magic
		 * and if we get a new room, we make a new room at cur_index, link it to where it should go, and increment cur_index.  Once we
		 * do that process for all 4 potential exits, we increment cur_room, and start working on the next room.  We do this until cur_index 
		 * equals num_of rooms, and we'll make the last room the color gold.
		 */ 
		
		this.rand_gen = new Random();
		this.rooms = new Room[num_of_rooms];
		this.rooms[0] = new Room(genColor());
		int cur_room = 0;
		int cur_index = 1;
		
		while(true)
		{
			if(cur_room == cur_index)
			{
				cur_room = 0;
			}
			for(int i=0; i<4;i++)
			{
				if(this.rooms[cur_room].getRoomAtDir(i) == null)
				{
					if(rand_gen.nextDouble() <= Constants.CHANCE_TO_CREATE_ROOM)
					{
						if(cur_index == num_of_rooms - 1)
						{
							this.rooms[cur_index] = new Room(Constants.EXIT_COLOR);
							this.rooms[cur_room].setRoomAtDir(i,this.rooms[cur_index]);
							return;
						}
						else
						{
							this.rooms[cur_index] = new Room(this.genColor());
							this.rooms[cur_room].setRoomAtDir(i, this.rooms[cur_index]);
							cur_index ++;
						}
					}
				}
			}
			cur_room++;
				
		}
	}	
	public Room getStartRoom()
	{
		return this.rooms[0];
	}
	private String genColor()
	{
		return Constants.colors[rand_gen.nextInt(Constants.colors.length)];
		
	}
}
