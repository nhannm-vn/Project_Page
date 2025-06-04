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
//-- JDBC API: la mot thu vien hay farm de ho tro
//viet code hoac ket noi tuong tac den DB, chi viet 1 lan
//bat chap su dung DBMS nao
public class DBHelper {

    public static Connection makeConnection()
            throws ClassNotFoundException, SQLException {
        //1. Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connection String to connect container
        //luu y dau ";" tuong ung dau "/"
        //syntax: jdbc:sqlserver://ip:port;databaseName=...
        String url = "jdbc:sqlserver://"
                + "localhost:1433;"
                + "databaseName=PRJSE1809";
        //3. Open connect using Driver Manger
        //_Dong nay dung de DriverManager de mo ket noi den database
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
}
