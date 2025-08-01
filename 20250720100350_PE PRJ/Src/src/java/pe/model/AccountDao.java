package pe.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DbUtils;

public class AccountDao {
    
    public AccountDto checkLogin(String username, String password) throws SQLException, ClassNotFoundException {
        AccountDto account = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            conn = DbUtils.getConnection();
            String sql = "SELECT username, password, fullName, phone, email, status, role FROM Account WHERE username = ? AND password = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            rs = stm.executeQuery();
            
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                boolean status = rs.getBoolean("status");
                int role = rs.getInt("role");
                
                account = new AccountDto(username, password, fullName, phone, email, status, role);
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
        
        return account;
    }
}
