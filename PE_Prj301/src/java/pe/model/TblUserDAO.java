/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.utils.DbUtils;

/**
 *
 * @author Hi
 */
public class TblUserDAO implements Serializable {
    public TblUserDTO checkLogin(String username, int password) throws SQLException, ClassNotFoundException{
        // Tao bien chot
        TblUserDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DbUtils.getConnection();
            if(con != null){
                String sql = "Select fullName "
                        + "From tbl_User "
                        + "Where username = ? "
                        + "And password = ? "
                        + "And role = 1";
                
                stm = con.prepareStatement(sql);
                
                stm.setString(1, username);
                stm.setInt(2, password);
                
                rs = stm.executeQuery();
                
                if (rs.next()) { //_Check co ton tai tk hay khong
                    //_Lay lan luot cac gt ra de tien hanh bo vao class tao ra dto
                    String fullName = rs.getString("fullName");

                    //_Luu y se khong luu password vi ngta co the moc ra duoc
                    result = new TblUserDTO(username, password, fullName, true);
                }
            }
        } finally{
            // _Khai bao truoc thi dong truoc theo chieu nguoc lai
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    
}
