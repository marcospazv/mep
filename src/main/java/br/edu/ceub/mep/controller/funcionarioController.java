package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.entity.Funcionario;
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

@WebServlet(name = "funcionarioController", urlPatterns = {"/funcionarioController"})
public class funcionarioController extends HttpServlet {

    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private SolicitanteBO solicitanteBO = new SolicitanteBO();
    private LoginBO lbo = new LoginBO();
    private Criptografia c = new Criptografia();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //pegar atributo req.getParameter("nome");
        //criar atributo req.setAtributte("msgSucesso", msgSucesso);
        //enviar atributo req.getRequestDispatcher("jsp/exemplo").forward(req, resp);
        String msgSucesso;
        String msgErro;
        Boolean adm;
        String objetoCrip[] = new String[2];
        String senhaCrip = "";
        String salt = "";

        if (Objects.nonNull(req.getParameter("incluir"))) {
            //Incluindo func

            if (req.getParameter("adm") == null) {
                adm = false;

            } else if (req.getParameter("adm").equals("Administrador")) {
                adm = true;
            } else {
                adm = false;
            }

            try {

                Solicitante s;
                s = funcionarioBO.verificarSolicitante(req.getParameter("cpf"));

                if (s == null) {

                    String senha = req.getParameter("cpf");
                    objetoCrip = c.criptografarSenha(senha);
                    senhaCrip = objetoCrip[0];
                    salt = objetoCrip[1];

                    String cpf = req.getParameter("cpf");

                    funcionarioBO.incluirFuncionario(req.getParameter("nome"),
                            req.getParameter("cpf"),
                            req.getParameter("dtNasc"),
                            adm,
                            senhaCrip,
                            salt,
                            req.getParameter("sexo"));

                    msgSucesso = "Registro de " + req.getParameter("nome") + " incluído com sucesso!";
                    req.setAttribute("msgSucesso", msgSucesso);

                } else {
                    msgErro = "CPF já cadastrado como solicitante";
                    req.setAttribute("msgErro", msgErro);
                }

            } catch (Exception e) {
                msgErro = "Erro ao incluir registro";
                req.setAttribute("msgErro", msgErro);
            }

            req.getRequestDispatcher("jsp/cadastraFunc.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("consultar"))) {
            //tratando consulta
            Object funcionario = null;
            try {
                String cpf = req.getParameter("cpf");
                funcionario = funcionarioBO.getFuncionario(cpf);
                req.setAttribute("func", funcionario);
                req.getRequestDispatcher("jsp/consultaFunc.jsp").forward(req, resp);

            } catch (Exception e) {
                msgErro = "Erro ao consultar registro";
                req.setAttribute("msgErro", msgErro);
                System.err.println("erro ao consultar" + e.getMessage());
                req.getRequestDispatcher("jsp/consultaFunc.jsp").forward(req, resp);
            }

        } else if (Objects.nonNull(req.getParameter("excluir"))) {
            //TRATANDO EXCLUSÃO

            if (funcionarioBO.excluirFuncionario(req.getParameter("cpf"))) {
                msgSucesso = "Registro excluído com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } else {
                msgErro = "Erro ao excluir registro";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/consultaFunc.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("alterar"))) {
            //TRATANDO ALTERAÇÃO
            String cpf = req.getParameter("cpf");

            req.setAttribute("funcionarioEditando", funcionarioBO.getFuncionario(cpf));
            req.getRequestDispatcher("jsp/alteraFunc.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("salvar"))) {
            //TRATANDO SALVAR A ALTERAÇÃO
            String cpf = req.getParameter("cpf");
            String cpfAntigo = req.getParameter("cpfAntigo");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-d");

            try {
                funcionarioBO.alterarFuncionario(cpf,
                        req.getParameter("nome"),
                        LocalDate.parse(req.getParameter("dtNasc"), dtf),
                        cpfAntigo);

                msgSucesso = "Registro alterado com sucesso!";
                req.setAttribute("msgSucesso", msgSucesso);
            } catch (Exception e) {
                msgErro = "Erro ao alterar registro";
                req.setAttribute("msgErro", msgErro);
                System.err.println("erro ao tentar alterar " + e.getMessage());
            }
            req.getRequestDispatcher("jsp/consultaFunc.jsp").forward(req, resp);

        } else if (Objects.nonNull(req.getParameter("alterarSenha"))) {
            //TRATANDO SALVAR A ALTERAÇÃO
            String cpf = req.getParameter("cpf");
            String senha = req.getParameter("senha");
            String senhaNova = req.getParameter("senhaNova");

            try {

                String saltDoBanco = lbo.pegaSaltFuncionario(cpf);
                byte[] saltAtual = c.pegaSalt(saltDoBanco);
                String senhaCriptografada = c.calculaSenha(saltAtual, senha);
                objetoCrip = c.criptografarSenha(senhaNova);
                senhaNova = objetoCrip[0];
                String saltNovo = objetoCrip[1];

                if (funcionarioBO.alterarSenha(cpf, senhaCriptografada, senhaNova, saltNovo) != false) {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object funcionario = null;
        //funcionario = FuncionarioBO.getFuncionario(req.getParameter("cpf"));
        req.setAttribute("func", funcionario);
        req.getRequestDispatcher("jsp/consultaFunc.jsp").forward(req, resp);
    }

}
