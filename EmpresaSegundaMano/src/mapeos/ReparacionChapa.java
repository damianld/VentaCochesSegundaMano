/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author a16jorgemf
 */
@MappedSuperclass
@Table(name = "reparacioneschapa")
@XmlRootElement
public class ReparacionChapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDREPARACION")
    private Integer idreparacion;
    @Basic(optional = false)
    @Column(name = "COLORCHAPA")
    private String colorchapa;
    @JoinColumn(name = "IDREPARACION", referencedColumnName = "IDREPARACION", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Reparacion reparacion;

    public ReparacionChapa() {
    }

    public ReparacionChapa(Integer idreparacion) {
        this.idreparacion = idreparacion;
    }

    public ReparacionChapa(Integer idreparacion, String colorchapa) {
        this.idreparacion = idreparacion;
        this.colorchapa = colorchapa;
    }

    public Integer getIdreparacion() {
        return idreparacion;
    }

    public void setIdreparacion(Integer idreparacion) {
        this.idreparacion = idreparacion;
    }

    public String getColorchapa() {
        return colorchapa;
    }

    public void setColorchapa(String colorchapa) {
        this.colorchapa = colorchapa;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreparacion != null ? idreparacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReparacionChapa)) {
            return false;
        }
        ReparacionChapa other = (ReparacionChapa) object;
        if ((this.idreparacion == null && other.idreparacion != null) || (this.idreparacion != null && !this.idreparacion.equals(other.idreparacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mapeos.ReparacionChapa[ idreparacion=" + idreparacion + " ]";
    }
    
}
