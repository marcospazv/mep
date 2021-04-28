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

        <script src="${pageContext.request.contextPath}/vendor/jquery/jquery.min.js"></script>

        <!-- Custom fonts for this template-->
        <link href="${pageContext.request.contextPath}/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="${pageContext.request.contextPath}/css/sb-admin-2.min.css" rel="stylesheet">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>





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
                        <h1 class="h3 mb-0 text-gray-800">Cadastro de Funcionário</h1>
                    </div>





                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <div style="color: green">${msgSucesso}</div>
                            <div style="color: red">${msgErro}</div>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">

                                <form method="POST" action="${pageContext.request.contextPath}/funcionarioController" id="form1" onsubmit="return  validarCampos()">
                                    <div class="form-row">
                                        <div class="form-group col-md-5">
                                            <label for="inputNome">Nome</label>
                                            <input class="form-control" type="text" name="nome" placeholder="Nome" maxlength="45" required>
                                        </div>


                                        <div class="form-group col-md-2">
                                            <label for="inputCPF">CPF</label>
                                            <input class="form-control cpf" type="text" name="cpf" value="" id="cpf" placeholder="Ex: 000.000.000-00" onblur="javascript:validarCampos()"  required>
                                        </div>

                                    </div>    

                                    <div class="form-row">
                                        <div class="form-group col-md-2">
                                            <label for="inputDt">Data de Nascimento</label>
                                            <input class="form-control" type="date"  name="dtNasc" placeholder="Data de Nascimento" min="1900-01-01" id="data" onblur="validarCampos()" required>
                                        </div>

                                        <div class="form-group col-md-2">
                                            <label for="inputSexo">Sexo</label>
                                            <select name="sexo" class="form-control" required>
                                                <option value="" disabled="disabled" selected="selected">Sexo</option>
                                                <option value="M">Masculino</option>
                                                <option value="F">Feminino</option>
                                            </select>
                                        </div>

                                        <c:if test="${usuario.nivelAcesso == 3}">
                                            <div class="form-group col-md-2">
                                                <label for="inputCargo">Tipo de funcionário</label>
                                                <select name="adm" class="form-control" required>
                                                    <option value="" disabled="disabled" selected="selected">Selecione</option>
                                                    <option value="Administrador">Administrador</option>
                                                    <option value="Funcionário">Funcionário</option>
                                                </select>
                                            </div>
                                        </c:if>
                                    </div>



                                    <button type="submit" name="incluir" onclick="return checarSubmit();" class="btn btn-primary" style="margin-right: 10px; margin-bottom: 10px">Cadastrar</button>

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
            $(document).ready(function () {
                $(".cpf").mask("000.000.000-00");
            });

            $('#form1').submit(function () {
                var cpfNovo = $('.cpf').cleanVal();
                $('#cpf').val(cpfNovo);
            });
        </script>


        <script>
            function validarCampos() {
                let cpf = document.getElementById("cpf").value;
                cpf = cpf.replace(/[\s.-]*/igm, "");
                var data = new Date(document.getElementById("data").value);
                var dataAtual = new Date();
                dataAtual.setFullYear(dataAtual.getFullYear() - 10);


                if (cpf === '') {
                    return false;
                }

                // Elimina CPFs invalidos conhecidos	
                if (cpf.length !== 11 ||
                        cpf === "00000000000" ||
                        cpf === "11111111111" ||
                        cpf === "22222222222" ||
                        cpf === "33333333333" ||
                        cpf === "44444444444" ||
                        cpf === "55555555555" ||
                        cpf === "66666666666" ||
                        cpf === "77777777777" ||
                        cpf === "88888888888" ||
                        cpf === "99999999999") {
                    alert("CPF INVÁLIDO");
                    return false;
                }
                // Valida 1o digito	
                add = 0;
                for (i = 0; i < 9; i++)
                    add += parseInt(cpf.charAt(i)) * (10 - i);
                rev = 11 - (add % 11);


                if (rev === 10 || rev === 11)
                    rev = 0;
                if (rev !== parseInt(cpf.charAt(9))) {
                    alert("CPF INVÁLIDO");
                    return false;
                }


                // Valida 2o digito	
                add = 0;
                for (i = 0; i < 10; i ++)
                    add += parseInt(cpf.charAt(i)) * (11 - i);
                rev = 11 - (add % 11);
                if (rev === 10 || rev === 11)
                    rev = 0;
                if (rev !== parseInt(cpf.charAt(10))) {
                    alert("CPF INVÁLIDO");
                    return false;
                }
                
                if (data >= dataAtual){
                    alert("DATA DE NASCIMENTO INVÁLIDA\n\Você deve ter mais de 10 anos de idade");
                    return false;
                }
                return true;
            }
        </script>


        <script>
            function checarSubmit()
            {
                if (!submissionflag) {
                    submissionflag = true;
                    return true;
                } else {
                    return false;
                }
            }
        </script>


        <!-- Bootstrap core JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="${pageContext.request.contextPath}/vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="${pageContext.request.contextPath}/vendor/chart.js/Chart.min.js"></script>





    </body>

</html>
