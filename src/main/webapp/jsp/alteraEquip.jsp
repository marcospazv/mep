<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.edu.ceub.mep.entity.Setor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="bo" class="br.edu.ceub.mep.negocio.EquipamentoBO"/>
<jsp:useBean id="controller" class="br.edu.ceub.mep.controller.equipamentoController"/>


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

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Alterar equipamento</h1>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">

                        </div>
                        <div class="card-body">
                            <div class="table-responsive">



                                <form method="POST" action="${pageContext.request.contextPath}/equipamentoController">
                                    <div class="form-row">
                                        <div class="form-group col-md-3">
                                            <label for="inputMar">Equipamento</label>
                                            <select id="inputMar" name="nomeEquipamento" class="form-control">
                                                <c:set var="nome" value="${equipamentoEditando.nomeEquipamento}"/>

                                                <option value="Gabinete" ${nome eq "Gabinete" ? "selected" : "" }>Gabinete</option>

                                                <option value="Mouse" ${nome eq "Mouse" ? "selected" : "" }>Mouse</option>

                                                <option value="Teclado" ${nome eq "Teclado" ? "selected" : "" }>Teclado</option>

                                                <option value="Monitor"  ${nome eq "Monitor" ? "selected" : "" }>Monitor</option>

                                                <option value="Placa-mae" ${nome eq "Placa-mae" ? "selected" : "" }>Placa mãe</option>

                                                <option value="Processador" ${nome eq "Processador" ? "selected" : "" }>Processador</option>

                                                <option value="Fonte" ${nome eq "Fonte" ? "selected" : "" }>Fonte</option>

                                                <option value="Memoria" ${nome eq "Memoria" ? "selected" : "" }>Memória</option>

                                                <option value="Outro" ${nome eq "Outro" ? "selected" : "" }>Outro</option>
                                            </select>
                                        </div>

                                        <div class="form-group col-md-3">
                                            <label for="inputMar">Setor</label>
                                            <select id="inputMar" name="setor" class="form-control">
                                                <option disabled="disabled" selected="selected">Setor do Equipamento</option>
                                                <c:forEach var="setor" items="${bo.listaSetores()}">
                                                    <c:set var="set" value="${equipamentoEditando.setor.setor}"/>
                                                    <c:if test="${setor.setor == set}">
                                                        <option value="${setor.id}" selected>${setor.setor}</option>
                                                    </c:if> 
                                                    <c:if test="${setor.setor ne set}">
                                                        <option value="${setor.id}" >${setor.setor}</option>
                                                    </c:if> 
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <input type="hidden" name="idEquipamento" value="${equipamentoEditando.id}" class="entrar">
                                    <input type="hidden" name="setorNome" value="${setor.setor}" class="entrar">
                                    <button type="submit" name="salvar" class="btn btn-primary" style="margin-bottom: 10px">Salvar</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>                    



                <!-- End of Main Content -->

                <c:import url="copyright.jsp"/>

            </div>
            <!-- End of Content Wrapper -->

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

