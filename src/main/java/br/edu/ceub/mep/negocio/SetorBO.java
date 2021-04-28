package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Setor;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;

public class SetorBO {

    public SetorBO() {
    }
    

    public void incluirSetor(String nome) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Setor setor = new Setor(nome);

        em.persist(setor);

        em.getTransaction().commit();
        em.close();

    }

    public List<Setor> getSetores() {
        List<Setor> setores;

        
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        setores = em.createQuery("FROM Setor").getResultList();

        em.getTransaction().commit();
        em.close();

        return setores;
        
    }

    public Boolean excluirSetor(Integer id) {

        try {

            EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Setor setor = em.getReference(Setor.class, id);
            em.remove(setor);

            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.err.println("Erro ao excluir setor de id:" + id + " : " + e.getMessage());
            return false;
        }

        return true;
    }


}
