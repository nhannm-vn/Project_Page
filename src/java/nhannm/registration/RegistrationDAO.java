/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhannm.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import nhannm.util.DBHelper;

/**
 *
 * @author ADMIN
 */
//_Buoc dau tien phai implement Serializable
public class RegistrationDAO implements Serializable {

    //_Phuong thuc check login de check tk co ton tai chua
    public boolean checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {
        //_Bien chot cua phuong thuc
        boolean result = false;
        //_Tao nay de hung tu Connection ben DBHelper tra ra
        Connection con = null;
        //_Tao nay de hung thang dong
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. model connects DB
            con = DBHelper.makeConnection();
            if(con != null){
                //2. model truy van du lieu tu DB
                //2.1 create SQL String
                //_Moi menh de cua cau lenh sql phai duoc viet tren 1 dong
                //_Truoc khi xuong dong phai chen mot khoang trang 
                //neu khong se co loi SyntaxFromNear...
                //_Tat ca cac table ten cot phai copy tu DB neu 
                //khong se co loi Object not Found
                String sql = "Select username "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //_Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2 create Statment Objects
                stm = con.prepareStatement(sql);
                //_Truyen co bao nhieu dau ? thi truyen het
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 excute query
                rs = stm.executeQuery();
                //3. model gets data..., then 
                //model sets data to properties
                if(rs.next()){
                    result = true;
                }
            }//_connection is an available
        } finally {
            if(rs != null){
                rs.close();
            }
            if(stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    //_Minh dang search se ra nhieu dong DTO
    //_Moi dong tuong ung voi 1 dong DTO
    private List<RegistrationDTO> accounts;

    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    
}
