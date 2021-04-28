package br.edu.ceub.mep.negocio;

import br.edu.ceub.mep.javabeans.chamadosPorMes;
import br.edu.ceub.mep.javabeans.chamadosPorSexo;
import br.edu.ceub.mep.javabeans.equipMaisChamado;
import br.edu.ceub.mep.javabeans.equipamentoPorSetor;
import br.edu.ceub.mep.javabeans.funcMaisManutencao;
import br.edu.ceub.mep.javabeans.manutencaoPorMes;
import br.edu.ceub.mep.javabeans.manutencaoPorSexo;
import br.edu.ceub.mep.javabeans.relatorioChamadosMensais;
import br.edu.ceub.mep.javabeans.relatorioEquipamentoChamado;
import br.edu.ceub.mep.javabeans.relatorioFuncionarioManutencoes;
import br.edu.ceub.mep.javabeans.relatorioManutencoesMensais;
import br.edu.ceub.mep.javabeans.relatorioSolicitanteChamado;
import br.edu.ceub.mep.javabeans.solicMaisChamao;
import br.edu.ceub.mep.javabeans.solicitantesPorLotacao;
import br.edu.ceub.mep.util.PersistenceManagerSingleton;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.query.NativeQuery;

public class RelatorioBO {

    public List<relatorioFuncionarioManutencoes> gerarRelatorioMaisManutencoes() {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT f.nome_funcionario AS NomeFuncionario ,COUNT(m.cd_funcionario) AS QtManutencoes"
                + " FROM tb_manutencao m JOIN"
                + " tb_funcionario f ON f.cd_funcionario = m.cd_funcionario "
                + " GROUP BY m.cd_funcionario"
                + " ORDER BY count(m.cd_funcionario) DESC";

        javax.persistence.Query query = em.createNativeQuery(sql, "funcionariosMaisManutencoes");
        List<relatorioFuncionarioManutencoes> funcionarios = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return funcionarios;
    }

    public List<relatorioSolicitanteChamado> gerarRelatorioMaisChamados() {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT s.nome_solicitante AS NomeSolicitante ,COUNT(c.cd_solicitante) AS QtChamados"
                + " FROM tb_chamado c JOIN"
                + " tb_solicitante s ON s.cd_solicitante = c.cd_solicitante"
                + " GROUP BY c.cd_solicitante"
                + " ORDER BY count(c.cd_solicitante) DESC";

        javax.persistence.Query query = em.createNativeQuery(sql, "solicitanteMaisChamados");
        List<relatorioSolicitanteChamado> solicitantes = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return solicitantes;
    }

    public List<relatorioEquipamentoChamado> gerarRelatorioMaisEquipamentos() {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT e.nome_equipamento AS NomeEquipamento ,COUNT(c.cd_equipamento) AS QtChamados"
                + " FROM tb_chamado c JOIN"
                + " tb_equipamento e ON e.cd_equipamento = c.cd_equipamento"
                + " GROUP BY e.nome_equipamento"
                + " ORDER BY count(c.cd_equipamento) DESC";

        javax.persistence.Query query = em.createNativeQuery(sql, "equipamentosMaisChamados");
        List<relatorioEquipamentoChamado> equipamentos = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return equipamentos;
    }

    public List<relatorioChamadosMensais> gerarRelatorioChamadosMensais(Integer mes) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT  c.cd_chamado AS idChamado, c.motivo AS motivo, c.dt_chamado AS dtChamado, sc.status_chamado AS status, "
                + " s.nome_solicitante AS solicitante, f.nome_funcionario AS funcionario, "
                + " e.nome_equipamento AS equipamento, c.cd_equipamento AS cdEquipamento "
                + " FROM tb_chamado c JOIN"
                + " tb_equipamento e ON e.cd_equipamento = c.cd_equipamento"
                + " JOIN tb_status_chamado sc ON sc.cd_status = c.cd_status"
                + " JOIN tb_funcionario f ON f.cd_funcionario = c.cd_funcionario"
                + " JOIN tb_solicitante s ON s.cd_solicitante = c.cd_solicitante"
                + " WHERE MONTH(c.dt_chamado) = "
                + mes
                + " and YEAR(c.dt_chamado) = YEAR(CURRENT_DATE)"
                + " ORDER BY c.dt_chamado";

