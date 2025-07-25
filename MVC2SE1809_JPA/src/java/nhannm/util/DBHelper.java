/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhannm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class DBHelper {
    //-- JDBC API: la mot thu vien hay farm de ho tro
    //viet code hoac ket noi tuong tac den DB, chi viet 1 lan
    //bat chap su dung DBMS nao
    
    public static Connection makeConnection()
    // Kiem tra driver coi co sai ten gi khong neu no bao loi
    // Hoac la ten driver duoi sai
    throws ClassNotFoundException, SQLException {
        //1. Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connection String to connect container
        //luu y dau ";" tuong ung dau "/"
        //syntax: jdbc:sqlserver://ip:port;databaseName=...
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1809";
        //3. Open connect using Driver Manager
        //_Dòng này dùng DriverManager để mở kết nối đến database
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        
        return con;
    }
}

//SQLException: 
//+Sai URL kết nối.
//+Sai tên người dùng hoặc mật khẩu.

//ClassNotFoundException
//+Lỗi xảy ra nếu driver chưa được thêm vào project 
//+Sai tên driver