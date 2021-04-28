<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
     
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
        <!--===============================================================================================-->
    </head>
    <body>

      
        <form class="box" style="left: 35%; background-color: #4e72df">
            <img src="${pageContext.request.contextPath}/img/logo-mep5-branca.png" style="width: 314px;">
        </form>
        

        <form method="POST" class="box" action="${pageContext.request.contextPath}/loginController">
            <div style="color: red">${msgErro}</div>
            <h1 style="width: 160px;padding-top: 0px;height: 0px;">Usuário:</h1>
            <input type="text" name="cpf" maxlength="11">
            <h2 style="width: 159px;height: 0px;">Senha:</h2>
            <input type="password" name="senha">
            <input type="submit" name="logar" value="Entrar">
        </form>

        

    </body>
</html>