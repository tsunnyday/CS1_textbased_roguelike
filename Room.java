public class Room
{
	private String desc;
	private Room east;
	private Room north;
	private Room west;
	private Room south;
	
	public Room(String d)
	{
		this.desc = d;
	}
	
	public String getDesc()
	{
		return this.desc;
	}
	public Room getRoomAtDir(int d)
	{
		if(d == Constants.NORTH)
		{
			return this.north;
		}
		if(d == Constants.SOUTH)
		{
			return this.south;
		}
		if(d == Constants.EAST)
		{
			return this.east;
		}
		if(d == Constants.WEST)
		{
			return this.west;
		}
		return null;
	}
	public void setRoomAtDir(int d, Room r)
	{
		if(d == Constants.NORTH && this.north != r)
		{
			this.north = r;
			r.setRoomAtDir(Constants.SOUTH, this);
		}
		if(d == Constants.SOUTH && this.south != r)
		{
			this.south = r;
			r.setRoomAtDir(Constants.NORTH, this);
		}
		if(d == Constants.EAST && this.east != r)
		{
			this.east = r;
			r.setRoomAtDir(Constants.WEST, this);
		}
		if(d == Constants.WEST && this.west != r)
		{
			this.west = r;
			r.setRoomAtDir(Constants.EAST, this);
		}
		return;
		
		
		
	}
	
	
	
	
	public String getExits()
	{
		String s = "";
		if(this.east != null)
		{
			s += "1) To your east is a " + this.east.getDesc() + " room.\n";
		}
		if(this.north != null)
		{
			s += "2) To your north is a " + this.north.getDesc() + " room.\n";
		}
		if(this.west != null)
		{
			s += "3) To your west is a " + this.west.getDesc() + " room.\n";
		}
		if(this.south != null)
		{
			s += "4) To your south is a " + this.south.getDesc() + " room.\n";
		}
		return s;
	}
	
	
	public String toString()
	{
		return "You find yourself in a " + this.getDesc() + " room.\n" + this.getExits();
	}


}
