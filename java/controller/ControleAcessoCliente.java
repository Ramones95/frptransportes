/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cliente;
import model.ClienteDAO;
import model.PerfilDeAcesso;
import model.Usuario;
import model.UsuarioDAO;

/**
 *
 * @author Ramon Cordeiro
 */
public class ControleAcessoCliente extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");

         try {
            
            String acao = request.getParameter("acao");
            if(acao.equals("Entrar")){
                Cliente cliente = new Cliente();
                cliente.setLogin(request.getParameter("txtLogin"));
                cliente.setSenha(request.getParameter("txtSenha"));
                
                
                ClienteDAO clienteDAO = new ClienteDAO();
                Cliente clienteAutenticado = clienteDAO.autenticaCliente(cliente);
                
                //se o usuario existe no banco de dados 
                if(clienteAutenticado !=null){
                    
                    //cria uma sessao para o usuario 
                    
                    HttpSession sessaoCliente = request.getSession();
                    sessaoCliente.setAttribute("clienteAutenticado", clienteAutenticado);
                    
                    response.sendRedirect("cliente/painel_cliente.jsp");
                    
                    
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("login_cliente.jsp");
                    request.setAttribute("msg","Login ou Senha incorreto !");
                    rd.forward(request, response);
                }
                
            }else {
                
                
                if(acao.equals("Sair")){
                    HttpSession sessaoCliente = request.getSession();
                    sessaoCliente.removeAttribute("clienteAutenticado");
                    response.sendRedirect("login_cliente.jsp");
                }
            }
               
            
        } catch (Exception erro) {
            
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("erro",erro );
            rd.forward(request, response);
        }
        
        
        
        
        
    }
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
