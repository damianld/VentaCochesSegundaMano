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
@Table(name = "reparacionesmecanicas")
@XmlRootElement
public class ReparacionMecanica extends Reparacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IDREPARACION")
    private int idreparacion;
    @Basic(optional = false)
    @Column(name = "PARTE")
    private String parte;
    @JoinColumn(name = "IDREPARACION", referencedColumnName = "IDREPARACION", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Reparacion reparacion;

    public ReparacionMecanica() {
    }

    public ReparacionMecanica(Integer idreparacion) {
        this.idreparacion = idreparacion;
    }

    public ReparacionMecanica( String parte) {
       
        this.parte = parte;
    }

    public int getIdreparacion() {
        return idreparacion;
    }

    public void setIdreparacion(int idreparacion) {
        this.idreparacion = idreparacion;
    }

    public String getParte() {
        return parte;
    }

    public void setParte(String parte) {
        this.parte = parte;
    }

    public Reparacion getReparacion() {
        return reparacion;
    }

    public void setReparacion(Reparacion reparacion) {
        this.reparacion = reparacion;
    }

    

    @Override
    public String toString() {
        return "mapeos.ReparacionMecanica[ idreparacion=" + idreparacion + " ]";
    }
    
}
