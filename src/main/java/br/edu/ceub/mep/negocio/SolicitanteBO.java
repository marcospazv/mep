package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Chamado;
import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Lotacao;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class SolicitanteBO {

    public SolicitanteBO() {
    }

    public void incluirSolicitante(String cpf, String nome, LocalDate dtNasc, Boolean excluido ,Lotacao lotacao, String senha, String salt, String sexo) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Solicitante solicitante = new Solicitante(cpf, nome, sexo, dtNasc, excluido, senha, salt, lotacao);
        //Solicitante solicitante = new Solicitante(cpf, nome, dtNasc, excluido, senha, salt, lotacao);
        em.persist(solicitante);

        em.getTransaction().commit();
        em.close();
    }

    public Solicitante getSolicitante(String cpf) {

        Solicitante solicitante;

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Query query = em.createQuery("FROM Solicitante s WHERE s.cpf = ?1");
        query.setParameter(1, cpf);

        solicitante = (Solicitante) query.getSingleResult();

        em.getTransaction().commit();
        em.close();
        return solicitante;
    }

    public Solicitante pegaSolicitante(String cpf) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Solicitante solicitante = em.find(Solicitante.class, cpf);
        

        em.getTransaction().commit();
        em.close();

        return solicitante;
    }

    public Boolean excluirSolicitante(String cpf) {
        // O registro não será excluído, apenas inativado no sistema
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        Solicitante solicitante;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("FROM Solicitante s WHERE s.cpf = ?1");
            query.setParameter(1, cpf);

            solicitante = (Solicitante) query.getSingleResult();
            solicitante.setExcluido(Boolean.TRUE);
            em.merge(solicitante);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            System.err.println("Erro ao excluir solicitante de cpf:" + cpf + " : " + e.getMessage());
            return false;
        }
    }

    public void alterarSolicitante(String cpf, String nome, LocalDate dtNasc, String cpfAntigo, Lotacao lotacao) {
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        Solicitante solicitante;
        em.getTransaction().begin();
        
        Query query = em.createQuery("FROM Solicitante s WHERE s.cpf = ?1");
        query.setParameter(1, cpfAntigo);

        solicitante = (Solicitante) query.getSingleResult();
        solicitante.setNomeSolicitante(nome);
        solicitante.setDtNascimento(dtNasc);
        solicitante.setCpf(cpf);
        solicitante.setLotacao(lotacao);
        em.merge(solicitante);
        
        em.getTransaction().commit();
        em.close();

    }
    
     public Boolean alterarSenha(String cpf, String senha, String novaSenha,String novoSalt){
        
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Solicitante s;
        
        
            Query query = em.createQuery("FROM Solicitante s WHERE s.cpf = ?1 and s.senha = ?2");
            query.setParameter(1, cpf);
            query.setParameter(2, senha);

            s = (Solicitante) query.getSingleResult();
            s.setSenha(novaSenha);
            s.setSalt(novoSalt);
            em.merge(s);

            em.getTransaction().commit();
            em.close();
            return true;
            
        
    }
     
     public List<Chamado> getChamados(Integer idSolicitante) {
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        List<Chamado> chamados;
        Solicitante s = new Solicitante();
        s.setId(idSolicitante);
        Query query = em.createQuery("FROM Chamado c WHERE c.solicitante = ?1 ");
        query.setParameter(1, s);
        
        query.setMaxResults(50);
        chamados = query.getResultList();

        em.getTransaction().commit();
        em.close();
        return chamados;
    }

     
     public Funcionario verificarFuncionario(String cpf){
         
         try {
 
             EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

             Funcionario funcionario;

             em.getTransaction().begin();

             Query query = em.createQuery("FROM Funcionario f WHERE f.cpf = ?1");
             query.setParameter(1, cpf);

             funcionario = (Funcionario) query.getSingleResult();

             em.getTransaction().commit();
             em.close();

             return funcionario;
             
         } catch (NoResultException ne) {
             return null;
         }
         
         
     }
}
