/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Funcionario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.FuncionarioDAO;

/**
 *
 * @author winne
 */
@WebServlet(name = "FuncionarioController", urlPatterns = {"/FuncionarioController"})
public class FuncionarioController extends HttpServlet {

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
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        String option = (String) request.getParameter("option");
        int id;
        ArrayList<Funcionario> myEmployees;
        Funcionario employee = new Funcionario();
        
        switch (option) {
            case "get":
                myEmployees = funcionarioDAO.getAll();
                request.setAttribute("myEmployees", myEmployees);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/views/ListaFuncionariosView.jsp");
                show.forward(request, response);
                break;

            case "insert":
                employee.setId(0);
                employee.setNome("");
                employee.setDocumento("");
                employee.setSenha("");
                employee.setPapel("");

                request.setAttribute("employee", employee);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/forms/FormFuncionario.jsp");
                insert.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                employee = funcionarioDAO.getById(id);

                if (employee.getId() > 0) {
                    request.setAttribute("employee", employee);
                    RequestDispatcher rs = request.getRequestDispatcher("/forms/FormFuncionario.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erro ao salvar funcionario!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/FuncionarioSavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":

                id = Integer.parseInt(request.getParameter("id"));
                funcionarioDAO.delete(id);

                myEmployees = funcionarioDAO.getAll();
                request.setAttribute("myEmployees", myEmployees);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/views/ListaFuncionariosView.jsp");
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
            Funcionario employee = new Funcionario();
            employee.setId(Integer.parseInt(request.getParameter("id")));
            employee.setNome(request.getParameter("nome"));
            employee.setDocumento(request.getParameter("cpf"));
            employee.setSenha(request.getParameter("senha"));
            employee.setPapel(request.getParameter("papel"));
            
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            if (funcionarioDAO.put(employee)) {
                message = "Funcionario salvo com sucesso!";
            } else {
                message = "Erro ao salvar funcionario!";
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/FuncionarioSavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao salvar funcionario!";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/FuncionarioSavingMessage.jsp");
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
