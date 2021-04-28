package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Lotacao;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.negocio.FuncionarioBO;
import br.edu.ceub.mep.negocio.LoginBO;
import br.edu.ceub.mep.negocio.SolicitanteBO;
import br.edu.ceub.mep.util.Criptografia;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SolicitanteController", urlPatterns = {"/SolicitanteController"})
public class SolicitanteController extends HttpServlet {

    private SolicitanteBO solicitanteBO = new SolicitanteBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private LoginBO lbo = new LoginBO();
    private Criptografia c = new Criptografia();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String msgErro;
        String msgSucesso;
        String objetoCrip[] = new String[2];
        String senhaCrip = "";
        String salt = "";

        if (Objects.nonNull(req.getParameter("incluir"))) {

            Integer idLotacao = Integer.valueOf(req.getParameter("lotacao"));
            Lotacao lotacao = new Lotacao();
            lotacao.setId(idLotacao);

            try {
                Funcionario f = new Funcionario();
                f = solicitanteBO.verificarFuncionario(req.getParameter("cpf"));

                if (f == null) {

                    String senha = req.getParameter("cpf");
                    objetoCrip = c.criptografarSenha(senha);
                    senhaCrip = objetoCrip[0];
                    salt = objetoCrip[1];

                    solicitanteBO.incluirSolicitante(
                            req.getParameter("cpf"),
                            req.getParameter("nomeSolicitante"),
                            LocalDate.parse(req.getParameter("dtNasc"), dtf),
                            Boolean.FALSE,
                            lotacao,
                            senhaCrip,
                            salt,
                            req.getParameter("sexo"));

                    msgSucesso = "Registro de " + req.getParameter("nomeSolicitante") + " incluído com sucesso!";
                    req.setAttribute("msgSucesso", msgSucesso);
                } else {
                    msgErro = "CPF já cadastrado como funcionario";
                    req.setAttribute("msgErro", msgErro);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage().toString());
                msgErro = "Erro ao incluir registro";
                req.setAttribute("msgErro", msgErro);
            }

            req.getRequestDispatcher("jsp/cadastraSolic.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultar"))) {

            Solicitante solicitante = new Solicitante();

            try {
                String cpf = req.getParameter("cpf");
                solicitante = solicitanteBO.getSolicitante(cpf);
                req.setAttribute("solic", solicitante);
            } catch (Exception e) {
                msgErro = "Erro ao consultar registro";
                req.setAttribute("msgErro", msgErro);
                System.err.println("erro ao consultar" + e.getMessage());
            }
            req.getRequestDispatcher("jsp/consultaSolic.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("alterar"))) {

            String cpf = req.getParameter("cpf");
            req.setAttribute("solicitanteEditando", solicitanteBO.getSolicitante(cpf));
            req.getRequestDispatcher("jsp/alteraSolic.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("excluir"))) {

            if (solicitanteBO.excluirSolicitante(req.getParameter("cpf"))) {
                msgSucesso = "Registro excluído com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
                req.getRequestDispatcher("jsp/consultaSolic.jsp").forward(req, resp);
            } else {
                msgErro = "Erro ao excluir registro";
                req.setAttribute("msgErro", msgErro);
                req.getRequestDispatcher("jsp/consultaSolic.jsp").forward(req, resp);
            }

        } else if (Objects.nonNull(req.getParameter("salvar"))) {

            Lotacao lotacao = new Lotacao();
            Integer idLotacao = Integer.valueOf(req.getParameter("lotacao"));
            lotacao.setId(idLotacao);

            try {
                solicitanteBO.alterarSolicitante(
                        req.getParameter("cpf"),
                        req.getParameter("nomeSolicitante"),
                        LocalDate.parse(req.getParameter("dtNasc"), dtf),
                        req.getParameter("cpfAntigo"),
                        lotacao);
                msgSucesso = "Registro alterado com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                msgErro = "Erro ao alterar registro";
                req.setAttribute("msgErro", msgErro);
                System.err.println("erro ao tentar alterar " + e.getMessage());
            }
            req.getRequestDispatcher("jsp/consultaSolic.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("alterarSenha"))) {
            //TRATANDO SALVAR A ALTERAÇÃO
            String cpf = req.getParameter("cpf");
            String senha = req.getParameter("senha");
            String senhaNova = req.getParameter("senhaNova");

            try {

                String saltDoBanco = lbo.pegaSaltSolicitante(cpf);
                byte[] saltAtual = c.pegaSalt(saltDoBanco);
                String senhaCriptografada = c.calculaSenha(saltAtual, senha);
                objetoCrip = c.criptografarSenha(senhaNova);
                senhaNova = objetoCrip[0];
                String saltNovo = objetoCrip[1];
                
                if (solicitanteBO.alterarSenha(cpf, senhaCriptografada, senhaNova, saltNovo) != false) {
                    msgSucesso = "Senha alterada com sucesso!";
                    req.setAttribute("msgSucesso", msgSucesso);
                    req.getRequestDispatcher("jsp/usuarioPerfil.jsp").forward(req, resp);

                }

            } catch (Exception e) {
                msgErro = "Senha incorreta ou usuário inválido";
                req.setAttribute("msgErro", msgErro);
                req.getRequestDispatcher("jsp/usuarioPerfil.jsp").forward(req, resp);
                System.err.println("erro ao tentar alterar senha " + e.getMessage());
            }
        }
    }
}
