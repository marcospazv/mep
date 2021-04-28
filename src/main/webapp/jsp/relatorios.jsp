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


                    <h3>Relatórios Simples</h3>     

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class="table-responsive">
                                    <form method="POST" action="${pageContext.request.contextPath}/relatorioController">
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <label for="inputCargo">Tipo de relatório</label>
                                                <select name="relatorio" class="form-control" required>
                                                    <option value="" disabled="disabled" selected="selected">Tipo</option>
                                                    <option value="1">Funcionarios que mais realizaram manutenções</option>
                                                    <option value="2">Solicitantes que mais fizeram chamados</option>
                                                    <option value="3">Equipamentos que mais deram problemas</option>
                                                    <option value="4">Equipamentos por Setor</option>
                                                    <option value="5">Solicitantes por Lotação</option>
                                                </select>
                                            </div>
                                            <div class="form-group" style="margin-top: 32px; margin-left: 20px">
                                                <button type="submit" name="gerar" class="btn btn-primary">Gerar</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <h3>Relatórios Mensais</h3>                    

                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class="table-responsive">
                                    <form method="POST" action="${pageContext.request.contextPath}/relatorioController">
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <label for="inputCargo">Tipo de relatório</label>
                                                <select name="relatorio" class="form-control" required>
                                                    <option value="" disabled="disabled" selected="selected">Tipo</option>
                                                    <option value="1">Chamados Mensais</option>
                                                    <option value="2">Manutencoes Mensais</option>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="inputCargo">Mês</label>
                                                <select name="mes" class="form-control" required>
                                                    <option value="" disabled="disabled" selected="selected">Tipo</option>
                                                    <option value="1">Janeiro</option>
                                                    <option value="2">Fevereiro</option>
                                                    <option value="3">Março</option>
                                                    <option value="4">Abril</option>
                                                    <option value="5">Maio</option>
                                                    <option value="6">Junho</option>
                                                    <option value="7">Julho</option>
                                                    <option value="8">Agosto</option>
                                                    <option value="9">Setembro</option>
                                                    <option value="10">Outubro</option>
                                                    <option value="11">Novembro</option>
                                                    <option value="12">Dezembro</option>
                                                </select>
                                            </div>
                                            <div class="form-group" style="margin-top: 32px; margin-left: 20px">
                                                <button type="submit" name="gerarMensal" class="btn btn-primary">Gerar</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>


                    <h3>Relatórios Gerais</h3>                    

                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class="table-responsive">
                                    <form method="POST" action="${pageContext.request.contextPath}/relatorioController">
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <label for="inputCargo">Tipo de relatório</label>
                                                <select name="relatorio" class="form-control" required>
                                                    <option value="" disabled="disabled" selected="selected">Tipo</option>
                                                    <option value="1">Chamados</option>
                                                    <option value="2">Manutenções</option>
                                                </select>
                                            </div>
                                            <div class="form-group" style="margin-top: 32px; margin-left: 20px">
                                                <button type="submit" name="gerarGeral" class="btn btn-primary">Gerar</button>
                                            </div>
                                        </div>
                                    </form>
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
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>

    </body>

</html>