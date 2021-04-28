package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class FuncionarioBO {

    public void incluirFuncionario(String nome, String cpf, String dtNasc, Boolean adm, String senha, String salt, String sexo) {

        //parse da data do formulario
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-d");

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();

        Funcionario f = new Funcionario(cpf, nome, sexo, LocalDate.parse(dtNasc, dtf), Boolean.FALSE, adm, senha, salt);
        //Funcionario f = new Funcionario(cpf, nome, LocalDate.parse(dtNasc, dtf), Boolean.FALSE, adm, senha, salt);
        em.persist(f);

        em.getTransaction().commit();
        em.close();

    }

    //Consulta de funcionario
    public Object getFuncionario(String cpf) {
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

        Object funcionario;

        em.getTransaction().begin();

        Query query = em.createQuery("FROM Funcionario f WHERE f.cpf = ?1");
        query.setParameter(1, cpf);

        funcionario = query.getSingleResult();

        em.getTransaction().commit();
        em.close();

        return funcionario;
    }

    public Boolean excluirFuncionario(String cpf) {
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        Funcionario funcionario;
        try {
            em.getTransaction().begin();
            Query query = em.createQuery("FROM Funcionario f WHERE f.cpf = ?1");
            query.setParameter(1, cpf);
            funcionario = (Funcionario) query.getSingleResult();
            
            funcionario.setExcluido(Boolean.TRUE);
            em.merge(funcionario);

            em.getTransaction().commit();
            em.close();
            return true;

        } catch (Exception e) {
            System.err.println("Erro ao excluir funcionário de cpf:" + cpf + " : " + e.getMessage());
            return false;
        }

    }

    //passa funcionario que esta sendo editado 
    public Funcionario pegaFuncionario(String cpf) {
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Funcionario funcionario = em.find(Funcionario.class, cpf);

        em.getTransaction().commit();
        em.close();

        return funcionario;
    }

    public void alterarFuncionario(String cpf, String nome, LocalDate dtNasc, String cpfAntigo) {
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Funcionario funcionario;
        
        Query query = em.createQuery("FROM Funcionario f WHERE f.cpf = ?1");
        query.setParameter(1, cpfAntigo);
        
        funcionario = (Funcionario) query.getSingleResult();
        funcionario.setCpf(cpf);
        funcionario.setNomeFuncionario(nome);
        funcionario.setDtNascimento(dtNasc);
        em.merge(funcionario);

        em.getTransaction().commit();
        em.close();
    }
    
    
    public Boolean alterarSenha(String cpf, String senha, String novaSenha, String novoSalt){
        
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Funcionario f;
        
        
            Query query = em.createQuery("FROM Funcionario f WHERE f.cpf = ?1 and f.senha = ?2");
            query.setParameter(1, cpf);
            query.setParameter(2, senha);

            f = (Funcionario) query.getSingleResult();
            f.setSenha(novaSenha);
            f.setSalt(novoSalt);
            em.merge(f);

            em.getTransaction().commit();
            em.close();
            return true;
            
        
    }
    
     public Solicitante verificarSolicitante(String cpf){
         
         try {
 
             EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();

             Solicitante solicitante;

             em.getTransaction().begin();

             Query query = em.createQuery("FROM Solicitante s WHERE s.cpf = ?1");
             query.setParameter(1, cpf);

             solicitante = (Solicitante) query.getSingleResult();

             em.getTransaction().commit();
             em.close();

             return solicitante;
             
         } catch (NoResultException ne) {
             return null;
         }
         
         
     }
    

}
