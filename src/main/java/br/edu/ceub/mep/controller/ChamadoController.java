package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.entity.Chamado;
import br.edu.ceub.mep.entity.Equipamento;
import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.entity.StatusChamado;
import br.edu.ceub.mep.negocio.ChamadoBO;
import br.edu.ceub.mep.negocio.SolicitanteBO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ChamadoController", urlPatterns = {"/ChamadoController"})
public class ChamadoController extends HttpServlet {

    private ChamadoBO chamadoBo = new ChamadoBO();
    private SolicitanteBO solicBo = new SolicitanteBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msgErro;
        String msgSucesso;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm:ss");

        if (Objects.nonNull(req.getParameter("realizar"))) {

            ZoneId local = ZoneId.of("America/Sao_Paulo");
            LocalDateTime agora = LocalDateTime.now(local);
            Equipamento equipamento = new Equipamento();
            equipamento.setId(Integer.valueOf(req.getParameter("idEquipamento")));
            StatusChamado sc = new StatusChamado();
            sc.setId(1); //1- Aberto 2- Em atendimento 3- Atendido 4- Fechado
            Solicitante s = new Solicitante();
            s.setId(Integer.valueOf(req.getParameter("solicitante")));

            try {
                chamadoBo.incluirChamado(req.getParameter("motivo"), agora, equipamento, sc, s);
                msgSucesso = "Registro incluído com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao incluir registro";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/realizarChamado.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultar"))) {

            Integer id = Integer.valueOf(req.getParameter("idUsuario"));

            try {
                List<Chamado> chamados = solicBo.getChamados(id);
                req.setAttribute("chamados", chamados);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao consultar chamados";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaChamado.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("atender"))) {

            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));
            Integer id = Integer.valueOf(req.getParameter("idUsuario"));
            Funcionario f = new Funcionario();
            f.setId(id);
            StatusChamado sc = new StatusChamado();
            sc.setId(2);

            try {
                chamadoBo.atenderChamado(f, idChamado, sc);
                msgSucesso = "Chamando atendido com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao atender chamado";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/inicio.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultarAtendidos"))) {

            Integer id = Integer.valueOf(req.getParameter("idUsuario"));
            Funcionario f = new Funcionario();
            f.setId(id);
            StatusChamado sc = new StatusChamado();
            sc.setId(2);

            try {
                List<Chamado> chamados = chamadoBo.consultaAtendidos(f);
                ArrayList<String> datasFormatadas = new ArrayList<>();
                for (Chamado c : chamados) {
                    datasFormatadas.add(c.getDtChamado().format(dtf));
                }
                req.setAttribute("chamados", chamados);
                req.setAttribute("datasFormatadas", datasFormatadas);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao consultar chamados atendidos";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/chamadosAtendidos.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("manutencao"))) {

            ZoneId local = ZoneId.of("America/Sao_Paulo");
            LocalDateTime agora = LocalDateTime.now(local);
            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));
            Integer id = Integer.valueOf(req.getParameter("idUsuario"));
            Funcionario f = new Funcionario();
            f.setId(id);
            StatusChamado sc = new StatusChamado();
            sc.setId(3);
            Chamado c = new Chamado();
            c.setId(idChamado);
            String descricao = req.getParameter("descricao");

            try {

                chamadoBo.chamadoManutencao(idChamado, sc);
                chamadoBo.realizarManutencao(c, f, agora, descricao);
                msgSucesso = "Manutenção realizada com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao atender chamado";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/chamadosAtendidos.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("fechar"))) {

            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));
            StatusChamado sc = new StatusChamado();
            sc.setId(4);
            Chamado c = new Chamado();
            c.setId(idChamado);

            try {

                chamadoBo.fecharChamado(idChamado, sc);
                msgSucesso = "Chamado fechado com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao fechar chamado";
                req.setAttribute("msgErro", msgErro);
                req.getRequestDispatcher("jsp/consultaChamado.jsp").forward(req, resp);
            }
            req.getRequestDispatcher("jsp/consultaChamado.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("reabrir"))) {

            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));
            StatusChamado sc = new StatusChamado();
            sc.setId(5); //reaberto
            Chamado c = new Chamado();
            c.setId(idChamado);

            try {

                chamadoBo.reabrirChamado(idChamado, sc);
                msgSucesso = "Chamado reaberto com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao reabrir chamado";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaChamado.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultarPorId"))) {

            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));

            try {

                Chamado chamado = chamadoBo.getChamadoById(idChamado);
                String dataFormatada = chamado.getDtChamado().format(dtf);
                req.setAttribute("chamado", chamado);
                req.setAttribute("dataFormatada", dataFormatada);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao consultar chamado";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaChamado2.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("alterar"))) {

            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));

            try {

                Chamado chamado = chamadoBo.getChamadoById(idChamado);
                req.setAttribute("chamado", chamado);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao alterar chamado";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/alteraChamado.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("excluir"))) {

            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));

            try {

                chamadoBo.excluirChamado(idChamado);
                msgSucesso = "Registro excluído com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao excluir chamado";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaChamado.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("salvar"))) {

            Integer idChamado = Integer.valueOf(req.getParameter("idChamado"));

            try {
                ZoneId local = ZoneId.of("America/Sao_Paulo");
                LocalDateTime agora = LocalDateTime.now(local);
                Equipamento equipamento = new Equipamento();
                equipamento.setId(Integer.valueOf(req.getParameter("idEquipamento")));
                
                chamadoBo.salvarChamado(req.getParameter("motivo"), agora, equipamento, idChamado);
                msgSucesso = "Registro alterado com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao consultar chamado";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaChamado.jsp").forward(req, resp);

        } 

    }

}
