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
                        <h1 class="h3 mb-0 text-gray-800">Cadastro de Lotação</h1>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

                                <form method="POST" action="${pageContext.request.contextPath}/lotacaoController">
                                    <div class="form-row">
                                        <div class="form-group col-md-3">
                                            <label for="inputMar">Nome da Lotação</label>
                                            <input class="form-control" type="text" name="nomeLotacao" placeholder="Nome da Lotação" maxlength="45" required>
                                        </div>
                                    </div>
                                    <button type="submit" name="cadastrar" onclick="submitDisable(this)" class="btn btn-primary" style="margin-right: 10px; margin-bottom: 10px">Cadastrar</button>

                                    <a class="btn btn-primary" href="${pageContext.request.contextPath}/jsp/inicio.jsp" role="button" style="margin-bottom: 10px">Cancelar</a>
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


        <script>
            var submitted = false;
            function submitDisable(button) {
                if (!submitted) {
                    submitted = true;
                    button.value = 'Aguarde';
                    button.disable = true;
                    document.forms["summary"].submit();
                } else {
                    alert("Aguarde!");
                }
                return true;
            }
        </script>

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
