package pe.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import pe.utils.DbUtils;

public class RoomForRentDao {
    
    public List<RoomForRentDto> searchRooms(String searchValue) throws SQLException, ClassNotFoundException {
        List<RoomForRentDto> result = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT id, title, price, location, description, postedDate, status, username "
                    + "FROM RoomForRent "
                    + "WHERE (location LIKE ? OR title LIKE ?) AND status != -2";
            stm = conn.prepareStatement(sql);
            stm.setString(1, "%" + searchValue + "%");
            stm.setString(2, "%" + searchValue + "%");
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                BigDecimal price = rs.getBigDecimal("price");
                String location = rs.getString("location");
                String description = rs.getString("description");
                Date postedDate = rs.getTimestamp("postedDate");
                int status = rs.getInt("status");
                String username = rs.getString("username");
                
                RoomForRentDto room = new RoomForRentDto(id, title, price, location, description, postedDate, status, username);
                result.add(room);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return result;
    }
    
    public boolean deleteRoom(int roomId) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            conn = DbUtils.getConnection();
            String sql = "UPDATE RoomForRent SET status = -2 WHERE id = ? AND status != 1";
            stm = conn.prepareStatement(sql);
            stm.setInt(1, roomId);
            
            int rowsAffected = stm.executeUpdate();
            result = rowsAffected > 0;
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        
        return result;
    }
}
