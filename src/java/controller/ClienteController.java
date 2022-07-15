/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Cliente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ClienteDAO;

/**
 *
 * @author winne
 */
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

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
        ClienteDAO clienteDAO = new ClienteDAO();
        String option = (String) request.getParameter("option");
        int id;
        ArrayList<Cliente> myClients;
        Cliente client = new Cliente();
        
        switch (option) {
            case "get":
                myClients = clienteDAO.getAll();
                request.setAttribute("myClients", myClients);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/views/ListaClientesView.jsp");
                show.forward(request, response);
                break;

            case "insert":
                client.setId(0);
                client.setNome("");
                client.setDocumento("");
                client.setEndereco("");
                client.setBairro("");
                client.setCidade("");
                client.setUf("");
                client.setCep("");
                client.setTelefone("");
                client.setEmail("");

                request.setAttribute("client", client);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/forms/FormCliente.jsp");
                insert.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                client = clienteDAO.getById(id);

                if (client.getId() > 0) {
                    request.setAttribute("client", client);
                    RequestDispatcher rs = request.getRequestDispatcher("FormCliente.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erro ao salvar cliente!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/ClienteSavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":

                id = Integer.parseInt(request.getParameter("id"));
                clienteDAO.delete(id);

                myClients = clienteDAO.getAll();
                request.setAttribute("myClients", myClients);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/views/ListaClientesView.jsp");
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
            Cliente client = new Cliente();
            client.setId(Integer.parseInt(request.getParameter("id")));
            client.setNome(request.getParameter("nome"));
            client.setDocumento(request.getParameter("documento"));
            client.setEndereco(request.getParameter("endereco"));
            client.setBairro(request.getParameter("bairro"));
            client.setCidade(request.getParameter("cidade"));
            client.setUf(request.getParameter("uf"));
            client.setCep(request.getParameter("cep"));
            client.setTelefone(request.getParameter("telefone"));
            client.setEmail(request.getParameter("email"));
            
            ClienteDAO clienteDAO = new ClienteDAO();

            if (clienteDAO.put(client)) {
                message = "Cliente salvo com sucesso!";
            } else {
                message = "Erro ao salvar cliente!";
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/ClienteSavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao salvar cliente!";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/ClienteSavingMessage.jsp");
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
