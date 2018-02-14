/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import bean.Operation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.OperationFacade;

/**
 *
 * @author acer
 */
@Named(value = "operationControler")
@SessionScoped
public class OperationControler implements Serializable {
    private List<Operation> items;
    private Operation selected;
    @EJB
    private OperationFacade ejbFacade;
    public String save(){
        ejbFacade.save(selected);
        return null;
    }
    public List<Operation> getItems() {
        return items;
    }

    public void setItems(List<Operation> items) {
        this.items = items;
    }

    public Operation getSelected() {
        if(selected==null){
            selected=new Operation();
        }
        return selected;
    }

    public void setSelected(Operation selected) {
        this.selected = selected;
    }

    public OperationFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(OperationFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }
    
    
    /**
     * Creates a new instance of OperationControler
     */
    public OperationControler() {
    }
    
}
