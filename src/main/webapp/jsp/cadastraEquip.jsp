<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bo" class="br.edu.ceub.mep.negocio.EquipamentoBO"/>


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

            <c:import url="usuarioInfo.jsp"/>

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Cadastro de Equipamento</h1>
                </div

                <!-- Button trigger modal -->
                <button type="button" id="imprimir" class="btn btn-primary" data-toggle="modal" data-backdrop="static"  data-target="#exampleModalCenter" hidden></button>



                <c:if test="${idEquip != null}">

                    <script>
                        window.onload = function () {
                            document.getElementById('imprimir').click();
                        }

                    </script>
                </c:if>

                <!-- Modal -->
                <div class="modal" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle" style="color: green">Equipamento cadastrado com sucesso!</h5>
                                <button type="button" class="close" data-dissmiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>Clique no botão para imprimir o código do equipamento, é sua única chance de imprimir o código</p>
                                <p hidden id="imprime">${idEquip}</p>
                            </div>


                            <div class="modal-footer">
                                <!--<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>-->
                                <button type="button" class="btn btn-secondary"  data-dismiss="modal">Fechar</button>
                                <button type="button" onclick="imprimeCod('imprime')" class="btn btn-primary">Imprimir</button>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <!--<div style="color: green">${msgSucesso}</div>
                        <div style="color: red">${msgErro}</div>-->
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">

                            <form method="POST" action="${pageContext.request.contextPath}/equipamentoController">
                                <div class="form-row">
                                    <div class="form-group col-md-3">
                                        <label for="inputMar">Equipamento</label>
                                        <select id="inputMar" name="nomeEquipamento" class="form-control" required>
                                            <option value="" disabled selected>Nome do equipamento</option>
                                            <option value="Gabinete">Gabinete</option>
                                            <option value="Mouse">Mouse</option>
                                            <option value="Teclado">Teclado</option>
                                            <option value="Monitor">Monitor</option>
                                            <option value="Placa-mae">Placa mãe</option>
                                            <option value="Processador">Processador</option>
                                            <option value="Fonte">Fonte</option>
                                            <option value="Memoria">Memória</option>
                                            <option value="Outro">Outro</option>
                                        </select>
                                    </div>

                                    <div class="form-group col-md-3">
                                        <label for="inputMar">Setor</label>
                                        <select id="inputMar" name="setor" class="form-control" required>
                                             <option value="" disabled selected>Nome do setor</option>
                                            <c:forEach var="setor" items="${bo.listaSetores()}">
                                                <option value="${setor.id}">${setor.setor}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <input type="hidden" name="setorId" value="${setor.id}" class="entrar">
                                <input type="hidden" name="setorNome" value="${setor.setor}" class="entrar">
                                <input type="hidden" name="funcionario" value="${usuario.id}" class="entrar">
                                <button type="submit" name="cadastrar" class="btn btn-primary" style="margin-right: 10px; margin-bottom: 10px">Cadastrar</button>

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

        function imprimeCod(x) {
         
            var restorepage = document.body.innerHTML;
            var printcontent = document.getElementById(x).innerHTML;
            document.body.innerHTML = printcontent;
            window.print();
            document.body.innerHTML = restorepage;
            location.reload();
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
