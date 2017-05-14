/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "UserRoles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRoles.findAll", query = "SELECT u FROM UserRoles u")
    , @NamedQuery(name = "UserRoles.findByUserId", query = "SELECT u FROM UserRoles u WHERE u.userRolesPK.userId = :userId")
    , @NamedQuery(name = "UserRoles.findByRole", query = "SELECT u FROM UserRoles u WHERE u.userRolesPK.role = :role")})
public class UserRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserRolesPK userRolesPK;

    public UserRoles() {
    }

    public UserRoles(UserRolesPK userRolesPK) {
        this.userRolesPK = userRolesPK;
    }

    public UserRoles(int userId, String role) {
        this.userRolesPK = new UserRolesPK(userId, role);
    }

    public UserRolesPK getUserRolesPK() {
        return userRolesPK;
    }

    public void setUserRolesPK(UserRolesPK userRolesPK) {
        this.userRolesPK = userRolesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userRolesPK != null ? userRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserRoles)) {
            return false;
        }
        UserRoles other = (UserRoles) object;
        if ((this.userRolesPK == null && other.userRolesPK != null) || (this.userRolesPK != null && !this.userRolesPK.equals(other.userRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datamodel.UserRoles[ userRolesPK=" + userRolesPK + " ]";
    }
    
}
