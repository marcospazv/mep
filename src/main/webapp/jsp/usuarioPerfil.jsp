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
            rel="${pageContext.request.contextPath}/stylesheet">

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
                        <h1 class="h3 mb-0 text-gray-800" style="margin-top: 50px" >Olá ${usuario.nomeUsuario}</h1>
                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            Perfil
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <h5>Login: ${usuario.cpf}</h2>


                            </div>        
                        </div>


                    </div>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            Alterar Senha
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <c:if test="${usuario.nivelAcesso == 2 or usuario.nivelAcesso == 3}">
                                    <form method="POST" action="${pageContext.request.contextPath}/funcionarioController" onsubmit="return validaSenha();">
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <label for="inputNome">Senha atual</label>
                                                <input class="form-control" type="password" name="senha"  maxlength="30" required>
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="inputNome">Nova senha</label>
                                                <input class="form-control" id="senhaNova" type="password" name="senhaNova"  minlength="6" maxlength="30" required>
                                            </div>
                                            <div class="form-group col-md-3">
                                                <label for="inputNome">Digite a nova senha novamente</label>
                                                <input class="form-control" id="senhaNovaRepetida" type="password" name="senhaNova" onblur="javascript:validaSenha()" minlength="6" maxlength="30" required>
                                            </div>
                                            <div class="form-group" style="margin-top: 32px; margin-left: 20px">
                                                <button type="submit" name="alterarSenha" class="btn btn-primary">Alterar</button>
                                                <input class="form-control" type="hidden" name="cpf"  value="${usuario.cpf}">
                                            </div>
                                        </div>
                                    </form>
                                </c:if>   

                                <c:if test="${usuario.nivelAcesso == 1}">
                                    <form method="POST" action="${pageContext.request.contextPath}/SolicitanteController">
                                        <div class="form-row">
                                            <div class="form-group col-md-2">
                                                <label for="inputNome">Senha atual</label>
                                                <input class="form-control" type="password" name="senha"  maxlength="30" required>
                                            </div>
                                            <div class="form-group col-md-2">
                                                <label for="inputNome">Nova senha</label>
                                                <input class="form-control" type="password" name="senhaNova"  maxlength="30" required>
                                            </div>
                                            <div class="form-group" style="margin-top: 32px; margin-left: 20px">
                                                <button type="submit" name="alterarSenha" class="btn btn-primary">Alterar</button>
                                                 <input class="form-control" type="hidden" name="cpf"  value="${usuario.cpf}">
                                            </div>
                                        </div>
                                    </form>
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


    <script>
        function validaSenha(){
            
            var senhaNova = document.getElementById("senhaNova").value;
            var senhaNovaRepetida = document.getElementById("senhaNovaRepetida").value;
            
            if(senhaNova !== senhaNovaRepetida){
                alert("AS SENHAS DEVEM SER IGUAIS");
                return false;
            }else{
                return true;
            }
            
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
