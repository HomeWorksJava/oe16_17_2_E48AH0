/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "KurzusJelentkezok")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KurzusJelentkezok.findAll", query = "SELECT k FROM KurzusJelentkezok k")
    , @NamedQuery(name = "KurzusJelentkezok.findByKurzusId", query = "SELECT k FROM KurzusJelentkezok k WHERE k.kurzusJelentkezokPK.kurzusId = :kurzusId")
    , @NamedQuery(name = "KurzusJelentkezok.findByUserId", query = "SELECT k FROM KurzusJelentkezok k WHERE k.kurzusJelentkezokPK.userId = :userId")
    , @NamedQuery(name = "KurzusJelentkezok.findByUserAndKurzusId", query = "SELECT k FROM KurzusJelentkezok k WHERE k.kurzusJelentkezokPK.userId = :userId AND k.kurzusJelentkezokPK.kurzusId = :kurzusId")
    , @NamedQuery(name = "KurzusJelentkezok.findByJelentkezesDatum", query = "SELECT k FROM KurzusJelentkezok k WHERE k.jelentkezesDatum = :jelentkezesDatum")})
    public class KurzusJelentkezok implements Serializable {
    // Transient miatt bebugolhat a netbeans és aláhúzza a classt
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KurzusJelentkezokPK kurzusJelentkezokPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "jelentkezes_datum")
    @Temporal(TemporalType.DATE)
    private Date jelentkezesDatum;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kurzus_id", insertable = false, updatable = false)
    @JsonIgnore
    private Kurzus kurzus;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private Users users;

    @Transient
    private String username;
    
    
    public KurzusJelentkezok() {
    }

    public KurzusJelentkezok(KurzusJelentkezokPK kurzusJelentkezokPK) {
        this.kurzusJelentkezokPK = kurzusJelentkezokPK;
    }
    
    public KurzusJelentkezok(KurzusJelentkezokPK kurzusJelentkezokPK, Date jelentkezesDatum) {
        this.kurzusJelentkezokPK = kurzusJelentkezokPK;
        this.jelentkezesDatum = jelentkezesDatum;
    }

    public KurzusJelentkezok(int kurzusId, int userId) {
        this.kurzusJelentkezokPK = new KurzusJelentkezokPK(kurzusId, userId);
    }
    
    public KurzusJelentkezok(int kurzusId, int userId, Date jelentkezesDatum) {
        this.kurzusJelentkezokPK = new KurzusJelentkezokPK(kurzusId, userId);
        this.jelentkezesDatum = jelentkezesDatum;
    }

    public KurzusJelentkezokPK getKurzusJelentkezokPK() {
        return kurzusJelentkezokPK;
    }

    public void setKurzusJelentkezokPK(KurzusJelentkezokPK kurzusJelentkezokPK) {
        this.kurzusJelentkezokPK = kurzusJelentkezokPK;
    }

    public Date getJelentkezesDatum() {
        return jelentkezesDatum;
    }

    public void setJelentkezesDatum(Date jelentkezesDatum) {
        this.jelentkezesDatum = jelentkezesDatum;
    }

    public Kurzus getKurzus() {
        return kurzus;
    }
    
    public void setKurzus(Kurzus kurzus) {
        this.kurzus = kurzus;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kurzusJelentkezokPK != null ? kurzusJelentkezokPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KurzusJelentkezok)) {
            return false;
        }
        KurzusJelentkezok other = (KurzusJelentkezok) object;
        if ((this.kurzusJelentkezokPK == null && other.kurzusJelentkezokPK != null) || (this.kurzusJelentkezokPK != null && !this.kurzusJelentkezokPK.equals(other.kurzusJelentkezokPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datamodel.KurzusJelentkezok[ kurzusJelentkezokPK=" + kurzusJelentkezokPK + " ]";
    }

    /**
     * @return the username
     */
    @Transient
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    @Transient
    public void setUsername(String username) {
        this.username = username;
    }
    
}
