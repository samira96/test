/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import bean.Operation;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author acer
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> {

    @PersistenceContext(unitName = "testPU")
    private EntityManager em;
    @EJB
    private CompteFacade ejbFacade;

    public List<Operation> findByCompte(Compte compte) {
        return em.createQuery("SELECT op FROM Operation op where op.compte.id='" + compte.getId() + "'").getResultList();
    }

    public int save(Operation operation) {
        Compte compte = ejbFacade.find(operation.getCompte().getId());
        Double nvSolde = 0.0;
        int res = 0;
        if (compte.getSolde() < operation.getMontant() && operation.getType() == 2) {
            return -1;
        } else if (operation.getType() == 1) {
            nvSolde = compte.getSolde() + operation.getMontant();
            res = 1;
        } else if (operation.getType() == 2) {
            nvSolde = compte.getSolde() - operation.getMontant();
             res = 2;
        }
        compte.setSolde(nvSolde);
        ejbFacade.edit(compte);
        operation.setId(genereteAttribute("Operation", "id"));
        create(operation);
        return res;
    }
    public int removeByCompte(String rib){
        return em.createQuery("DELETE FROM Operation op WHERE op.compte.id='"+rib+"'").executeUpdate();
                }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

}
