package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.entity.Equipamento;
import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Setor;
import br.edu.ceub.mep.negocio.EquipamentoBO;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "equipamentoController", urlPatterns = {"/equipamentoController"})
public class equipamentoController extends HttpServlet {

    private EquipamentoBO equipamentoBo = new EquipamentoBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msgErro;
        String msgSucesso;

        if (Objects.nonNull(req.getParameter("cadastrar"))) {

            String id = req.getParameter("setorid");
            String nome = req.getParameter("nomeEquipamento");
            Setor setor = new Setor();
            setor.setId(Integer.valueOf(req.getParameter("setor")));
            setor.setSetor(req.getParameter("setorNome"));
            Funcionario f = new Funcionario();
            f.setId(Integer.valueOf(req.getParameter("funcionario")));

            try {
                Integer idEquip = equipamentoBo.incluirEquipamento(nome, Boolean.FALSE, setor, f);
                msgSucesso = "Equipamento incluido com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
                req.setAttribute("idEquip", idEquip);
                    req.getRequestDispatcher("jsp/cadastraEquip.jsp").forward(req, resp);

            } catch (Exception e) {
                msgErro = "Erro ao incluir equipamento";
                req.setAttribute("msgErro", msgErro);
            }

            req.getRequestDispatcher("jsp/cadastraEquip.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultar"))) {

            try {
                Integer id = Integer.valueOf(req.getParameter("idEquipamento"));
                Equipamento equipamento = equipamentoBo.getEquipamento(id);
                req.setAttribute("equipamento", equipamento);

                req.getRequestDispatcher("jsp/consultaEquip.jsp").forward(req, resp);

            } catch (Exception e) {
                msgErro = "Erro ao consultar equipamento";
                req.setAttribute("msgErro", msgErro);
                System.err.println("erro ao consultar" + e.getMessage());

            }

            req.getRequestDispatcher("jsp/consultaEquip.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("alterar"))) {

            //passando o equipamento que esta sendo editado
            Integer id = Integer.valueOf(req.getParameter("idEquipamento"));

            req.setAttribute("equipamentoEditando", equipamentoBo.pegaEquipamento(id));
            req.getRequestDispatcher("jsp/alteraEquip.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("salvar"))) {
            Integer id = Integer.valueOf(req.getParameter("idEquipamento"));
            Integer idSetor = Integer.valueOf(req.getParameter("setor"));
            Setor setor = new Setor();
            setor.setId(idSetor);

            try {
                equipamentoBo.alterarEquipamento(id, req.getParameter("nomeEquipamento"), setor);
                msgSucesso = "Registro alterado com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                msgErro = "Erro ao alterar registro";
                req.setAttribute("msgErro", msgErro);
            }

            req.getRequestDispatcher("jsp/consultaEquip.jsp").forward(req, resp);
            
        } else if(Objects.nonNull(req.getParameter("excluir"))){
            Integer id = Integer.valueOf(req.getParameter("idEquipamento"));
            if(equipamentoBo.excluirEquipamento(id)){
                msgSucesso = "Registro excluído com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
                req.getRequestDispatcher("jsp/consultaEquip.jsp").forward(req, resp);
            }else{
                msgErro = "Erro ao excluir registro";
                req.setAttribute("msgErro", msgErro);
                req.getRequestDispatcher("jsp/consultaEquip.jsp").forward(req, resp);
            }
            
        }
        
        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msgErro;

        try {

            req.setAttribute("setores", equipamentoBo.listaSetores());

        } catch (Exception e) {
            msgErro = "erro ao obter setores";
            req.setAttribute("msgErro", msgErro);

        }

        req.getRequestDispatcher("jsp/equipamento.jsp").forward(req, resp);
    }

}