        javax.persistence.Query query = em.createNativeQuery(sql, "chamadosMensais");
        List<relatorioChamadosMensais> chamados = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return chamados;
    }

    public List<relatorioManutencoesMensais> gerarRelatorioManutencoesMensais(Integer mes) {

        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT M.cd_manutencao AS idManutencao, M.dt_manutencao AS dtManutencao, M.descricao AS descricao , C.cd_chamado AS idChamado, "
                + " C.cd_equipamento AS idEquipamento, C.motivo AS motivo, F.nome_funcionario AS nomeFuncionario "
                + " FROM tb_manutencao M "
                + " JOIN tb_chamado C ON C.cd_chamado = M.cd_chamado"
                + " JOIN tb_funcionario F ON F.cd_funcionario = M.cd_funcionario"
                + " where month(M.dt_manutencao) = "
                + mes
                + " and year(M.dt_manutencao) = year(curdate())"
                + " ORDER BY M.dt_manutencao";

        javax.persistence.Query query = em.createNativeQuery(sql, "ManutencoesMensais");
        List<relatorioManutencoesMensais> manutencoes = query.getResultList();

        em.getTransaction().commit();
        em.close();

        return manutencoes;

    }
    
    public equipMaisChamado gerarEquipamentoMaisChamado(){
        
         EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT E.nome_equipamento AS equipamento FROM tb_chamado C "
                + " INNER JOIN tb_equipamento E ON E.cd_equipamento = C.cd_equipamento"
                + " GROUP BY E.nome_equipamento ORDER BY count(C.cd_equipamento) desc limit 1;";

        javax.persistence.Query query = em.createNativeQuery(sql, "equipMaisChamado");
        equipMaisChamado equipamento = (equipMaisChamado) query.getSingleResult();

        em.getTransaction().commit();
        em.close();

        return equipamento;
    }
    
    
    public solicMaisChamao gerarSolicMaisChamado(){
        
         EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT nome_solicitante AS solicitante FROM tb_chamado C INNER JOIN "
                + " tb_solicitante S ON S.cd_solicitante = C.cd_solicitante"
                + " GROUP BY C.cd_solicitante ORDER BY count(C.cd_solicitante) DESC limit 1 ";

        javax.persistence.Query query = em.createNativeQuery(sql, "solicMaisChamado");
        solicMaisChamao solicitante = (solicMaisChamao) query.getSingleResult();

        em.getTransaction().commit();
        em.close();

        return solicitante;
        
    }
    
    public List<chamadosPorMes> gerarChamadosPorMes(){
        
         
         EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT  monthname(C.dt_chamado) AS mes ,count(C.cd_equipamento) qtdChamado "
                + " FROM tb_chamado C "
                + " WHERE year(C.dt_chamado) = year(curdate()) GROUP BY month(C.dt_chamado) ";

        javax.persistence.Query query = em.createNativeQuery(sql, "chamadosPorMes");
        List<chamadosPorMes> chamados =  query.getResultList();

        em.getTransaction().commit();
        em.close();
        
        return chamados;
    }
    
    
    public List<chamadosPorSexo> gerarChamadosPorSexo(){
        
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "select S.sexo AS sexo, count(C.dt_chamado) AS qtChamado from tb_chamado C  "
                + " INNER JOIN tb_solicitante S ON S.cd_solicitante = C.cd_solicitante group by S.sexo";

        javax.persistence.Query query = em.createNativeQuery(sql, "chamadosPorSexo");
        List<chamadosPorSexo> chamados =  query.getResultList();

        em.getTransaction().commit();
        em.close();
        
        
        return chamados;
    }
    
    
    public funcMaisManutencao gerarFuncMaisManutencao(){
        
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT F.nome_funcionario AS funcionario FROM tb_manutencao M  "
                + " INNER JOIN tb_funcionario F on F.cd_funcionario = M.cd_funcionario" +
                "  GROUP BY M.cd_funcionario ORDER BY count(M.cd_funcionario) DESC LIMIT 1 ";

        javax.persistence.Query query = em.createNativeQuery(sql, "funcMaisManutencoes");
        funcMaisManutencao funcionario = (funcMaisManutencao) query.getSingleResult();

        em.getTransaction().commit();
        em.close();
        
        return funcionario;
    }
    
    
    
    public List<manutencaoPorMes> gerarManutencoesPorMes(){
        
         EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT monthname(M.dt_manutencao) AS mes, count(M.cd_manutencao) AS qtManutencao  "
                + " FROM tb_manutencao M" +
                " WHERE year(M.dt_manutencao) = year(curdate()) group by month(M.dt_manutencao)";

        javax.persistence.Query query = em.createNativeQuery(sql, "manutencaoPorMes");
        List<manutencaoPorMes> manutencoes =  query.getResultList();

        em.getTransaction().commit();
        em.close();
        
        return manutencoes;
    }
    
    
    public List<manutencaoPorSexo> gerarManutencoesPorSexo(){
        
          EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT F.sexo AS sexo, count(M.dt_manutencao) AS qtManutencao FROM tb_manutencao M  "
                + " INNER JOIN tb_funcionario F ON F.cd_funcionario = M.cd_funcionario GROUP BY F.sexo;";

        javax.persistence.Query query = em.createNativeQuery(sql, "manutencaoPorSexo");
        List<manutencaoPorSexo> manutencoes =  query.getResultList();

        em.getTransaction().commit();
        em.close();
        
        
        return manutencoes;
    }
    
    
    public List<equipamentoPorSetor> gerarEquipamentosPorSetor(){
       EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT S.setor AS setor, count(S.setor) AS qtEquipamento FROM tb_equipamento E  "
                + " INNER JOIN tb_setor S ON S.cd_setor = E.cd_setor group by S.setor;";

        javax.persistence.Query query = em.createNativeQuery(sql, "equipamentoPorSetor");
        List<equipamentoPorSetor> setores =  query.getResultList();

        em.getTransaction().commit();
        em.close();
        
        
        return setores; 
    }
    
    public List<solicitantesPorLotacao> gerarSolicitantesPorLotacao(){
        EntityManager em = PersistenceManagerSingleton.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        String sql = "SELECT L.lotacao AS lotacao, count(L.lotacao) AS qtSolicitante FROM "
                + " tb_solicitante S INNER JOIN tb_lotacao L ON L.cd_lotacao = S.cd_lotacao GROUP BY L.lotacao;";

        javax.persistence.Query query = em.createNativeQuery(sql, "solicitantePorLotacao");
        List<solicitantesPorLotacao> lotacoes =  query.getResultList();

        em.getTransaction().commit();
        em.close();
        
        
        return lotacoes; 
    }
    
}
