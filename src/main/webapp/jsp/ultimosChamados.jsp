<!doctype html>
<html lang="en">
    <head>
        <title>Últimos Chamados</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body style="background-color: black">

        <div class="wrapper d-flex align-items-stretch">
            <nav id="sidebar">
                <div class="custom-menu">
                    <button type="button" id="sidebarCollapse" class="btn btn-primary">
                        <i class="fa fa-bars"></i>
                        <span class="sr-only">Esconder Menu</span>
                    </button>
                </div>
                <div class="p-4 pt-5">
                    <h1><a href="inicio.jsp" class="logo">MENU</a></h1>
                    <ul class="list-unstyled components mb-5">
                        <li>
                            <a href="#">Home</a>
                        </li>
                        <li class="active">

                            <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Abrir Chamado</a>
                            <ul class="collapse list-unstyled" id="homeSubmenu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/jsp/abrirChamado.jsp">Problemas gerais</a>
                                </li>

                            </ul>
                        </li>

                        <li>
                            <a href="${pageContext.request.contextPath}/jsp/ultimosChamados.jsp">últimos Chamados</a>

                            <a href="${pageContext.request.contextPath}/index.html">Sair</a>
                        </li>
                    </ul>

                </div>
            </nav>

            <div id="content" class="p-4 p-md-5 pt-5">


            </div>

            <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/popper.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/main.js"></script>


            <table class="table table-hover table-dark" style="margin-left: 350px ; margin-top: 50px">
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">EQUIPAMENTO</th>
                        <th scope="col">MOTIVO</th>
                        <th scope="col">DATA</th>
                        <th scope="col">STATUS</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="setor" items="">
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
    </body>
</html>