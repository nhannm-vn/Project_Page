/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nhannm.registration;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "Registration", catalog = "PRJSE1809", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Registration.findAll", query = "SELECT r FROM Registration r"),
    @NamedQuery(name = "Registration.findByUsername", query = "SELECT r FROM Registration r WHERE r.username = :username"),
    @NamedQuery(name = "Registration.findByPassword", query = "SELECT r FROM Registration r WHERE r.password = :password"),
    @NamedQuery(name = "Registration.findByLastname", query = "SELECT r FROM Registration r WHERE r.lastname = :lastname"),
    @NamedQuery(name = "Registration.findByIsAdmin", query = "SELECT r FROM Registration r WHERE r.isAdmin = :isAdmin")})
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 30)
    private String password;
    @Basic(optional = false)
    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;
    @Basic(optional = false)
    @Column(name = "isAdmin", nullable = false)
    private boolean isAdmin;

    public Registration() {
    }

    public Registration(String username) {
        this.username = username;
    }

    public Registration(String username, String password, String lastname, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Registration)) {
            return false;
        }
        Registration other = (Registration) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "nhannm.controller.Registration[ username=" + username + " ]";
    }
    
}
