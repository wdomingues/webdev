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
import javax.servlet.http.HttpSession;
import model.FuncionarioDAO;

/**
 *
 * @author winne
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

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
        RequestDispatcher rd = request.getRequestDispatcher("/forms/FormLogin.jsp");
        rd.forward(request,response);
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
            String cpf = request.getParameter("cpf");
            String senha = request.getParameter("senha");
            
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

            ArrayList<Funcionario> myEmployees = funcionarioDAO.getAll();
            String papel = "";
            String papelExt = "";
            for (Funcionario emp : myEmployees){
                if (emp.getDocumento().equals(cpf) && emp.getSenha().equals(senha)){
                    employee = emp;
                    break;
                }
            }
            papel = employee.getPapel();
                    
            if(papel == "") {
                papelExt = " ";
                request.setAttribute("message", "Usuario nao identificado");
                RequestDispatcher rd = request.getRequestDispatcher("/forms/FormLogin.jsp");
                rd.forward(request, response);
            } 
            else {
                if(papel.equalsIgnoreCase("1")) {
                    papelExt = "Vendedor";
                } else if(papel.equalsIgnoreCase("2")) {
                    papelExt = "Comprador";
                } else if(papel.equalsIgnoreCase("0")) {
                    papelExt = "Administrador";
                }
                request.setAttribute("message", "Usuario" + papelExt.toUpperCase() + " logado");
            }   
            HttpSession session = request.getSession();
            session.setAttribute("papelExt", papelExt);
            session.setAttribute("usuario", employee);
            String areaRestrita = "/auxJSPs/restricteds/AreaRestrita"+papelExt+".jsp";
            RequestDispatcher rd = request.getRequestDispatcher(areaRestrita);
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao logar!";
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("/auxJSPs/LoginSavingMessage.jsp");
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
