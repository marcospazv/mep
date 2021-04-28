package br.edu.ceub.mep.controller;

import br.edu.ceub.mep.entity.Equipamento;
import br.edu.ceub.mep.entity.Funcionario;
import br.edu.ceub.mep.entity.Manutencao;
import br.edu.ceub.mep.entity.Solicitante;
import br.edu.ceub.mep.entity.StatusChamado;
import br.edu.ceub.mep.negocio.ManutencaoBO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "manutencaoController", urlPatterns = {"/manutencaoController"})
public class manutencaoController extends HttpServlet {
    
    private ManutencaoBO manutencaoBO = new ManutencaoBO();
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String msgErro;
        String msgSucesso;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm:ss");
        Funcionario f = new Funcionario();
        
        
        if (Objects.nonNull(req.getParameter("consultarAtendidas"))) {
            
            ZoneId local = ZoneId.of("America/Sao_Paulo");
            LocalDateTime agora = LocalDateTime.now(local);
            Integer id = Integer.valueOf(req.getParameter("idUsuario"));
            f.setId(id);
            
            try {
               
                List<Manutencao> manutencoes =  manutencaoBO.manutencoesAtendidas(f);
                req.setAttribute("manutencoes", manutencoes);
                
            } catch (Exception e) {
                System.out.println(e.getMessage());
                msgErro = "Erro ao incluir registro";
                req.setAttribute("msgErro", msgErro);
            }
            req.getRequestDispatcher("jsp/manutencaoRealizada.jsp").forward(req, resp);
            
        }
        
        
        
        
        
        
    }

   

}
