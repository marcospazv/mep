package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class LoginBO {

    public Funcionario verificaFuncionario(String cpf, String senha) {

        Funcionario f;

        try {
            EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("FROM Funcionario f WHERE f.cpf = ?1 and f.senha = ?2");
            query.setParameter(1, cpf);
            query.setParameter(2, senha);

            f = (Funcionario) query.getSingleResult();

            em.getTransaction().commit();
            em.close();
            return f;

        } catch (NoResultException nr) {
            return null;
        }

    }

    public Solicitante verificaSolicitante(String cpf, String senha) {

        Solicitante s;

        try {
            EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("FROM Solicitante s WHERE s.cpf = ?1 and s.senha = ?2");
            query.setParameter(1, cpf);
            query.setParameter(2, senha);

            s = (Solicitante) query.getSingleResult();

            em.getTransaction().commit();
            em.close();
            return s;

        } catch (NoResultException nr) {
            return null;
        }

    }
    
    
    public String pegaSaltSolicitante(String cpf){
        
        try {
            EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select s.salt FROM Solicitante s WHERE s.cpf = ?1 ");
            query.setParameter(1, cpf);

            String salt = (String) query.getSingleResult();

            em.getTransaction().commit();
            em.close();
            return salt;

        } catch (NoResultException nr) {
            return null;
        }
    }
    
     
    public String pegaSaltFuncionario(String cpf){
        
        try {
            EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Select f.salt FROM Funcionario f WHERE f.cpf = ?1 ");
            query.setParameter(1, cpf);

            String salt = (String) query.getSingleResult();

            em.getTransaction().commit();
            em.close();
            return salt;

        } catch (NoResultException nr) {
            return null;
        }
    }
    
    

}
