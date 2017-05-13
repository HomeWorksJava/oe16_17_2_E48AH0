/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author hallgato
 */
@Embeddable
public class KurzusJelentkezokPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "kurzus_id")
    private int kurzusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private int userId;

    public KurzusJelentkezokPK() {
    }

    public KurzusJelentkezokPK(int kurzusId, int userId) {
        this.kurzusId = kurzusId;
        this.userId = userId;
    }

    public int getKurzusId() {
        return kurzusId;
    }

    public void setKurzusId(int kurzusId) {
        this.kurzusId = kurzusId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) kurzusId;
        hash += (int) userId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KurzusJelentkezokPK)) {
            return false;
        }
        KurzusJelentkezokPK other = (KurzusJelentkezokPK) object;
        if (this.kurzusId != other.kurzusId) {
            return false;
        }
        if (this.userId != other.userId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datamodel.KurzusJelentkezokPK[ kurzusId=" + kurzusId + ", userId=" + userId + " ]";
    }
    
}
