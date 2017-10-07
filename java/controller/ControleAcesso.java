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
import model.PerfilDeAcesso;
import model.Usuario;
import model.UsuarioDAO;
import util.Conf;

/**
 *
 * @author Ramon Cordeiro
 */
public class ControleAcesso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            
            String acao = request.getParameter("acao");
            if(acao.equals("Entrar")){
                Usuario usuario = new Usuario();
                usuario.setLogin(request.getParameter("txtLogin"));
                usuario.setSenha(request.getParameter("txtSenha"));
                
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                Usuario usuarioAutenticado = usuarioDAO.autenticaUsuario(usuario);
                
                //se o usuario existe no banco de dados 
                if(usuarioAutenticado !=null){
                    
                    //cria uma sessao para o usuario 
                    
                    HttpSession sessaoUsuario = request.getSession();
                    sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);
                    
                    //redireciona para página principal
                    if (usuarioAutenticado.getPerfil().equals(PerfilDeAcesso.ADMINISTRADOR)) {
                    response.sendRedirect("admin/principal_admin.jsp");  
                        
                    } 
                    
                    //redireciona para a página principal do motorista
                    else if (usuarioAutenticado.getPerfil().equals(PerfilDeAcesso.MOTORISTA)) {
                    response.sendRedirect("principal_motorista.jsp");
                        
                    }
                    
                    else{
                       response.sendRedirect("logistica/principal_func_logistica.jsp");  
                    }
                }else{
                    RequestDispatcher rd = request.getRequestDispatcher("/login_func.jsp");
                    request.setAttribute("msg","Login ou Senha incorreto !");
                    rd.forward(request, response);
                }
                
            }else {
                
                
                if(acao.equals("Sair")){
                    HttpSession sessaoUsuario = request.getSession();
                    sessaoUsuario.removeAttribute("usuarioAutenticado");
                    response.sendRedirect("logout.jsp");
                }
            }
               
            
        } catch (Exception erro) {
            
            RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("erro",erro );
            rd.forward(request, response);
        }
        
        
        
        
        
        
    }

  
    @Override
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
