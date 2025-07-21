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
import java.util.ArrayList;
import java.util.List;
import pe.utils.DbUtils;

/**
 *
 * @author Hi
 */
public class TblWatchDAO implements Serializable {
    private List<TblWatchDTO> accounts;

    //_Phuong thuc tra ra mot list
    public List<TblWatchDTO> getAccounts() {
        return accounts;
    }
    
    public void searchRangeQuantity (int searchMinValue, int searchMaxValue)
            throws SQLException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1. model connects DB
            //_Khai bao bien va gan null
            //_Phai dong tat ca cac doi tuong bang moi cach
            //_Thuc hien xu li
            con = DbUtils.getConnection();
            if (con != null) {
                //_Lay tat ca cac cot trong bang registration voi dk
                //cot lastname co chua sort string
                String sql = "Select id, name, brandName, price, size, quantity, description "
                        + "From tbl_Watch "
                        + "Where quantity >= ? And quantity <= ?";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setInt(1, searchMinValue);
                stm.setInt(2, searchMaxValue);

                //2.3. excute query
                rs = stm.executeQuery();

                //_Duyet du lieu(model get du lieu tu result set)
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String brandName = rs.getString("brandName");
                    double price = rs.getDouble("price");
                    int size = rs.getInt("size");
                    int quantity = rs.getInt("quantity");
                    String description = rs.getString("description");
                   
                    //_Tao DTO vi cac gt dat trong DTO
                    TblWatchDTO dto = new TblWatchDTO(id, name, brandName, price, size, quantity, description);
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
    
    public boolean updateAccount(int id, String description, double price, int quantity)
            throws SQLException, ClassNotFoundException {
        //_Bien chot cua ham de tra ra gia tri
        boolean result = false;
        Connection con = null;
        PreparedStatement stm = null;

        try {
            
            con = DbUtils.getConnection();
            if (con != null) {
                
                String sql = "Update tbl_Watch "
                        + "Set description = ?, price = ?, quantity = ? "
                        + "Where id = ?";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setString(1, description);
                stm.setDouble(2, price);
                stm.setInt(3, quantity);
                stm.setInt(4, id);

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
    
    public boolean deleteAccount(int id)
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
            con = DbUtils.getConnection();
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
                String sql = "Delete From tbl_Watch "
                        + "Where id = ?";
                //Cau lenh SQL la cau lenh tinh va nap vao the truy van
                //2.2. create Statement Objects
                stm = con.prepareStatement(sql);
                //_Truyen dung so, co bao nhieu dau ? thi phai truyen het
                stm.setInt(1, id);
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
}
