package br.edu.ceub.mep.controller;

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
import br.edu.ceub.mep.negocio.RelatorioBO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@WebServlet(name = "relatorioController", urlPatterns = {"/relatorioController"})
public class relatorioController extends HttpServlet {

    private RelatorioBO bo = new RelatorioBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msgErro;

        if (Objects.nonNull(req.getParameter("gerar"))) {

            if (req.getParameter("relatorio").equals("1")) {

                try {
                    List<relatorioFuncionarioManutencoes> funcionarios = bo.gerarRelatorioMaisManutencoes();

                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioMaisManutencoes.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(funcionarios));
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (JRException r) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(r.getMessage());
                }
                req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);

            } else if (req.getParameter("relatorio").equals("2")) {

                try {
                    List<relatorioSolicitanteChamado> solicitantes = bo.gerarRelatorioMaisChamados();

                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioSolicitanteChamados.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(solicitantes));
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (Exception e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }

                req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);

            } else if (req.getParameter("relatorio").equals("3")) {

                try {
                    List<relatorioEquipamentoChamado> equipamentos = bo.gerarRelatorioMaisEquipamentos();

                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioEquipamentoChamado.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(equipamentos));
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (Exception e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }

                req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);
                
            }  else if (req.getParameter("relatorio").equals("4")) {

                try {

                    List<equipamentoPorSetor> setores = bo.gerarEquipamentosPorSetor();
                    
                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioEquipamentoPorSetor.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(setores));
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (Exception e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }

                req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);
                
            } else if (req.getParameter("relatorio").equals("5")) {

                try {

                    List<solicitantesPorLotacao> lotacoes = bo.gerarSolicitantesPorLotacao();
                    
                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioSolicitantePorLotacao.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(lotacoes));
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (Exception e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }

                req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);
            }

            // Relatorios mensais    
        } else if (Objects.nonNull(req.getParameter("gerarMensal"))) {

            Integer mes = Integer.valueOf(req.getParameter("mes"));

            if (req.getParameter("relatorio").equals("1")) {

                try {

                    List<relatorioChamadosMensais> chamados = bo.gerarRelatorioChamadosMensais(mes);

                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioChamadosMensais.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(chamados));
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (Exception e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }
                req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);
            } else if (req.getParameter("relatorio").equals("2")) {

                try {

                    List<relatorioManutencoesMensais> manutencoes = bo.gerarRelatorioManutencoesMensais(mes);

                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioManutencoesMensais.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JRBeanCollectionDataSource(manutencoes));
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (Exception e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }
                req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);
            }

        } else if (Objects.nonNull(req.getParameter("gerarGeral"))) {

            if (req.getParameter("relatorio").equals("1")) {

                try {

                    equipMaisChamado equipamento = bo.gerarEquipamentoMaisChamado();
                    solicMaisChamao solicitante = bo.gerarSolicMaisChamado();
                    List<chamadosPorMes> chamadosMes = bo.gerarChamadosPorMes();
                    List<chamadosPorSexo> chamadosSexo = bo.gerarChamadosPorSexo();

                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioChamadosGeral.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);
                    String solic = solicitante.getSolicitante();
                    String equip = equipamento.getEquipamento();

                    for (int i = 0; i < chamadosMes.size(); i++) {

                        switch (chamadosMes.get(i).getMes()) {
                            case "January":
                                chamadosMes.get(i).setMes("Janeiro");
                                break;

                            case "February":
                                chamadosMes.get(i).setMes("Fevereiro");
                                break;
                            case "March":
                                chamadosMes.get(i).setMes("Março");
                                break;
                            case "April":
                                chamadosMes.get(i).setMes("Abril");
                                break;
                            case "May":
                                chamadosMes.get(i).setMes("Maio");
                                break;
                            case "June":
                                chamadosMes.get(i).setMes("Junho");
                                break;
                            case "July":
                                chamadosMes.get(i).setMes("Julho");
                                break;
                            case "August":
                                chamadosMes.get(i).setMes("Agosto");
                                break;
                            case "September":
                                chamadosMes.get(i).setMes("Setembro");
                                break;
                            case "October":
                                chamadosMes.get(i).setMes("Outubro");
                                break;
                            case "November":
                                chamadosMes.get(i).setMes("Novembro");
                                break;
                            case "December":
                                chamadosMes.get(i).setMes("Dezembro");
                                break;
                        }
                    }

                    JRBeanCollectionDataSource chamadosM = new JRBeanCollectionDataSource(chamadosMes);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);

                    //passando dados para as tabelas
                    map.put("solicitante", solic);
                    map.put("mesLista", chamadosM);
                    map.put("equipamento", equip);

                    //passando dados para o chart
                    String masculino = "Masculino";
                    String feminino = "Feminino";
                    map.put("masculino", masculino);
                    map.put("feminino", feminino);
                    Integer femValor = chamadosSexo.get(0).getQtChamado();
                    Integer mascValor = chamadosSexo.get(1).getQtChamado();
                    map.put("femininoValor", femValor);
                    map.put("masculinoValor", mascValor);
                    JasperPrint print = JasperFillManager.fillReport(report, map, new JREmptyDataSource());
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (JRException e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }

            } else if (req.getParameter("relatorio").equals("2")) {

                try {

                    funcMaisManutencao funcionario = bo.gerarFuncMaisManutencao();
                    List<manutencaoPorMes> manutencoesMes = bo.gerarManutencoesPorMes();
                    List<manutencaoPorSexo> manutencoesSexo = bo.gerarManutencoesPorSexo();

                    String fonte = getServletContext().getRealPath("/WEB-INF/report/RelatorioManutencaoGeral.jrxml");
                    JasperReport report = JasperCompileManager.compileReport(fonte);

                    String func = funcionario.getFuncionario();

                    for (int i = 0; i < manutencoesMes.size(); i++) {

                        switch (manutencoesMes.get(i).getMes()) {

                            case "January":
                                manutencoesMes.get(i).setMes("Janeiro");
                                break;
                            case "February":
                                manutencoesMes.get(i).setMes("Fevereiro");
                                break;
                            case "March":
                                manutencoesMes.get(i).setMes("Março");
                                break;
                            case "April":
                                manutencoesMes.get(i).setMes("Abril");
                                break;
                            case "May":
                                manutencoesMes.get(i).setMes("Maio");
                                break;
                            case "June":
                                manutencoesMes.get(i).setMes("Junho");
                                break;
                            case "July":
                                manutencoesMes.get(i).setMes("Julho");
                                break;
                            case "August":
                                manutencoesMes.get(i).setMes("Agosto");
                                break;
                            case "September":
                                manutencoesMes.get(i).setMes("Setembro");
                                break;
                            case "October":
                                manutencoesMes.get(i).setMes("Outubro");
                                break;
                            case "November":
                                manutencoesMes.get(i).setMes("Novembro");
                                break;
                            case "December":
                                manutencoesMes.get(i).setMes("Dezembro");
                                break;
                        }
                    }

                    JRBeanCollectionDataSource manutencaoM = new JRBeanCollectionDataSource(manutencoesMes);

                    InputStream img = getClass().getResourceAsStream("/img/logo-mep3.png");
                    HashMap map = new HashMap();
                    map.put("logo", img);
                    
                    map.put("funcionario", func);
                    map.put("mesLista", manutencaoM);
                    
                    
                    
                       //passando dados para o chart
                    String masculino = "Masculino";
                    String feminino = "Feminino";
                    map.put("masculino", masculino);
                    map.put("feminino", feminino);
                    Integer femValor = manutencoesSexo.get(0).getQtManutencao();
                    Integer mascValor = manutencoesSexo.get(1).getQtManutencao();
                    map.put("femininoValor", femValor);
                    map.put("masculinoValor", mascValor);

                    JasperPrint print = JasperFillManager.fillReport(report, map, new JREmptyDataSource());
                    JasperExportManager.exportReportToPdfStream(print, resp.getOutputStream());

                } catch (JRException e) {
                    msgErro = "Erro ao gerar chamado";
                    req.setAttribute("msgErro", msgErro);
                    System.out.println(e.getMessage());
                }

            }
            req.getRequestDispatcher("jsp/relatorios.jsp").forward(req, resp);

        }

    }

}
