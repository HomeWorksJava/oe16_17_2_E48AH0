/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserId", query = "SELECT u FROM Users u WHERE u.userId = :userId")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPasswd", query = "SELECT u FROM Users u WHERE u.passwd = :passwd")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Size(max = 255)
    @Column(name = "passwd")
    private String passwd;
    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<KurzusJelentkezok> kurzusJelentkezokCollection;*/
    /*@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "users",orphanRemoval = true)
    private Set<KurzusJelentkezok> kurzusJelentkezokCollection = new HashSet<>();*/

    public Users() {
    }

    public Users(Integer userId) {
        this.userId = userId;
    }

    public Users(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
    }
    
    public Users(String username, String password) {
        this.username = username;
        this.passwd = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datamodel.Users[ userId=" + userId + " ]";
    }

    /**
     * @return the kurzusJelentkezokCollection
     */
    /*public Set<KurzusJelentkezok> getKurzusJelentkezokCollection() {
        return kurzusJelentkezokCollection;
    }

    /**
     * @param kurzusJelentkezokCollection the kurzusJelentkezokCollection to set
     */
    /*
    public void setKurzusJelentkezokCollection(Set<KurzusJelentkezok> kurzusJelentkezokCollection) {
        this.kurzusJelentkezokCollection = kurzusJelentkezokCollection;
    }*/
    
}
