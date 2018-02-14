/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Compte;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author acer
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {

    @PersistenceContext(unitName = "testPU")
    private EntityManager em;
    @EJB
    private OperationFacade operationFacade;
    public int debiter(String idCompte, double mt) {
        Compte compte = find(idCompte);
        if (compte.getSolde() < mt) {
            return -1;
        }
        compte.setSolde(compte.getSolde() - mt);
        return 1;
    }
public void remove(String rib){
    operationFacade.removeByCompte(rib);
    super.remove(new Compte(rib));
}
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }

}
