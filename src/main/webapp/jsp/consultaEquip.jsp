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
                        <h1 class="h3 mb-0 text-gray-800">Consultar Equipamento</h1>

                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <div class="table-responsive">
                                    <form method="POST" action="${pageContext.request.contextPath}/equipamentoController">
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <label for="inputNome">Código do equipamento</label>
                                                <input class="form-control" type="number" name="idEquipamento" placeholder="Código do Equipamento"  required>
                                            </div>
                                            <div class="form-group" style="margin-top: 32px; margin-left: 20px">
                                                <button type="submit" name="consultar" class="btn btn-primary">Consultar</button>
                                            </div>
                                        </div>
                                    </form>


                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Código</th>
                                                <th>Tipo</th>
                                                <th>Setor</th>
                                                <th>Funcionário</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                            <tr>
                                                <td>${equipamento.id}</td>
                                                <td>${equipamento.nomeEquipamento}</td>
                                                <td>${equipamento.setor.setor}</td>
                                                <td>${equipamento.funcionario.nomeFuncionario}</td>
                                                <c:set var="status" value="${equipamento.excluido}"/>
                                                <td>${status == false ? "Ativo" : "" }
                                                    ${status == true ? "Excluído" : ""}
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <c:if test="${usuario.nivelAcesso == 3 or usuario.nivelAcesso == 2}">
                                        <c:if test="${equipamento.excluido != true}">
                                            <div class="form-row" style="padding-left: 5px">                           
                                                <!-- ALTERAR -->                           
                                                <form method="POST" action="${pageContext.request.contextPath}/equipamentoController" >
                                                    <input type="hidden" name="idEquipamento" value="${equipamento.id}" >  
                                                    <button type="submit" class="btn btn-primary" name="alterar" style="margin-right: 20px">Alterar</button>
                                                </form>

                                                <!-- EXCLUIR -->      
                                                <button type="submit" class="btn btn-primary" data-toggle="modal" data-backdrop="static"  data-target="#exampleModalCenter">Excluir</button>



                                                <!-- Modal -->
                                                <div class="modal" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                                    <div class="modal-dialog modal-dialog-centered" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLongTitle" style="color: red">Atenção!</h5>
                                                                <button type="button" class="close" data-dissmiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <p>Deseja mesmo excluir o equipamento?</p>
                                                            </div>


                                                            <div class="modal-footer">
                                                                <!--<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>-->
                                                                <button type="button" class="btn btn-secondary"  data-dismiss="modal">Cancelar</button>
                                                                <form method="POST" action="${pageContext.request.contextPath}/equipamentoController">
                                                                    <input type="hidden" name="idEquipamento" value="${equipamento.id}">    
                                                                    <button type="submit" class="btn btn-primary" name="excluir">Excluir</button>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div> 
                                                <!-- Fim Modal -->            
                                            </div>
                                        </c:if>          
                                    </c:if>          
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