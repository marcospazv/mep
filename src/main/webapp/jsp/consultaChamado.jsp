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

        <script src="vendor/jquery/jquery.min.js"></script>
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
                        <h1 class="h3 mb-0 text-gray-800">Chamados realizados</h1>

                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body" style="height: auto">
                            <div class="table-responsive" style="height: 500px; overflow-y: scroll">

                                <form method="POST" action="${pageContext.request.contextPath}/ChamadoController">
                                    <div class="form-row" >
                                        <div class="form-group col-md-3">
                                            <div class="form-group" style="margin-top: 20px;">
                                                <button type="submit" name="consultar" class="btn btn-primary">Buscar</button>
                                            </div>
                                            <input type="hidden" name="idUsuario" value="${usuario.id}"/> 
                                        </div>
                                    </div>
                                </form>


                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Código</th>
                                            <th>Motivo</th>
                                            <th>Equipamento</th>
                                            <th>Data</th>
                                            <th>Funcionário</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="chamado" items="${chamados}">


                                            <tr class="breakrow">
                                                <c:choose>
                                                    <c:when test="${chamado.statusChamado.id == 3}">
                                                        <td><button type="submit" class="btn btn-primary" id="butao">+</button></td>
                                                    </c:when>

                                                    <c:when test="${chamado.statusChamado.id == 1}">
                                                        <td><button type="submit" class="btn btn-primary" id="butao">+</button></td>
                                                    </c:when> 

                                                    <c:otherwise>
                                                        <td></td>
                                                    </c:otherwise>    
                                                </c:choose>
                                                <td>${chamado.id}</td>
                                                <td>${chamado.motivo}</td>
                                                <td>${chamado.equipamento.nomeEquipamento}</td>
                                                <td>${chamado.dtChamado}</td>
                                                <td>${chamado.funcionario.nomeFuncionario}</td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${chamado.statusChamado.statusChamado eq 'Atendido'}">
                                                            <span class="badge-success">
                                                                ${chamado.statusChamado.statusChamado}
                                                            </span>
                                                        </c:when>
                                                        <c:when test="${chamado.statusChamado.statusChamado eq 'Em atendimento'}">
                                                            <span class="badge-warning">
                                                                ${chamado.statusChamado.statusChamado}
                                                            </span>
                                                        </c:when>
                                                        <c:when test="${chamado.statusChamado.statusChamado eq 'Aberto'}">
                                                            <span class="badge-danger">
                                                                ${chamado.statusChamado.statusChamado}
                                                            </span>
                                                        </c:when>
                                                        <c:when test="${chamado.statusChamado.statusChamado eq 'Fechado'}">
                                                            <span class="badge-dark">
                                                                ${chamado.statusChamado.statusChamado}
                                                            </span>
                                                        </c:when>
                                                        <c:when test="${chamado.statusChamado.statusChamado eq 'Reaberto'}">
                                                            <span class="badge-danger">
                                                                ${chamado.statusChamado.statusChamado}
                                                            </span>
                                                        </c:when>
                                                    </c:choose>
                                                </td>
                                            </tr>

                                            <c:if test="${chamado.statusChamado.id == 3}">
                                                <tr class="datarow" style="display: none">
                                                    <td colspan="12">
                                                        <form method="POST" action="${pageContext.request.contextPath}/ChamadoController">
                                                            <div class="form-row">
                                                                <div class="form-group col-md-3">   
                                                                    <input type="hidden" name="idChamado" value="${chamado.id}"/> 
                                                                    <button type="submit" name="reabrir" class="btn btn-primary">Reabrir chamado</button>
                                                                    <button type="submit" name="fechar" class="btn btn-primary" style="margin-left: 10px">Fechar</button>
                                                                </div>
                                                            </div>        
                                                        </form>
                                                    </td>
                                                </tr>
                                            </c:if>

                                            <c:if test="${chamado.statusChamado.id == 1}">
                                                <tr class="datarow" style="display: none">
                                                    <td colspan="12">
                                                        <form method="POST" action="${pageContext.request.contextPath}/ChamadoController">
                                                            <div class="form-row">
                                                                <div class="form-group col-md-3">   
                                                                    <input type="hidden" name="idChamado" value="${chamado.id}"/> 
                                                                    <button type="submit" name="alterar" class="btn btn-primary">Alterar chamado</button>
                                                                    <button type="submit" name="excluir" class="btn btn-primary" style="margin-left: 10px">Excluir</button>
                                                                </div>
                                                            </div>        
                                                        </form>     
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </tbody>
                                </table>

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

</html>