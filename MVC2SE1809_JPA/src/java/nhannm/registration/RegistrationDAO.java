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
public class RegistrationDAO implements Serializable {

    //phuong thuc check login de check co ton tai hay chua
    //public boolean checkLogin(String username, String password)
    //throws SQLException, ClassNotFoundException {
    //_Bay gio kieu du lieu tra ra se la dto chu khong con la boolean nua
    public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException {
        //_Bien chot cua ham de tra ra gia tri
        //boolean result = false;
        //_Gio ket qua chot khi login thanh cong se la dong welcome + lastname
        //chinh vi vay phai chinh lai bien chot ket qua
        //nghia la se tra ra luon dto chu khong con la boolean de chot ket qua nua
        RegistrationDTO result = null;

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. model connects DB
            //_Khai bao bien va gan null
            //_Phai dong tat ca cac doi tuong bang moi cach
            //_Thuc hien xu li
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. model truy van du lieu tu DB
                //2.1. create SQL String
                //_Moi menh de cua cau lenh SQL phai duoc viet tren 1 dong
                //_Truoc khi xuong dong phai chen mot khoang trang
                //neu khong se co loi la SyntaxFromNear...
                //_Tat ca cac table ten cot phai copy tu DB 

                //**Luu y: vi method nay da truyen vao username va password nen
                //minh se khong query ra nua ma chi lay nhung cot chua co
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? "
                        + "And password = ?";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3. excute query
                rs = stm.executeQuery();
                //_R -> Result Set. Moi phan tu trong ds nay tuong ung voi 1 row
                //trong table dong dau tien chu BOF dong cuoi EOF
                //con tro luon luon tro dong dau tien, muon qua thang tiep theo 
                //phai su dung phuong thuc next, con chua du lieu tra ra true,
                //khong con chua thi se ra false. Dac tinh cua con tro la forward
                //only chi di toi cuoi cung
                //_Khong bh co chuyen result set = null thi ko co du lieu
                //3. model gets data from result set, then
                // model sets data to properties of model
                //_ Ra mot dong do username la PK
                if (rs.next()) { //_Check co ton tai tk hay khong
                    //_Lay lan luot cac gt ra de tien hanh bo vao class tao ra dto
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");

                    //_Luu y se khong luu password vi ngta co the moc ra duoc
                    result = new RegistrationDTO(username, null, fullName, role);
                }
            }//_connection is an available

        } finally {
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
        //_Sau cung se tra ra obj dto cua moi lan login vao thanh cong
        return result;
    }

    //_Minh dang search se ra nhieu dong DTO
    //_Moi dong tuong ung voi 1 dong DTO
    //_Khai bao list accounts chua cac dong DTO tra ra
    private List<RegistrationDTO> accounts;

    //_Phuong thuc tra ra mot list
    public List<RegistrationDTO> getAccounts() {
        return accounts;
    }

    public void searchLastname(String searchValue)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. model connects DB
            //_Khai bao bien va gan null
            //_Phai dong tat ca cac doi tuong bang moi cach
            //_Thuc hien xu li
            con = DBHelper.makeConnection();
            if (con != null) {
                //_Lay tat ca cac cot trong bang registration voi dk
                //cot lastname co chua sort string
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname Like ?";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setString(1, "%" + searchValue + "%");
                //2.3. excute query
                rs = stm.executeQuery();

                //_Duyet du lieu(model get du lieu tu result set)
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    //_Tao DTO vi cac gt dat trong DTO
                    RegistrationDTO dto = new RegistrationDTO(username, password,
                            fullName, role);
                    //set
                    if (this.accounts == null) {
                        this.accounts = new ArrayList<>();
                    }//account is not available
                    this.accounts.add(dto);

                }//traverse each row in table

            }//_connection is an available

        } finally {
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
        //**Luu y: tai day se khong return list acconts vi no da private va
        //muon lay duoc no phai thong qua method getAccounts
    }

    public boolean deleteAccount(String username)
            throws SQLException, ClassNotFoundException {
        //_Bien chot cua ham de tra ra gia tri
        //boolean result = false;
        //_Gio ket qua chot khi login thanh cong se la dong welcome + lastname
        //chinh vi vay phai chinh lai bien chot ket qua
        //nghia la se tra ra luon dto chu khong con la boolean de chot ket qua nua
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. model connects DB
            //_Khai bao bien va gan null
            //_Phai dong tat ca cac doi tuong bang moi cach
            //_Thuc hien xu li
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. model truy van du lieu tu DB
                //2.1. create SQL String
                //_Moi menh de cua cau lenh SQL phai duoc viet tren 1 dong
                //_Truoc khi xuong dong phai chen mot khoang trang
                //neu khong se co loi la SyntaxFromNear...
                //_Tat ca cac table ten cot phai copy tu DB 

                //**Luu y: vi method nay da truyen vao username va password nen
                //minh se khong query ra nua ma chi lay nhung cot chua co
                // Xoa tat ca cac dong duoi table bang mot keyword co dinh
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setString(1, username);
                //2.3. excute query

                // Tat ca cac cau lenh insert delete update thi tra ra integer
                //va se la excuteUptate chu khong con excuteQuery
                int effectRows = stm.executeUpdate();

                //3. checking effect rows are valid
                //then model set data to properties of model
                if (effectRows > 0) {
                    result = true;
                }
            }//_connection is an available

        } finally {
            // _Khai bao truoc thi dong truoc theo chieu nguoc lai
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //_Sau cung se tra ra obj dto cua moi lan login vao thanh cong
        return result;
    }

    public boolean updateAccount(String username, String password, String isAdmin)
            throws SQLException, ClassNotFoundException {
        //_Bien chot cua ham de tra ra gia tri
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. model connects DB
            //_Khai bao bien va gan null
            //_Phai dong tat ca cac doi tuong bang moi cach
            //_Thuc hien xu li
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. model truy van du lieu tu DB
                //2.1. create SQL String
                //_Moi menh de cua cau lenh SQL phai duoc viet tren 1 dong
                //_Truoc khi xuong dong phai chen mot khoang trang
                //neu khong se co loi la SyntaxFromNear...
                //_Tat ca cac table ten cot phai copy tu DB 

                //**Luu y: vi method nay da truyen vao username va password nen
                //minh se khong query ra nua ma chi lay nhung cot chua co
                // Xoa tat ca cac dong duoi table bang mot keyword co dinh
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username = ?";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setString(1, password);

                //**Vi trong database thi boolean duoc luu la "1" va "0"
                //chinh vi vay neu nhu ma checked thi se set la "1" va nguoc lai
                if (isAdmin != null && isAdmin.equals("ON")) {
                    stm.setString(2, "1");
                } else {
                    stm.setString(2, "0");
                }
                stm.setString(3, username);
                //2.3. excute query

                // Tat ca cac cau lenh insert delete update thi tra ra integer
                //va se la excuteUptate chu khong con excuteQuery
                int effectRows = stm.executeUpdate();

                //3. checking effect rows are valid
                //then model set data to properties of model
                if (effectRows > 0) {
                    result = true;
                }
            }//_connection is an available

        } finally {
            // _Khai bao truoc thi dong truoc theo chieu nguoc lai
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //_Sau cung se tra ra obj dto cua moi lan login vao thanh cong
        return result;
    }

    // Neu nhieu hon 2 paramter nen suy nghi truyen object
    public boolean createAccount(RegistrationDTO account) throws SQLException, ClassNotFoundException {
        //_Bien chot cua ham de tra ra gia tri
        //boolean result = false;
        //_Gio ket qua chot khi login thanh cong se la dong welcome + lastname
        //chinh vi vay phai chinh lai bien chot ket qua
        //nghia la se tra ra luon dto chu khong con la boolean de chot ket qua nua
        boolean result = false;

        Connection con = null;
        PreparedStatement stm = null;
        

        try {
            //1. model connects DB
            //_Khai bao bien va gan null
            //_Phai dong tat ca cac doi tuong bang moi cach
            //_Thuc hien xu li
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. model truy van du lieu tu DB
                //2.1. create SQL String
                //_Moi menh de cua cau lenh SQL phai duoc viet tren 1 dong
                //_Truoc khi xuong dong phai chen mot khoang trang
                //neu khong se co loi la SyntaxFromNear...
                //_Tat ca cac table ten cot phai copy tu DB 

                //**Luu y: vi method nay da truyen vao username va password nen
                //minh se khong query ra nua ma chi lay nhung cot chua co
                String sql = "Insert Into Registration("
                        + "username, password, lastname, isAdmin"
                        + ") Values("
                        + "?, ?, ?, ?"
                        + ")";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setString(1, account.getUsername());
                stm.setString(2, account.getPassword());
                stm.setString(3, account.getFullName());
                stm.setBoolean(4, account.isRole());
                //2.3. excute query
                int effectRows = stm.executeUpdate();
                
                //_R -> Result Set. Moi phan tu trong ds nay tuong ung voi 1 row
                //trong table dong dau tien chu BOF dong cuoi EOF
                //con tro luon luon tro dong dau tien, muon qua thang tiep theo 
                //phai su dung phuong thuc next, con chua du lieu tra ra true,
                //khong con chua thi se ra false. Dac tinh cua con tro la forward
                //only chi di toi cuoi cung
                //_Khong bh co chuyen result set = null thi ko co du lieu
                //3. model gets data from result set, then
                // model sets data to properties of model
                //_ Ra mot dong do username la PK
                if(effectRows > 0){
                    result = true;
                }
                
            }//_connection is an available

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        //_Sau cung se tra ra obj dto cua moi lan login vao thanh cong
        return result;
    }

}

//Tai bai so 4 va hoc cu phap
//ScriptingElements: viet nhu the nao, dung nhu the nao
//+Declaration
//+Scriptlets
//+Expression
//+Implicit Object
