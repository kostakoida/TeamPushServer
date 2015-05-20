package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Room;

public interface RoomDAO {
	
	public Collection<Room> getRooms();
	
	public Room getRoom(long roomId);
		
	public boolean insertRoom(Room room);
	
	public boolean deleteRoom(Room room);
}
