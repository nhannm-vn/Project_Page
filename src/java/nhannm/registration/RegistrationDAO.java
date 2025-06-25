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
    //public boolean checkLogin(String username, String password) throws SQLException,
    //       ClassNotFoundException {
    //_Bay gio kieu du lieu tra ra se la 1 dto nghia la 1 obj. Vi chung ta sau khi
    //login thanh cong thi co nhu cau hien thi welcome + lastname. Chu khong con la
    //check xem co login hay khong thoi
    public RegistrationDTO checkLogin(String username, String password) throws SQLException,
            ClassNotFoundException {
        //_Bien chot cua phuong thuc
        //=> luc nay vi doi kieu du lieu tra ra nen minh cung se thay doi bien chot
        //luc nay bien se chua 1 dto luon neu login success chu khong chi la mot 
        //ket qua boolean
        RegistrationDTO result = null;
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
                
                //**Luu y vi method da truyen vao username + password chinh vi 
                //vay minh se khong query 2 thang do nua ma chi query 2 thang con
                //thieu ma thoi
                String sql = "Select lastname, isAdmin "
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
                //3. model gets data from Result Set, then 
                //model sets data to properties
                if(rs.next()){ //_Neu vao duoc if nghia la model get data success tu result set
                    //_Khi co data thi tien hanh lay cac data de bo vao class 
                    //va tao ra obj dto
                    String fullName = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    
                    //_Luu khi khi tao ra dto nguoi ta se khong luu password 
                    //vi so nguy hiem hacker co the dom toi duoc
                    result = new RegistrationDTO(username, null, fullName, isAdmin);
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
        //_Nghia la sau cung se tra ra obj dto sau moi lan login thanh cong
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
    
    
    //_Truyen vao username nghia la parameter pk de xoa bang cau truy van
    public boolean deleteAccount(String username) throws SQLException,
            ClassNotFoundException {
        //_Bien chot luc nay se la boolean 
        boolean result = false;
        //_Tao nay de hung tu Connection ben DBHelper tra ra
        Connection con = null;
        //_Tao nay de hung thang dong
        PreparedStatement stm = null;
        //_Minh khong can lay du lieu nen khong co result set
        //ResultSet rs = null;
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
                
                //**Luu y vi method da truyen vao username + password chinh vi 
                //vay minh se khong query 2 thang do nua ma chi query 2 thang con
                //thieu ma thoi
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //_Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2 create Statment Objects
                stm = con.prepareStatement(sql);
                //_Truyen co bao nhieu dau ? thi truyen het
                stm.setString(1, username);
                //2.3 excute query
                //**Tat ca cau lenh insert, delete, update thi tra ra so dong integer
                //effect. Va se la executeUpdate chu khong con la excuteQuery
                int effectRows = stm.executeUpdate();
                
                //3. checking effectRows are valid then model set
                //data to properties of model
                if(effectRows > 0){
                    result = true;
                }
            }//_connection is an available
        } finally {
            if(stm != null){
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //_Nghia la sau cung se tra ra obj dto sau moi lan login thanh cong
        return result;
    }
    
}
