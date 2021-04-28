<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <h1 class="h3 mb-0 text-gray-800">Alterar Funcionário</h1>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">

                        </div>
                        <div class="card-body">
                            <div class="table-responsive">


                                <form method="POST" action="${pageContext.request.contextPath}/funcionarioController">
                                    <div class="form-row">
                                        <div class="form-group col-md-5">
                                            <label for="inputNome">Nome</label>
                                            <input class="form-control" type="text" name="nome" placeholder="Nome" value="${funcionarioEditando.nomeFuncionario}" maxlength="45" required>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="inputCPF">CPF</label>
                                            <input class="form-control" type="text" name="cpf" placeholder="CPF" value="${funcionarioEditando.cpf}" maxlength="11" required>
                                        </div>
                                    </div>    

                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="inputDt">Data de Nascimento</label>
                                            <input class="form-control" type="date" name="dtNasc" placeholder="Data de Nascimento" value="${funcionarioEditando.dtNascimento}" min="1900-01-01" required>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="inputCargo">Sexo</label>
                                            <select name="sexo" class="form-control">
                                                <option disabled="disabled" selected="selected">Sexo</option>
                                                <option>Masculino</option>
                                                <option>Feminino</option>
                                            </select>
                                        </div>
                                    </div>

                                    <input type="hidden" name="cpfAntigo" placeholder="CPF" value="${funcionarioEditando.cpf}" maxlength="11">

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

