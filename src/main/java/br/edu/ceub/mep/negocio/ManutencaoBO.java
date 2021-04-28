package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Manutencao;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ManutencaoBO {

    public List<Manutencao> manutencoesAtendidas(Funcionario f) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        List<Manutencao> manutencoes;

        Query query = em.createQuery("FROM Manutencao m WHERE m.funcionario = ?1");
        query.setParameter(1, f);
        query.setMaxResults(10);

        manutencoes = query.getResultList();

        em.getTransaction().commit();
        em.close();
        return manutencoes;

    }

}
