/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import bean.Compte;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CompteFacade;
import service.OperationFacade;

/**
 *
 * @author acer
 */
@Named(value = "compteControler")
@SessionScoped
public class CompteControler implements Serializable {

    private Compte selected;
    private List<Compte> items;
    @EJB
    private CompteFacade ejbFacade;
    private double montant;
    @EJB
    private OperationFacade operationFacade;

    public void initPram() {
        selected = null;
    }

    public String create() {
        ejbFacade.create(selected);
        initPram();
        return null;
    }

    public void detail(Compte compte) {
        getSelected().setOperations(operationFacade.findByCompte(compte));
    }

    public String debiter() {
        int res = ejbFacade.debiter(selected.getId(), montant);
        if (res > 0) {
            initPram();
            return "/compte/List";
        } else {
            return null;
        }
    }
public void remove(Compte compte){
    ejbFacade.remove(compte.getId());
    items.remove(items.indexOf(compte));
}
    public OperationFacade getOperationFacade() {
        return operationFacade;
    }

    public void setOperationFacade(OperationFacade operationFacade) {
        this.operationFacade = operationFacade;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Compte getSelected() {
        if (selected == null) {
            selected = new Compte();
        }
        return selected;
    }

    public void setSelected(Compte selected) {
        this.selected = selected;
    }

    public List<Compte> getItems() {
        items = ejbFacade.findAll();
        return items;
    }

    public void setItems(List<Compte> items) {
        this.items = items;
    }

    public CompteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CompteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    /**
     * Creates a new instance of CompteControler
     */
    public CompteControler() {
    }

}
