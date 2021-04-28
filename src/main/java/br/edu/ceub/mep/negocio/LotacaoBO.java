package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Lotacao;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;


public class LotacaoBO {

    public LotacaoBO() {
    }
    
    
    
    public void incluirLotacao(String nome){
        
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        
        em.getTransaction().begin();
        
        Lotacao lotacao = new Lotacao(nome);
        
        em.persist(lotacao);
        
        em.getTransaction().commit();
        em.close();
        
    }
    
    public List<Lotacao> getLotacoes(){
        
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        
        List<Lotacao> lotacoes;
        
        em.getTransaction().begin();
        
        lotacoes = em.createQuery("FROM Lotacao").getResultList();
        
        
        em.getTransaction().commit();
        em.close();
        
        return lotacoes;
        
    }
    
    public Boolean excluirLotacao(Integer id){
        
        try {
             EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
             
             em.getTransaction().begin();
             
             Lotacao lotacao = em.getReference(Lotacao.class, id);
             em.remove(lotacao);
             
             em.getTransaction().commit();
             em.close();
            
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
    
}
