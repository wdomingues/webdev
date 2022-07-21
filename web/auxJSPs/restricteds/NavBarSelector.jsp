<%-- 
    Document   : NavBarSelector
    Created on : 21/07/2022, 05:39:26
    Author     : winne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    if (session != null) {
        String papelExt = (String)session.getAttribute("papelExt");
        if (papelExt != null) {
            switch (papelExt){
                case "Administrador":
%>
                    <%@include file="./NavBarAdministrador.html" %>
<%
                    break;
                case "Vendedor":
%>
                    <%@include file="./NavBarVendedor.html" %>
<%
                    break;
                case "Comprador":
%>
                    <%@include file="./NavBarComprador.html"%>
<%
                    break;
                default:
%>
                    <%@include file="./NavBarNaoLogado.html"%>
<%
            }
        } else {
%>
            <%@include file="./NavBarNaoLogado.html"%>
<%
        } 
    } else {
%>
        <%@include file="./NavBarNaoLogado.html"%>
<%
    }
%>
