/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Venda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.VendaDAO;

/**
 *
 * @author winne
 */
@WebServlet(name = "VendaController", urlPatterns = {"/VendaController"})
public class VendaController extends HttpServlet {

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
        VendaDAO vendaDAO = new VendaDAO();
        String option = (String) request.getParameter("option");
        int id;
        ArrayList<Venda> myVendas;
        Venda venda = new Venda();
        
        switch (option) {
            case "get":
                myVendas = vendaDAO.getAll();
                request.setAttribute("myVendas", myVendas);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/ListaVendasView.jsp");
                show.forward(request, response);
                break;

            case "insert":
                venda.setId(0);
                venda.setQuantidadeVenda(0);
                venda.setDataVenda(new Date(00/00/0000));
                venda.setValorVenda(0f);
                venda.setIdCliente(0);
                venda.setIdProduto(0);
                venda.setIdFuncionario(0);

                request.setAttribute("venda", venda);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/FormVenda.jsp");
                insert.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                venda = vendaDAO.getById(id);

                if (venda.getId() > 0) {
                    request.setAttribute("venda", venda);
                    RequestDispatcher rs = request.getRequestDispatcher("FormVenda.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erroao salvar venda!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/SavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":

                id = Integer.parseInt(request.getParameter("id"));
                vendaDAO.delete(id);

                myVendas = vendaDAO.getAll();
                request.setAttribute("myVendas", myVendas);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/ListaVendasView.jsp");
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
            Venda venda = new Venda();

            venda.setId(Integer.parseInt(request.getParameter("id")));
            venda.setQuantidadeVenda(Integer.parseInt(request.getParameter("quantidade_venda")));
            venda.setDataVenda(new Date(request.getParameter("data_venda")));
            venda.setValorVenda(Float.parseFloat(request.getParameter("valor_venda")));
            venda.setIdCliente(Integer.parseInt(request.getParameter("id_cliente")));
            venda.setIdProduto(Integer.parseInt(request.getParameter("id_produto")));
            venda.setIdFuncionario(Integer.parseInt(request.getParameter("id_funcionario")));

            VendaDAO vendaDAO = new VendaDAO();

            if (vendaDAO.put(venda)) {
                message = "Venda salva com sucesso!";
            } else {
                message = "Error while saving Venda!";
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/SavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao salvar Venda!";
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
