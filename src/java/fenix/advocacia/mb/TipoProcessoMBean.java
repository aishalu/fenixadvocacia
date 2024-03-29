/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.advocacia.mb;

import fenix.advocacia.dao.ejbs.TipoProcessoFacade;
import fenix.advocacia.modelo.entities.TipoProcesso;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author LUBADIKA
 */
@Named(value = "tipoProcessoMBean")
@SessionScoped
public class TipoProcessoMBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TipoProcesso tipoProcesso;
    private List<TipoProcesso> tipoProcessos;

    @Inject
    TipoProcessoFacade tipoProcessoFacade; //injectamos o facade em vez do DAO

    public TipoProcessoMBean() {
    }

    @PostConstruct
    public void inicializar() {
        tipoProcesso = new TipoProcesso();
    }

    public TipoProcesso getTipoProcesso() {
        return tipoProcesso;
    }

    public void setTipoProcesso(TipoProcesso tipoProcesso) {
        this.tipoProcesso = tipoProcesso;
    }

    public List<TipoProcesso> getTipoProcessos() {

        tipoProcessos = tipoProcessoFacade.findAll();
        return tipoProcessos;
    }

    public void setTipoProcessos(List<TipoProcesso> tipoProcessos) {
        this.tipoProcessos = tipoProcessos;
    }

    public void save(ActionEvent evt) {
        tipoProcessoFacade.create(tipoProcesso);

        tipoProcesso = new TipoProcesso();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));

    }

    public String startEdit() {
        return "tipoProcesso_listar?faces-redirect=true";
    }

    public void edit(ActionEvent evt) {
        tipoProcessoFacade.edit(tipoProcesso);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao editar os dados"));

        tipoProcesso = null;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("tipoProcesso_listar.jsf");

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TipoProcessoMBean.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public String delete(ActionEvent evt) {

        tipoProcessoFacade.remove(tipoProcesso);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao deletar os dados"));
        tipoProcesso = null;
        return "tipoProcesso_listar?faces-redirect=true";
    }

}
