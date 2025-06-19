/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhannm.registration;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
//_Class nam tai server nen phai implement Serializable
//_Nhiem vu thang nay se 
public class RegistrationDTO implements Serializable {

    private String username;
    private String password;
    private String fullName;
    private boolean role;

    // Default constructor
    public RegistrationDTO() {
    }

    // Constructor full field
    
    public RegistrationDTO(String username, String password, String fullName, boolean role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    
    
    //Getter and Setter
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the role
     */
    public boolean isRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(boolean role) {
        this.role = role;
    }
   
}

// EL syntax thay the cho expression 
// Syntax: ${EL expression}
// Tip1: Khi su dung EL hay standard action thi tat ca moi thu phai la attribute
//(neu co JSP thi ContextScope => ApplicationScope type: ServletContext)
// Tip2: Attribute mac dinh la bien ben trong JSP
// Tip3: 
//+Trong EL khi Attribute dung 1 minh khong kem toan tu
//neu attribute = null thi EL se tu dong ep gia tri thanh ""
//+Nguoc lai neu co attribute kem theo toan tu hay gt != null thi EL
//se co co che tu dong ep kieu
