/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodel;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author hallgato
 */
@Entity
@Table(name = "Kurzus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kurzus.findAll", query = "SELECT k FROM Kurzus k")
    , @NamedQuery(name = "Kurzus.findByKurzusId", query = "SELECT k FROM Kurzus k WHERE k.kurzusId = :kurzusId")
    , @NamedQuery(name = "Kurzus.findByNev", query = "SELECT k FROM Kurzus k WHERE k.nev = :nev")
    , @NamedQuery(name = "Kurzus.findByLeiras", query = "SELECT k FROM Kurzus k WHERE k.leiras = :leiras")
    , @NamedQuery(name = "Kurzus.findByMaxJelentkezok", query = "SELECT k FROM Kurzus k WHERE k.maxJelentkezok = :maxJelentkezok")
    , @NamedQuery(name = "Kurzus.findByIndulasDatum", query = "SELECT k FROM Kurzus k WHERE k.indulasDatum = :indulasDatum")
    , @NamedQuery(name = "Kurzus.findByLetrehozvaDatum", query = "SELECT k FROM Kurzus k WHERE k.letrehozvaDatum = :letrehozvaDatum")
    , @NamedQuery(name = "Kurzus.findByLetrehozoUserId", query = "SELECT k FROM Kurzus k WHERE k.letrehozoUserId = :letrehozoUserId")
    , @NamedQuery(name = "Kurzus.findByAllapot", query = "SELECT k FROM Kurzus k WHERE k.allapot = :allapot")})
public class Kurzus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "kurzus_id")
    private Integer kurzusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nev")
    private String nev;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8191)
    @Column(name = "leiras")
    private String leiras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_jelentkezok")
    private int maxJelentkezok;
    @Basic(optional = false)
    @NotNull
    @Column(name = "indulas_datum")
    @Temporal(TemporalType.DATE)
    private Date indulasDatum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "letrehozva_datum")
    @Temporal(TemporalType.DATE)
    private Date letrehozvaDatum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "letrehozo_user_id")
    private int letrehozoUserId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allapot")
    private int allapot;
   /* @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "kurzus")
    private Collection<KurzusJelentkezok> kurzusJelentkezokCollection;*/
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kurzus",orphanRemoval = true)
    private Set<KurzusJelentkezok> kurzusJelentkezokCollection = new HashSet<>();

    public Kurzus() {
    }

    public Kurzus(Integer kurzusId) {
        this.kurzusId = kurzusId;
    }

    public Kurzus(String nev, String leiras, int maxJelentkezok, Date indulasDatum, Date letrehozvaDatum, int letrehozoUserId, int allapot) {
        this.nev = nev;
        this.leiras = leiras;
        this.maxJelentkezok = maxJelentkezok;
        this.indulasDatum = indulasDatum;
        this.letrehozvaDatum = letrehozvaDatum;
        this.letrehozoUserId = letrehozoUserId;
        this.allapot = allapot;
    }

    public Integer getKurzusId() {
        return kurzusId;
    }

    public void setKurzusId(Integer kurzusId) {
        this.kurzusId = kurzusId;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public int getMaxJelentkezok() {
        return maxJelentkezok;
    }

    public void setMaxJelentkezok(int maxJelentkezok) {
        this.maxJelentkezok = maxJelentkezok;
    }

    public Date getIndulasDatum() {
        return indulasDatum;
    }

    public void setIndulasDatum(Date indulasDatum) {
        this.indulasDatum = indulasDatum;
    }

    public Date getLetrehozvaDatum() {
        return letrehozvaDatum;
    }

    public void setLetrehozvaDatum(Date letrehozvaDatum) {
        this.letrehozvaDatum = letrehozvaDatum;
    }

    public int getLetrehozoUserId() {
        return letrehozoUserId;
    }

    public void setLetrehozoUserId(int letrehozoUserId) {
        this.letrehozoUserId = letrehozoUserId;
    }

    public int getAllapot() {
        return allapot;
    }

    public void setAllapot(int allapot) {
        this.allapot = allapot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kurzusId != null ? kurzusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kurzus)) {
            return false;
        }
        Kurzus other = (Kurzus) object;
        if ((this.kurzusId == null && other.kurzusId != null) || (this.kurzusId != null && !this.kurzusId.equals(other.kurzusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "datamodel.Kurzus[ kurzusId=" + kurzusId + " ]";
    }

    /**
     * @return the kurzusJelentkezokCollection
     */
    public Set<KurzusJelentkezok> getKurzusJelentkezokCollection() {
        return kurzusJelentkezokCollection;
    }
    
    public boolean isRegistered(int uId) {
        Set<KurzusJelentkezok> collection = getKurzusJelentkezokCollection();
        for (KurzusJelentkezok k : collection) {
            if (k.kurzusJelentkezokPK.getUserId()==uId) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param kurzusJelentkezokCollection the kurzusJelentkezokCollection to set
     */
    public void setKurzusJelentkezokCollection(Set<KurzusJelentkezok> kurzusJelentkezokCollection) {
        this.kurzusJelentkezokCollection = kurzusJelentkezokCollection;
    }
    
}
