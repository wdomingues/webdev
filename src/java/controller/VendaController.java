/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Cliente;
import application.Funcionario;
import application.Produto;
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
import model.FuncionarioDAO;
import model.ProdutoDAO;
import model.ClienteDAO;
import java.lang.Integer;

import static application.Funcionario.Papeis.ADMINISTRADOR;
import static application.Funcionario.Papeis.VENDEDOR;
import static utils.Validators.convertDateString2Date;
/**
 *
 * @author winne
 */
@WebServlet(name = "VendaController", urlPatterns = {"/VendaController"})
public class VendaController extends HttpServlet {

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
        Permissions.requireRole(request, ADMINISTRADOR, VENDEDOR);
        VendaDAO vendaDAO = new VendaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        
        String option = request.getParameter("option");
        int id;
                
        ArrayList<Venda> myVendas;
        ArrayList<Cliente> myClients;
        ArrayList<Produto> myProducts;
        ArrayList<Funcionario> myEmployees;

        Venda venda = new Venda();
        
        myClients = clienteDAO.getAll();
        myProducts = produtoDAO.getAll();
        myEmployees = funcionarioDAO.getAll();
        request.setAttribute("myClients", myClients);
        request.setAttribute("myProducts", myProducts);
        request.setAttribute("myEmployees", myEmployees);
                
        switch (option) {
            case "get":
                Permissions.requireRole(request, VENDEDOR);
                myVendas = vendaDAO.getAll();
                request.setAttribute("myVendas", myVendas);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/views/ListaVendasView.jsp");
                show.forward(request, response);
                break;
            case "insert":
                Permissions.requireRole(request, VENDEDOR);
                venda.setId(0);
                venda.setQuantidadeVenda(0);
                venda.setDataVenda(new Date(0));
                venda.setValorVenda(0f);
                venda.setIdCliente(0);
                venda.setIdProduto(0);
                venda.setIdFuncionario(0);

                request.setAttribute("venda", venda);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/forms/FormVenda.jsp");
                insert.forward(request, response);
                break;

            case "edit":
                Permissions.requireRole(request, VENDEDOR);
                id = Integer.parseInt(request.getParameter("id"));
                venda = vendaDAO.getById(id);
                Permissions.requireUserId(request, venda.getIdFuncionario());

                if (venda.getId() > 0) {
                    request.setAttribute("venda", venda);
                    RequestDispatcher rs = request.getRequestDispatcher("/forms/FormVenda.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erro ao salvar venda!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/VendaSavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":
                Permissions.requireRole(request, VENDEDOR);
                id = Integer.parseInt(request.getParameter("id"));
                venda = vendaDAO.getById(id);
                Permissions.requireUserId(request, venda.getIdFuncionario());

                vendaDAO.delete(id);

                myVendas = vendaDAO.getAll();
                request.setAttribute("myVendas", myVendas);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/views/ListaVendasView.jsp");
                listAfterDeleting.forward(request, response);
                break;
            case "getReport":
                Permissions.requireRole(request, ADMINISTRADOR);
                myVendas = vendaDAO.getAll();
                request.setAttribute("myVendas", myVendas);
                RequestDispatcher rdReport = getServletContext().getRequestDispatcher("/views/RelatorioVendasView.jsp");
                rdReport.forward(request, response);
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
        Permissions.requireRole(request, VENDEDOR);
        Permissions.requireUserId(request, Integer.parseInt(request.getParameter("id_funcionario")));
        String message;
        try {
            Venda venda = new Venda();
            Produto product = new Produto();

            String dataStr = request.getParameter("data_venda");
            
            venda.setDataVenda(convertDateString2Date(dataStr));
            
            venda.setId(Integer.parseInt(request.getParameter("id")));
            venda.setQuantidadeVenda(Integer.parseInt(request.getParameter("quantidade_venda")));
            venda.setValorVenda(Float.parseFloat(request.getParameter("valor_venda").replace(",", ".")));
            venda.setIdCliente(Integer.parseInt(request.getParameter("id_cliente")));
            venda.setIdProduto(Integer.parseInt(request.getParameter("id_produto")));
            venda.setIdFuncionario(Integer.parseInt(request.getParameter("id_funcionario")));

            ProdutoDAO produtoDAO = new ProdutoDAO();
            product = produtoDAO.getById(venda.getIdProduto());
            
            VendaDAO vendaDAO = new VendaDAO();
            if (venda.getId() == 0) { // Ou seja, não foi persistido
                if (product.getQuantidadeDisponivel() >= venda.getQuantidadeVenda()) {
                    product.setQuantidadeDisponivel(product.getQuantidadeDisponivel() - venda.getQuantidadeVenda());
                    if (vendaDAO.put(venda)) {
                        if ((produtoDAO.put(product))) {
                            message = "Venda salva com sucesso!";
                        } else {
                            message = "Erro: Venda salva, mas estoque não atualizado";
                        }
                    } else {
                        message = "Erro ao salvar a venda!";
                    }
                } else {
                    message = "Erro: A quantidade desejada é maior que a quantidade disponível!";
                }
            } else { // Ou seja, estou editando
                if (vendaDAO.put(venda)) {
                        message = "Venda salva com sucesso!";
                } else {
                    message = "Erro ao salvar a venda!";
                }
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/VendaSavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao salvar Venda!";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/VendaSavingMessage.jsp");
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
