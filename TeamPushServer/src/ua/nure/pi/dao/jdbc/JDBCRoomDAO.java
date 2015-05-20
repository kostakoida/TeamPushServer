package ua.nure.pi.dao.jdbc;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ua.nure.pi.dao.DAOFactory;
import ua.nure.pi.dao.RoomDAO;
import ua.nure.pi.entity.Room;
import ua.nure.pi.entity.Room;
import ua.nure.pi.parameter.MapperParameters;
import ua.nure.pi.security.Hashing;

public abstract class JDBCRoomDAO implements RoomDAO  {
	
	protected String SQL_SELECT_ROOM = "SELECT * FROM Rooms WHERE RoomID = ?";
	protected String SQL_SELECT_ROOM_BY_NAME = "SELECT * FROM Rooms WHERE NAME = ?";
	protected String SQL_SELECT_ALL_ROOM = "SELECT * FROM Rooms";
		
	protected String SQL_INSERT_ROOM = "INSERT INTO Rooms(Name, Password) VALUES(?, ?)";
	protected String SQL_UPDATE_ROOM_NAME = "UPDATE Rooms SET Name = ? WHERE RoomID = ?";
	protected String SQL_UPDATE_ROOM_PASS = "UPDATE Rooms SET Password = ? WHERE RoomID = ?";
	protected String SQL_DELETE_Room = "DELETE FROM Rooms WHERE RoomID = ?";
	
	protected DAOFactory jdbcDAOFactory;

	@Override
	public Room getRoom(long RoomId) {
		Connection con = null;
		Room Room = null;
		try {
			con = getConnection();
			Room = getRoom(RoomId, con);
		} catch (SQLException e) {
			System.err.println("Can not get Room." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return Room;
	}

	public Room getRoom(long RoomId, Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		Room Room = null;
		try {
			pstmt = con.prepareStatement(SQL_SELECT_ROOM);
			pstmt.setLong(1, RoomId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Room = unMapRoom(rs);
			}
			return Room;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
	}

	@Override
	public Collection<Room> getRooms() {
		Connection con = null;
		try {
			con = getConnection();
			return getAllRooms(con);
		} catch (SQLException e) {
			System.err.println("Can not get all Rooms." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return null;
	}

	private Collection<Room> getAllRooms(Connection con)
			throws SQLException {
		Collection<Room> Rooms = new ArrayList<Room>();
		Statement stmt = null;
		Room Room = new Room();
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECT_ALL_ROOM);
			while (rs.next()) {
				Room = unMapRoom(rs);
				Rooms.add(Room);
			}
			return Rooms;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
	}	
	
	private Room unMapRoom(ResultSet rs) throws SQLException {
		Room Room = new Room();
		//Room.setRoomId(rs.getLong(MapperParameters.Room__ID));
		Room.setName(rs.getString(MapperParameters.Room__NAME));
		Room.setPassword(rs.getString(MapperParameters.Room__PASSWORD));
		return Room;
	}
	
	private void mapRoom(Room Room, PreparedStatement pstmt)
			throws SQLException {
		pstmt.setString(1, Hashing.salt(Room.getPassword(), Room.getName()));
		pstmt.setLong(2, Room.getId());
	//	pstmt.setArray(3, (Room.getMembers().toArray()));

	}


	@Override
	public boolean insertRoom(Room Room) {
		boolean result = false;
		Connection con = null;
		try {
			con = getConnection();
			result = insertRoom(Room, con);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not insert Room." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return result;
	}

	
	public boolean insertRoom(Room Room, Connection con)
			throws SQLException {
		PreparedStatement pstmt = null;
		boolean result = false;

		try {
			pstmt = con.prepareStatement(SQL_INSERT_ROOM, Statement.RETURN_GENERATED_KEYS);
			mapRoom(Room, pstmt);
			result = pstmt.executeUpdate() == 1;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next())
				Room.setId(rs.getLong(1));
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement. " + e.getMessage());
				}
			}
		}
		return result;
	}
	

	@Override
	public boolean deleteRoom(Room Room) {
		Connection con = null;
		boolean result = false;
		try {
			con = getConnection();
			result = deleteRoom(con, Room);
			if(result)
				con.commit();
		} catch (SQLException e) {
			System.err.println("Can not update pass." + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Can not close connection" + e.getMessage());
			}
		}
		return result;
	}

	private boolean deleteRoom(Connection con, Room Room)
			throws SQLException {
		boolean result;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(SQL_DELETE_Room);
			pstmt.setLong(1, Room.getId());
			result = pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					System.err.println("Can not close statement" + e.getMessage());
				}
			}
		}
		return result;
	}	
	
	protected abstract Connection getConnection() throws SQLException;
}
