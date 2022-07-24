/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Fornecedor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FornecedorDAO;

import static application.Funcionario.Papeis.*;

/**
 *
 * @author winne
 */
@WebServlet(name = "FornecedorController", urlPatterns = {"/FornecedorController"})
public class FornecedorController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Permissions.requireRole(request, COMPRADOR);
        FornecedorDAO fornecedorDAO = new FornecedorDAO();
        String option = request.getParameter("option");
        int id;
        ArrayList<Fornecedor> mySuppliers;
        Fornecedor supplier = new Fornecedor();
        
        switch (option) {
            case "get":
                Permissions.requireRole(request, COMPRADOR);
                mySuppliers = fornecedorDAO.getAll();
                request.setAttribute("mySuppliers", mySuppliers);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/views/ListaFornecedoresView.jsp");
                show.forward(request, response);
                break;

            case "insert":
                Permissions.requireRole(request, COMPRADOR);
                supplier.setId(0);
                supplier.setNome("");
                supplier.setDocumento("");
                supplier.setEndereco("");
                supplier.setBairro("");
                supplier.setCidade("");
                supplier.setUf("");
                supplier.setCep("");
                supplier.setTelefone("");
                supplier.setEmail("");

                request.setAttribute("supplier", supplier);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/forms/FormFornecedor.jsp");
                insert.forward(request, response);
                break;

            case "edit":
                Permissions.requireRole(request, COMPRADOR);
                id = Integer.parseInt(request.getParameter("id"));
                supplier = fornecedorDAO.getById(id);

                if (supplier.getId() > 0) {
                    request.setAttribute("supplier", supplier);
                    RequestDispatcher rs = request.getRequestDispatcher("/forms/FormFornecedor.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erro ao salvar fornecedor!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/FornecedorSavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":
                Permissions.requireRole(request, COMPRADOR);
                id = Integer.parseInt(request.getParameter("id"));
                fornecedorDAO.delete(id);

                mySuppliers = fornecedorDAO.getAll();
                request.setAttribute("mySuppliers", mySuppliers);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/views/ListaFornecedoresView.jsp");
                listAfterDeleting.forward(request, response);
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Permissions.requireRole(request, COMPRADOR);
        request.setCharacterEncoding("UTF-8");
        String message;
        try {
            Fornecedor supplier = new Fornecedor();
            supplier.setId(Integer.parseInt(request.getParameter("id")));
            supplier.setNome(request.getParameter("razao_social"));
            supplier.setDocumento(request.getParameter("cnpj"));
            supplier.setEndereco(request.getParameter("endereco"));
            supplier.setBairro(request.getParameter("bairro"));
            supplier.setCidade(request.getParameter("cidade"));
            supplier.setUf(request.getParameter("uf").substring(0,2));
            supplier.setCep(request.getParameter("cep").replace(".", ""));
            supplier.setTelefone(request.getParameter("telefone"));
            supplier.setEmail(request.getParameter("email"));
            
            FornecedorDAO fornecedorDAO = new FornecedorDAO();

            if (fornecedorDAO.put(supplier)) {
                message = "Fornecedor salvo com sucesso!";
            } else {
                message = "Erro ao salvar fornecedor!";
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/FornecedorSavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao salvar fornecedor!";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/FornecedorSavingMessage.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
