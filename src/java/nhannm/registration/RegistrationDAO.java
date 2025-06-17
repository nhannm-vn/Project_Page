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
import java.util.ArrayList;
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
    
    //_Khai bao list accounts chua cac dong DTO tra ra
    //day la list chua cac accounts
    private List<RegistrationDTO> accounts;

    //_Phuong thuc tra ra mot list
    //vi tren la private nen can phuong thuc get
    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }
    
    //_Phuong thuc search account dua tren searchValue
    public void searchLastname(String searchValue)throws SQLException,
            ClassNotFoundException {

        //_Tao nay de hung tu Connection ben DBHelper tra ra
        Connection con = null;
        //_Tao nay de hung thang dong
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. model connects DB
            con = DBHelper.makeConnection();
            if(con != null){
                //_Lay tat ca cac cot trong bang reigistration voi dk
                //cot lastname co chua sort string
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //_Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2 create Statment Objects
                stm = con.prepareStatement(sql);
                //_Truyen co bao nhieu dau ? thi truyen het
                stm.setString(1, "%" + searchValue + "%");
                //2.3 excute query
                rs = stm.executeQuery();
                //3. model gets data from result set, then 
                //model sets data to properties
                
                //_Vi duoc nhieu dong du lieu nen se duyet while
                //_Duyet du lieu(model get du lieu tu result set)
                while(rs.next()){
                    //_Lay ra lan luot tung field gia tri
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //_Tao DTO vi cac gt dat trong DTO
                    //noi cach khac tao ra object tu cac gia tri tren
                    RegistrationDTO dto = new RegistrationDTO(username, password, 
                            fullName, role);
                    //_Set value vao trong list dto vi gia tri nay la nhieu dong
                    //nen duoc luu vao trong list
                    //_Neu chua co list thi tao list roi moi them gia tri vao
                    if(this.accounts == null){
                        this.accounts = new ArrayList<>();
                    }
                    //_Chua co thi tao, co roi thi them gia tri thoi
                    this.accounts.add(dto);
                }//traverse each row in table
                
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
    }
    
    
    
}
