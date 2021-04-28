package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.negocio.LotacaoBO;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "lotacaoController", urlPatterns = {"/lotacaoController"})
public class lotacaoController extends HttpServlet {

    private LotacaoBO lotacaoBo = new LotacaoBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msgErro;
        String msgSucesso;

        if (Objects.nonNull(req.getParameter("cadastrar"))) {

            try {
                lotacaoBo.incluirLotacao(req.getParameter("nomeLotacao"));
                msgSucesso = "Lotação incluida com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {

                msgErro = "Erro ao incluir lotação";
                req.setAttribute("msgErro", msgErro);
            }

            req.getRequestDispatcher("jsp/cadastraLot.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultar"))) {

            try {
                req.setAttribute("lotacoes", lotacaoBo.getLotacoes());
            } catch (Exception e) {
                msgErro = "Erro ao consultar lotação";
                req.setAttribute("msgErro", msgErro);
            }
             req.getRequestDispatcher("jsp/consultaLot.jsp").forward(req, resp);
            
        } else if (Objects.nonNull(req.getParameter("excluir"))){
            
            Integer id = Integer.valueOf(req.getParameter("idLotacao"));
            
            try {
                lotacaoBo.excluirLotacao(id);
                msgSucesso = "Lotação excluida com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                msgErro = "Erro ao consultar lotação";
                req.setAttribute("msgErro", msgErro);
            }
            
            req.getRequestDispatcher("jsp/consultaLot.jsp").forward(req, resp);
            
        }
        
        

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String msgErro;
        
        try {
                req.setAttribute("lotacoes", lotacaoBo.getLotacoes());
            } catch (Exception e) {
                msgErro = "Erro ao consultar lotação";
                req.setAttribute("msgErro", msgErro);
            }
             req.getRequestDispatcher("jsp/lotacao.jsp").forward(req, resp);
    }

}
