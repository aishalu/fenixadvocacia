/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fenix.advocacia.mb;

import fenix.advocacia.dao.ejbs.TipoDecisaoFacade;
import fenix.advocacia.modelo.entities.TipoDecisao;
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
@Named(value = "tipoDecisaoMBean")
@SessionScoped
public class TipoDecisaoMBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TipoDecisao tipoDecisao;
    private List<TipoDecisao> tipoDecisaos;

    public TipoDecisaoMBean() {
    }
    
    @Inject
    TipoDecisaoFacade tipoDecisaoFacade;
    
    @PostConstruct
    public void inicializar() {
       tipoDecisao= new TipoDecisao();
   }

    public TipoDecisao getTipoDecisao() {
        return tipoDecisao;
    }

    public void setTipoDecisao(TipoDecisao tipoDecisao) {
        this.tipoDecisao = tipoDecisao;
    }

    public List<TipoDecisao> getTipoDecisaos() {
        tipoDecisaoFacade.findAll();
        return tipoDecisaos;
    }

    public void setTipoDecisaos(List<TipoDecisao> tipoDecisaos) {
        this.tipoDecisaos = tipoDecisaos;
    }
    public void save(ActionEvent evt) {
        tipoDecisaoFacade.create(tipoDecisao);

        tipoDecisao = new TipoDecisao();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao guardar os dados"));

    }

    public String startEdit() {
        return "tipoDecisao_listar?faces-redirect=true";
    }
    
      public void edit(ActionEvent evt) {
        tipoDecisaoFacade.edit(tipoDecisao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao editar os dados"));

        tipoDecisao = null;

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("tipoDecisoa_listar.jsf");

        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(TipoDecisaoMBean.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
      
        public String delete(ActionEvent evt) {

        tipoDecisaoFacade.remove(tipoDecisao);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardar\t", "\tSucesso ao deletar os dados"));
        tipoDecisao = null;
        return "tipoDecisao_listar?faces-redirect=true";
    }
    
    
    

}
