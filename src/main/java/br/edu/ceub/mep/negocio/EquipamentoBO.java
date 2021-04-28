package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Equipamento;
import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Setor;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EquipamentoBO {

    public Integer incluirEquipamento(String nome,Boolean excluido,Setor setor, Funcionario funcionario) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Equipamento equipamento = new Equipamento(nome, excluido, setor, funcionario);

        em.persist(equipamento);

        em.getTransaction().commit();
        em.close();
        return equipamento.getId();
        
    }

    public List<Setor> listaSetores() {

        List<Setor> setores;
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        setores = em.createQuery("FROM Setor").getResultList();

        em.getTransaction().commit();
        em.close();

        return setores;
    }

    //Consulta de equipamento
    public Equipamento getEquipamento(Integer id) {
        Equipamento equipamento;

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("FROM Equipamento e WHERE e.id = ?1");
        query.setParameter(1, id);

        equipamento = (Equipamento) query.getSingleResult();

        em.getTransaction().commit();
        em.close();

        return equipamento;
    }

    public Equipamento pegaEquipamento(Integer id) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Equipamento equipamento = em.find(Equipamento.class, id);

        em.getTransaction().commit();
        em.close();
        return equipamento;
    }

    public void alterarEquipamento(Integer id, String nome, Setor setor) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        Equipamento equipamento = em.find(Equipamento.class, id);
        equipamento.setNomeEquipamento(nome);
        equipamento.setSetor(setor);
        
        em.merge(equipamento);
        em.getTransaction().commit();
        em.close();
        

    }
    
    public Boolean excluirEquipamento(Integer id){
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        
        try {
            em.getTransaction().begin();
            Equipamento equipamento = em.find(Equipamento.class, id);
            equipamento.setExcluido(Boolean.TRUE);
            em.merge(equipamento);
            
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
             System.err.println("Erro ao excluir equipamento de id: " + id + " : " + e.getMessage());
            return false;
        }
        
    }

}
