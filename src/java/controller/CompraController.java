/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Compra;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CompraDAO;

/**
 *
 * @author winne
 */
@WebServlet(name = "CompraController", urlPatterns = {"/CompraController"})
public class CompraController extends HttpServlet {

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
        CompraDAO compraDAO = new CompraDAO();
        String option = (String) request.getParameter("option");
        int id;
        ArrayList<Compra> myCompras;
        Compra compra = new Compra();
        
        switch (option) {
            case "get":
                myCompras = compraDAO.getAll();
                request.setAttribute("myCompras", myCompras);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/ListaComprasView.jsp");
                show.forward(request, response);
                break;

            case "insert":
                compra.setId(0);
                compra.setQuantidadeCompra(0);
                compra.setDataCompra(new Date(00/00/0000));
                compra.setValorCompra(0f);
                compra.setIdFornecedor(0);
                compra.setIdProduto(0);
                compra.setIdFuncionario(0);

                request.setAttribute("compra", compra);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/FormCompra.jsp");
                insert.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                compra = compraDAO.getById(id);

                if (compra.getId() > 0) {
                    request.setAttribute("compra", compra);
                    RequestDispatcher rs = request.getRequestDispatcher("FormCompra.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erro ao salvar compra!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/SavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":

                id = Integer.parseInt(request.getParameter("id"));
                compraDAO.delete(id);

                myCompras = compraDAO.getAll();
                request.setAttribute("myCompras", myCompras);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/ListaComprasView.jsp");
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
            Compra compra = new Compra();

            compra.setId(Integer.parseInt(request.getParameter("id")));
            compra.setQuantidadeCompra(Integer.parseInt(request.getParameter("quantidade_compra")));
            compra.setDataCompra(new Date(request.getParameter("data_compra")));
            compra.setValorCompra(Float.parseFloat(request.getParameter("valor_compra")));
            compra.setIdFornecedor(Integer.parseInt(request.getParameter("id_fornecedor")));
            compra.setIdProduto(Integer.parseInt(request.getParameter("id_produto")));
            compra.setIdFuncionario(Integer.parseInt(request.getParameter("id_funcionario")));

            CompraDAO compraDAO = new CompraDAO();

            if (compraDAO.put(compra)) {
                message = "Compra salva com sucesso!";
            } else {
                message = "Erro ao salvar a compra!";
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/SavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Compra salva com sucesso!";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/SavingMessage.jsp");
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
