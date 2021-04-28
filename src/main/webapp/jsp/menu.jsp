<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>


        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${pageContext.request.contextPath}/jsp/inicio.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">MEP </div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/inicio.jsp">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Inicio</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <c:if test="${usuario.nivelAcesso == 3}"> <!-- 3 = adm -->
                    <div class="sidebar-heading">
                        Relatório
                    </div>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/relatorios.jsp">
                            <span>Relatórios</span></a>
                    </li>
                    <hr class="sidebar-divider">
                </c:if>


                <!-- Heading -->
                <div class="sidebar-heading">
                    Consulta
                </div>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/consultaFunc.jsp">
                        <span>Funcionários Manutenção</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/consultaSolic.jsp">
                        <span>Solicitante</span></a>
                </li>


                <!-- Nav Item - Utilities Collapse Menu -->
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/jsp/consultaEquip.jsp">
                        <span>Equipamentos</span></a>
                </li>

                <c:if test="${usuario.nivelAcesso == 1}"> <!--consulta de chamados feitos -->
                    <!-- Nav Item - Utilities Collapse Menu -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/consultaChamado.jsp">
                            <span>Chamados</span></a>
                    </li>
                </c:if>



                <c:if test="${usuario.nivelAcesso != 1}"> <!--consulta de chamados por id -->
                    <!-- Nav Item - Utilities Collapse Menu -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/consultaChamado2.jsp">
                            <span>Chamados</span></a>
                    </li>
                </c:if>

                <c:if test="${usuario.nivelAcesso == 3}">
                    <!-- Nav Item - Utilities Collapse Menu -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/consultaSet.jsp">
                            <span>Setores</span></a>
                    </li>

                    <!-- Nav Item - Utilities Collapse Menu -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/consultaLot.jsp">
                            <span>Lotações</span></a>
                    </li>
                </c:if>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <c:if test="${usuario.nivelAcesso == 3 or usuario.nivelAcesso == 2}">  <!-- 2 = funcionario  -->
                    <!-- Heading -->
                    <div class="sidebar-heading">
                        Cadastro
                    </div>

                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/cadastraFunc.jsp">
                            <span>Funcionários Manutenção</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/cadastraSolic.jsp">
                            <span>Solicitante</span></a>
                    </li>
                </c:if>


                <c:if test="${usuario.nivelAcesso == 3 or usuario.nivelAcesso == 2}">
                    <!-- Nav Item - equipamento -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/cadastraEquip.jsp">
                            <span>Equipamento</span></a>
                    </li>
                </c:if>

                <c:if test="${usuario.nivelAcesso == 3}"> 
                    <!-- Nav Item - setor -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/cadastraSet.jsp">
                            <span>Setor</span></a>
                    </li>

                    <!-- Nav Item - lotacao -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/cadastraLot.jsp">
                            <span>Lotação</span></a>
                    </li>
                </c:if>



                <c:if test="${usuario.nivelAcesso != 3}">

                    <hr class="sidebar-divider">
                    <!-- Heading -->
                    <div class="sidebar-heading">
                        Chamado
                    </div>

                </c:if>   

                <c:if test="${usuario.nivelAcesso == 1}">
                    <!-- Nav Item - manuteção -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/realizarChamado.jsp">
                            <span>Realizar chamado</span></a>
                    </li>
                </c:if>




                <c:if test="${usuario.nivelAcesso == 2}">
                    <!-- Nav Item - manuteção -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/chamadosAtendidos.jsp">
                            <span>Chamados atendidos</span></a>
                    </li>

                </c:if> 


                <c:if test="${usuario.nivelAcesso == 2}">
                    <hr class="sidebar-divider">
                    <!-- Heading -->
                    <div class="sidebar-heading">
                        Manutenção
                    </div>

                    <!-- Nav Item - manuteção -->
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/jsp/manutencaoRealizada.jsp">
                            <span>Manutenções realizadas</span></a>
                    </li>

                </c:if> 


                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>



            </ul>
            <!-- End of Sidebar -->

    </body>
</html>
