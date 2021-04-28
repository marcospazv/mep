package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Chamado;
import br.edu.ceub.mep.entity.Equipamento;
import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Manutencao;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.entity.StatusChamado;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ChamadoBO {

    public ChamadoBO() {
    }

    public void incluirChamado(String motivo, LocalDateTime dtChamado, Equipamento equipamento, StatusChamado statusChamado, Solicitante solicitante) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Chamado chamado = new Chamado(motivo, dtChamado, equipamento, statusChamado, solicitante);
        em.persist(chamado);

        em.getTransaction().commit();
        em.close();

    }

    //consulta os chamados abertos pro funcionario
    public List<Chamado> getChamados() {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Chamado> chamados;

        chamados = em.createQuery("FROM Chamado c WHERE c.statusChamado = 1 or c.statusChamado = 5").getResultList();

        em.getTransaction().commit();
        em.close();
        return chamados;

    }

    public void atenderChamado(Funcionario f, Integer idChamado, StatusChamado sc) {

        Chamado c;
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Chamado> chamados;

        Query query = em.createQuery("FROM Chamado c WHERE c.id = ?1");
        query.setParameter(1, idChamado);

        c = (Chamado) query.getSingleResult();
        c.setFuncionario(f);
        c.setStatusChamado(sc);
        em.merge(c);

        em.getTransaction().commit();
        em.close();

    }

    public List<Chamado> consultaAtendidos(Funcionario f) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Chamado> chamados;
        Query query = em.createQuery("FROM Chamado c WHERE c.funcionario = ?1 and c.statusChamado = 2");
        query.setParameter(1, f);

        chamados = query.getResultList();

        em.getTransaction().commit();
        em.close();
        return chamados;

    }

    public void realizarManutencao(Chamado c, Funcionario f, LocalDateTime dtManutencao, String descricao) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Manutencao manutencao = new Manutencao(descricao, dtManutencao, f, c);
        em.persist(manutencao);

        em.getTransaction().commit();
        em.close();

    }
//    

    //Altera o status do chamado para atendido
    public void chamadoManutencao(Integer idChamado, StatusChamado sc) {

        Chamado c;
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("FROM Chamado c WHERE c.id = ?1");
        query.setParameter(1, idChamado);

        c = (Chamado) query.getSingleResult();
        c.setStatusChamado(sc);
        em.merge(c);

        em.getTransaction().commit();
        em.close();

    }

    public void fecharChamado(Integer idChamado, StatusChamado sc) {

        Chamado c;
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("FROM Chamado c WHERE c.id = ?1");
        query.setParameter(1, idChamado);

        c = (Chamado) query.getSingleResult();
        c.setStatusChamado(sc);
        em.merge(c);

        em.getTransaction().commit();
        em.close();

    }

    public void reabrirChamado(Integer idChamado, StatusChamado sc) {

        Chamado c;
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("FROM Chamado c WHERE c.id = ?1");
        query.setParameter(1, idChamado);

        c = (Chamado) query.getSingleResult();
        c.setStatusChamado(sc);
        em.merge(c);

        em.getTransaction().commit();
        em.close();

    }

    public Chamado getChamadoById(Integer idChamado) {

        Chamado chamado;
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("FROM Chamado c WHERE c.id = ?1");
        query.setParameter(1, idChamado);
        
        chamado = (Chamado) query.getSingleResult();
        
        em.getTransaction().commit();
        em.close();

        return chamado;
    }
    
    
      public void salvarChamado(String motivo, LocalDateTime dtChamado, Equipamento equipamento, Integer idChamado) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Query query = em.createQuery("FROM Chamado c WHERE c.id = ?1");
        query.setParameter(1, idChamado);
        
        Chamado chamado = (Chamado)query.getSingleResult();
        
        chamado.setMotivo(motivo);
        chamado.setEquipamento(equipamento);
        chamado.setDtChamado(dtChamado);
        em.merge(chamado);

        em.getTransaction().commit();
        em.close();

    }

      
     public void excluirChamado(Integer idChamado){
         
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Chamado chamado = em.getReference(Chamado.class, idChamado);
        em.remove(chamado);

        em.getTransaction().commit();
        em.close();
         
     } 
     
     
    
}
