<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>MEP</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">


        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap-grid.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap-reboot.css" rel="stylesheet">

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
                        <h1 class="h3 mb-0 text-gray-800">Últimas manutenções realizadas</h1>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

                                <div class="form-row">

                                    <form method="POST" action="${pageContext.request.contextPath}/manutencaoController">
                                        <div class="form-row">
                                            <div class="form-group col-md-3">
                                                <div class="form-group" style="margin-top: 20px; margin-left: 5px">
                                                    <button type="submit" name="consultarAtendidas" class="btn btn-primary">Buscar</button>
                                                </div>
                                                <input type="hidden" name="idUsuario" value="${usuario.id}"/> 
                                            </div>
                                        </div>
                                    </form>

                                    <table class="table table-bordered"  width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th colspan="1">Código</th>
                                                <th colspan="2">Data</th>
                                                <th colspan="2">Descrição</th>
                                                <th colspan="2">Chamado</th>
                                                <th colspan="2">Funcionário</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="manutencao" items="${manutencoes}">
                                                <tr>
                                                    <td colspan="1">${manutencao.id}</td>
                                                    <td colspan="2">${manutencao.dtManutencao}</td>
                                                    <td colspan="2">${manutencao.descricao}</td>
                                                    <td colspan="2">${manutencao.chamado.id}</td>
                                                    <td colspan="2">${manutencao.funcionario.nomeFuncionario}</td>
                                                </tr>

                                            </c:forEach>
                                        </tbody>
                                    </table>

                                </div>

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
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="${pageContext.request.contextPath}/js/demo/chart-area-demo.js"></script>
        <script src="${pageContext.request.contextPath}/js/demo/chart-pie-demo.js"></script>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <script>
            $(document).ready(function () {

                //collapse and expand sections

                $('.breakrow').click(function () {
                //$('#tableMain').on('click', 'tr.breakrow', function () {

                    $(this).nextUntil('tr.breakrow').slideToggle(200);
                });
            });
        </script>


    </body>

</html>
