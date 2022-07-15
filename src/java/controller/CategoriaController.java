/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Categoria;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CategoriaDAO;

/**
 *
 * @author winne
 */
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        String option = (String) request.getParameter("option");
        int id;
        ArrayList<Categoria> myCategories;
        Categoria category = new Categoria();
        
        switch (option) {
            case "get":
                myCategories = categoriaDAO.getAll();
                request.setAttribute("myCategories", myCategories);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/views/ListaCategoriasView.jsp");
                show.forward(request, response);
                break;

            case "insert":
                category.setId(0);
                category.setNomeCategoria("");

                request.setAttribute("category", category);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/forms/FormCategoria.jsp");
                insert.forward(request, response);
                break;

            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                category = categoriaDAO.getById(id);

                if (category.getId() > 0) {
                    request.setAttribute("category", category);
                    RequestDispatcher rs = request.getRequestDispatcher("/forms/FormCategoria.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erro ao salvar categoria!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/CategoriaSavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":

                id = Integer.parseInt(request.getParameter("id"));
                categoriaDAO.delete(id);

                myCategories = categoriaDAO.getAll();
                request.setAttribute("myCategories", myCategories);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/views/ListaCategoriasView.jsp");
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
        request.setCharacterEncoding("UTF-8");
        String message;
        try {
            Categoria category = new Categoria();
            category.setId(Integer.parseInt(request.getParameter("id")));
            category.setNomeCategoria(request.getParameter("nome_categoria"));
            
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            if (categoriaDAO.put(category)) {
                message = "Categoria salvo com sucesso!";
            } else {
                message = "Erro ao salvar categoria!";
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/CategoriaSavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao salvar categoria!";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/CategoriaSavingMessage.jsp");
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
