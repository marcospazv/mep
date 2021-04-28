package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.negocio.LoginBO;
import br.edu.ceub.mep.util.Criptografia;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "loginController", urlPatterns = {"/loginController"})
public class loginController extends HttpServlet {

    private LoginBO loginBO = new LoginBO();
    private Criptografia c = new Criptografia();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msgErro;
        String cpf = req.getParameter("cpf");
        String senha = req.getParameter("senha");
        String senhaCrip = "";

        if (Objects.nonNull(req.getParameter("logar"))) {

            String saltF = loginBO.pegaSaltFuncionario(cpf);
            String saltS = loginBO.pegaSaltSolicitante(cpf);
            try {

                if (saltF != null) {

                    byte[] salt = c.pegaSalt(saltF);
                    senhaCrip = c.calculaSenha(salt, senha);

                } else if (saltS != null) {

                    byte[] salt = c.pegaSalt(saltS);
                    senhaCrip = c.calculaSenha(salt, senha);

                } else if (saltF == null & saltS == null) {
                    msgErro = "Login invalido ou o usuário não existe";
                    req.setAttribute("msgErro", msgErro);
                    req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Login invalido ou o usuário não existe";
                req.setAttribute("msgErro", msgErro);
                req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
            }

            try {

                Solicitante s = new Solicitante();
                Funcionario f = new Funcionario();

                if (saltF != null) {

                    f = loginBO.verificaFuncionario(cpf, senhaCrip);
                    if (f != null) {

                        if (f.getExcluido() == false) {
                            req.setAttribute("funcionario", f);
                            req.getRequestDispatcher("jsp/inicio.jsp").forward(req, resp);
                        } else {
                            msgErro = "Usuário excluido";
                            req.setAttribute("msgErro", msgErro);
                            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
                        }

                    } else {
                        msgErro = "Login invalido ou o usuário não existe";
                        req.setAttribute("msgErro", msgErro);
                        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
                    }

                } else if (saltS != null) {

                    s = loginBO.verificaSolicitante(cpf, senhaCrip);
                    if (s != null) {
                        if (s.getExcluido() == false) {

                            req.setAttribute("solicitante", s);
                            req.getRequestDispatcher("jsp/inicio.jsp").forward(req, resp);
                        } else {
                            msgErro = "Usuário excluido";
                            req.setAttribute("msgErro", msgErro);
                            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
                        }

                    } else {
                        msgErro = "Login invalido ou o usuário não existe";
                        req.setAttribute("msgErro", msgErro);
                        req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
                    }

                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Login invalido ou o usuário não existe";
                req.setAttribute("msgErro", msgErro);
                req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);

            }

        }

    }

}
