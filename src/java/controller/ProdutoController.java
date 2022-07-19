/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import application.Categoria;
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
import model.CategoriaDAO;
import model.ClienteDAO;
import model.FuncionarioDAO;
import model.ProdutoDAO;
import model.VendaDAO;

/**
 *
 * @author winne
 */
@WebServlet(name = "ProdutoController", urlPatterns = {"/ProdutoController"})
public class ProdutoController extends HttpServlet {

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
        ProdutoDAO produtoDAO = new ProdutoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        String option = (String) request.getParameter("option");
        int id;
        ArrayList<Categoria> myCategories;
        ArrayList<Produto> myProducts;
        
        Produto product = new Produto();
        
        myCategories = categoriaDAO.getAll();
        request.setAttribute("myCategories", myCategories);

        
        switch (option) {
            case "get":
                myProducts = produtoDAO.getAll();
                request.setAttribute("myProducts", myProducts);
                RequestDispatcher show = getServletContext().getRequestDispatcher("/views/ListaProdutosView.jsp");
                show.forward(request, response);
                break;
                
            case "insert":
                product.setId(0);
                product.setNomeProduto("");
                product.setDescricao("");
                product.setPrecoCompra(0f);
                product.setPrecoVenda(0f);
                product.setQuantidadeDisponivel(0);
                product.setLiberadoVenda("");
                product.setIdCategoria(0);

                request.setAttribute("product", product);
                RequestDispatcher insert = getServletContext().getRequestDispatcher("/forms/FormProduto.jsp");
                insert.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                product = produtoDAO.getById(id);

                if (product.getId() > 0) {
                    request.setAttribute("product", product);
                    RequestDispatcher rs = request.getRequestDispatcher("/forms/FormProduto.jsp");
                    rs.forward(request, response);
                } else {
                    String message = "Erro ao salvar produto!";
                    request.setAttribute("message", message);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/ProdutoSavingMessage.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":

                id = Integer.parseInt(request.getParameter("id"));
                produtoDAO.delete(id);

                myProducts = produtoDAO.getAll();
                request.setAttribute("myProducts", myProducts);
                RequestDispatcher listAfterDeleting = getServletContext().getRequestDispatcher("/views/ListaProdutosView.jsp");
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
            Produto product = new Produto();

            product.setId(Integer.parseInt(request.getParameter("id")));
            product.setNomeProduto(request.getParameter("nome_produto"));
            product.setDescricao(request.getParameter("descricao"));
            product.setPrecoCompra(Float.parseFloat(request.getParameter("preco_compra").replace(",", ".")));
            product.setPrecoVenda(Float.parseFloat(request.getParameter("preco_venda").replace(",", ".")));
            product.setQuantidadeDisponivel(Integer.parseInt(request.getParameter("quantidade_disponivel")));
            product.setLiberadoVenda(request.getParameter("liberado_venda"));
            product.setIdCategoria(Integer.parseInt(request.getParameter("id_categoria")));
            
            ProdutoDAO produtoDAO = new ProdutoDAO();

            if (produtoDAO.put(product)) {
                message = "Produto salvo com sucesso!";
            } else {
                message = "Erro ao salvar produto!";
            }

            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/ProdutoSavingMessage.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            message = "Erro ao salvar produto!";
            request.setAttribute("message", message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/auxJSPs/ProdutoSavingMessage.jsp");
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
