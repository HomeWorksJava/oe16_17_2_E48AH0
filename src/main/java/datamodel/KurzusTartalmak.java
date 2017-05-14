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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "KurzusTartalmak")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KurzusTartalmak.findAll", query = "SELECT k FROM KurzusTartalmak k")
    , @NamedQuery(name = "KurzusTartalmak.findByTartalomId", query = "SELECT k FROM KurzusTartalmak k WHERE k.tartalomId = :tartalomId")
    , @NamedQuery(name = "KurzusTartalmak.findByCim", query = "SELECT k FROM KurzusTartalmak k WHERE k.cim = :cim")
    , @NamedQuery(name = "KurzusTartalmak.findByTartalom", query = "SELECT k FROM KurzusTartalmak k WHERE k.tartalom = :tartalom")
    , @NamedQuery(name = "KurzusTartalmak.findByLetrehozva", query = "SELECT k FROM KurzusTartalmak k WHERE k.letrehozva = :letrehozva")})
public class KurzusTartalmak implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tartalom_id")
    private Integer tartalomId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cim")
    private String cim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8191)
    @Column(name = "tartalom")
    private String tartalom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "letrehozva")
    @Temporal(TemporalType.DATE)
    private Date letrehozva;
    /*@JoinColumn(name = "kurzus_id", referencedColumnName = "kurzus_id")
    @ManyToOne(optional = false)*/
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kurzus_id", insertable = false, updatable = false)
    @JsonIgnore
    private Kurzus kurzusId;

    @Column(name = "kurzus_id")
    private int kurzus_Id;
    
    public KurzusTartalmak() {
    }

    public KurzusTartalmak(Integer tartalomId) {
        this.tartalomId = tartalomId;
    }

    public KurzusTartalmak(Integer tartalomId, String cim, String tartalom, Date letrehozva) {
        this.tartalomId = tartalomId;
        this.cim = cim;
        this.tartalom = tartalom;
        this.letrehozva = letrehozva;
    }

    public Integer getTartalomId() {
        return tartalomId;
    }

    public void setTartalomId(Integer tartalomId) {
        this.tartalomId = tartalomId;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public String getTartalom() {
        return tartalom;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
    }

    public Date getLetrehozva() {
        return letrehozva;
    }

    public void setLetrehozva(Date letrehozva) {
        this.letrehozva = letrehozva;
    }

    public Kurzus getKurzusId() {
        return kurzusId;
    }

    public void setKurzusId(Kurzus kurzusId) {
        this.kurzusId = kurzusId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tartalomId != null ? tartalomId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KurzusTartalmak)) {
            return false;
        }
        KurzusTartalmak other = (KurzusTartalmak) object;
        if ((this.tartalomId == null && other.tartalomId != null) || (this.tartalomId != null && !this.tartalomId.equals(other.tartalomId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datamodel.KurzusTartalmak[ tartalomId=" + tartalomId + " ]";
    }

    /**
     * @return the kurzus_Id
     */
    public int getKurzus_Id() {
        return kurzus_Id;
    }

    /**
     * @param kurzus_Id the kurzus_Id to set
     */
    public void setKurzus_Id(int kurzus_Id) {
        this.kurzus_Id = kurzus_Id;
    }
    
}
