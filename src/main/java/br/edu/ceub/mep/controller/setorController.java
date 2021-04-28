package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.negocio.SetorBO;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "setorController", urlPatterns = {"/setorController"})
public class setorController extends HttpServlet {

    private SetorBO setorBO = new SetorBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msgSucesso;
        String msgErro;

        if (Objects.nonNull(req.getParameter("cadastrar"))) {
            //INCLUINDO SETOR
            try {

                setorBO.incluirSetor(req.getParameter("nomeSetor"));
                msgSucesso = "Setor incluido com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);

            } catch (Exception e) {
                msgErro = "Erro ao incluir setor";
                req.setAttribute("msgErro", msgErro);
            }

            req.getRequestDispatcher("jsp/cadastraSet.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultar"))) {
            //PASANDO LISTA DE SETORES

            try {
                req.setAttribute("setores", setorBO.getSetores());
                msgSucesso = "Pronto";
                req.setAttribute("msgSucesso", msgSucesso);

            } catch (Exception e) {
                msgErro = "Erro ao obter lista de alunos " + e.getMessage();
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaSet.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("excluir"))) {
            //TRATANDO EXCLUSAO DE SETORES

            Integer id = Integer.valueOf(req.getParameter("idSetor"));
            try {
                setorBO.excluirSetor(id);
                msgSucesso = "Setor excluido com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);

            } catch (Exception e) {
                msgErro = "Erro ao excluir setor " + e.getMessage();
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaSet.jsp").forward(req, resp);

        }
    }

        @Override
        protected void doGet
        (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String msgErro;

            try {
                req.setAttribute("setores", setorBO.getSetores());
            } catch (Exception e) {
                msgErro = "Erro ao obter lista de alunos " + e.getMessage();
            }
            req.getRequestDispatcher("jsp/consultaSetor.jsp").forward(req, resp);

        }

    }
