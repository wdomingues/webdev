<%-- 
    Document   : FornecedorSavingMessage
    Created on : 14/07/2022, 16:49:37
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="Header.html" %>
    </head>
    <body>
        <div class="container mt-2">

            <jsp:include page="NavBar.html" />

            <div class="col-8 mt-5">

                <div class="alert alert-success" role="alert">
                    <h5>
                        <%= request.getAttribute("message") %>
                    </h5>
                </div>

                <p></p>
                <div><a href="FornecedorController?option=get">Voltar</a></div>
            </div>
        </div>

        <%@include file="BasicScripts.html" %>
    </body>
</html>