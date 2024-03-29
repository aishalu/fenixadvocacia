/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.advocacia.modelo.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author LUBADIKA
 */
@Entity
@Table(name = "estado_processo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoProcesso.findAll", query = "SELECT e FROM EstadoProcesso e")
    , @NamedQuery(name = "EstadoProcesso.findByIdEstadoProcesso", query = "SELECT e FROM EstadoProcesso e WHERE e.idEstadoProcesso = :idEstadoProcesso")
    , @NamedQuery(name = "EstadoProcesso.findByEstadoProcesso", query = "SELECT e FROM EstadoProcesso e WHERE e.estadoProcesso = :estadoProcesso")})
public class EstadoProcesso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_estado_processo")
    private Integer idEstadoProcesso;
    @Size(max = 45)
    @Column(name = "estado_processo")
    private String estadoProcesso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoProcesso")
    private Collection<Processo> processoCollection;

    public EstadoProcesso() {
    }

    public EstadoProcesso(Integer idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }

    public Integer getIdEstadoProcesso() {
        return idEstadoProcesso;
    }

    public void setIdEstadoProcesso(Integer idEstadoProcesso) {
        this.idEstadoProcesso = idEstadoProcesso;
    }

    public String getEstadoProcesso() {
        return estadoProcesso;
    }

    public void setEstadoProcesso(String estadoProcesso) {
        this.estadoProcesso = estadoProcesso;
    }

    @XmlTransient
    public Collection<Processo> getProcessoCollection() {
        return processoCollection;
    }

    public void setProcessoCollection(Collection<Processo> processoCollection) {
        this.processoCollection = processoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoProcesso != null ? idEstadoProcesso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoProcesso)) {
            return false;
        }
        EstadoProcesso other = (EstadoProcesso) object;
        if ((this.idEstadoProcesso == null && other.idEstadoProcesso != null) || (this.idEstadoProcesso != null && !this.idEstadoProcesso.equals(other.idEstadoProcesso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fenixadvocacia.modelo.entities.EstadoProcesso[ idEstadoProcesso=" + idEstadoProcesso + " ]";
    }
    
}
