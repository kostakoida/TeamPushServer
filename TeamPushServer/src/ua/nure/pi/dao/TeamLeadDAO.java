package ua.nure.pi.dao;

import ua.nure.pi.entity.Room;
import ua.nure.pi.entity.User;

public interface TeamLeadDAO extends UserDAO{
	
	public boolean addBanList(User user);
	
	public boolean deleteRomm(Room room);
}
