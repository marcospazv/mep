<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="usuario" class="br.edu.ceub.mep.javabeans.loginBean" scope="session"/>
<jsp:useBean id="bo" class="br.edu.ceub.mep.negocio.ChamadoBO"/>

<c:set var="f" value="${funcionario}"/>

<c:if test="${f ne null}">
    <jsp:setProperty name="usuario" property="nomeUsuario" value=" ${funcionario.nomeFuncionario}"/>
    <jsp:setProperty name="usuario" property="cpf" value="${funcionario.cpf}"/>
    <jsp:setProperty name="usuario" property="id" value="${funcionario.id}"/>

    <c:if test="${funcionario.adm == true}">
        <jsp:setProperty name="usuario" property="nivelAcesso" value="3"/>  <!-- Nivel de acesso 3 para adm  -->
    </c:if>    

    <c:if test="${funcionario.adm == false}">
        <jsp:setProperty name="usuario" property="nivelAcesso" value="2"/>  <!-- Nivel de acesso 2 para funcionario  -->
    </c:if>  
</c:if>

<c:if test="${solicitante ne null}">
    <jsp:setProperty name="usuario" property="nomeUsuario" value=" ${solicitante.nomeSolicitante} "/>
    <jsp:setProperty name="usuario" property="cpf" value="${solicitante.cpf}"/>
    <jsp:setProperty name="usuario" property="id" value="${solicitante.id}"/>
    <jsp:setProperty name="usuario" property="nivelAcesso" value="1"/>  <!-- Nivel de acesso 1 para solicitante  -->
</c:if>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>MEP</title>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/print.css">


    </head>

    <body id="page-top">



        <c:import url="menu.jsp"/>


        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">



                <c:import url="usuarioInfo.jsp"/>


                <!-- Begin Page Content -->
                <div class="container-fluid">



                    <c:choose>

                        <c:when test="${usuario.nivelAcesso == 2}">

                            <!-- DataTales Example -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <div class="table-responsive">

                                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Código</th>
                                                        <th>Motivo</th>
                                                        <th>Equipamento</th>
                                                        <th>Data</th>
                                                        <th>Solicitante</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="chamado" items="${bo.chamados}">
                                                        <tr>
                                                            <td>${chamado.id}</td>
                                                            <td>${chamado.motivo}</td>
                                                            <td>${chamado.equipamento.nomeEquipamento}</td>
                                                            <td>${chamado.dtChamado}</td>
                                                            <td>${chamado.solicitante.nomeSolicitante}</td>
                                                            <td>
                                                                <span class="badge-danger">${chamado.statusChamado.statusChamado}</span>
                                                            </td>
                                                            <td>
                                                                <form method="POST" action="${pageContext.request.contextPath}/ChamadoController">
                                                                    <input type="hidden" name="idChamado" value="${chamado.id}"/>
                                                                    <input type="hidden" name="idUsuario" value="${usuario.id}"/>
                                                                    <button type="submit" name="atender" class="btn btn-primary">Atender chamado</button> 
                                                                </form>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </c:when>

                        <c:otherwise>
                            <h1 class="h3 mb-0 text-gray-800" style="text-align: center; margin-top: 20%; font-size: 4.0rem">Bem Vindo ${usuario.nomeUsuario}!</h1>
                            <h1 class="h3 mb-0 text-gray-800" style="text-align: center;font-size: 2.0rem">O que deseja fazer hoje?</h1>
                        </c:otherwise>    

                    </c:choose>
                </div>
            </div>

            <c:import url="copyright.jsp"/>
        </div>



        <!-- Bootstrap core JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>



    </body>

</html>