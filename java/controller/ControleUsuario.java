/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PerfilDeAcesso;
import model.Usuario;
import model.UsuarioDAO;
import javax.servlet.RequestDispatcher; 
import model.Cliente;
import model.ClienteDAO;

/**
 *
 * @author Ramon Cordeiro
 */
public class ControleUsuario extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
   
        try {
             String acao = request.getParameter("acao");
             
             if(acao.equals("Cadastrar")){
                
                cadastrarUsuario(request,response);
                 
             }else if(acao.equals("ListarUsuarios")){
                 listarTodosUsuarios(request, response);
                 
             }else if(acao.equals("buscarUsuarioEdicao")){
                 buscarUsuarioEdicao(request, response);
                 
             }else if(acao.equals("Alterar")){
                 alteraCliente(request, response);
             }else if(acao.equals("usuarioExcluido")){
                 excluirUsuario(request,response);
             }
             
             
        } catch (Exception erro) {
             RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            request.setAttribute("erro", erro);
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

    private void listarTodosUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        
        UsuarioDAO dao  = new UsuarioDAO();
        
        List<Usuario> listarTodos = dao.consultarTodosUsuarios();
        
        request.setAttribute("retornaTodosUsuarios", listarTodos);
        RequestDispatcher rd = request.getRequestDispatcher("/admin/manter_func.jsp");
        rd.forward(request, response);
        
        
        
    }

    private void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
                String login = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                String perfil= request.getParameter("optPerfil");
                String nome = request.getParameter("txtNome");
                String cnh = request.getParameter("txtCnh");
                
                Usuario usuario = new Usuario();
                usuario.setLogin(login);
                usuario.setSenha(senha);
                usuario.setNome(nome);
                usuario.setCnh(cnh);
                
                if(perfil.equalsIgnoreCase("administrador")){
                    usuario.setPerfil(PerfilDeAcesso.ADMINISTRADOR);
                    
                } 
                else if (perfil.equalsIgnoreCase("motorista")){
                    usuario.setPerfil(PerfilDeAcesso.MOTORISTA);
                    
                }
                
                else {
                    usuario.setPerfil(PerfilDeAcesso.FUNC_LOGISTICA);
                }
                
                
                
                UsuarioDAO usuarioDAO = new UsuarioDAO();
                usuarioDAO.cadastraNovoUsuario(usuario);
                request.setAttribute("msg", "Cadastrado com sucesso");
                RequestDispatcher rd =  request.getRequestDispatcher("admin/cadastro_usuario.jsp");
                rd.forward(request, response);
    }

    private void buscarUsuarioEdicao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
         Usuario usuario = new Usuario();

        String id = request.getParameter("idUsuario");
        usuario.setId(Integer.parseInt(id));

        UsuarioDAO dao = new UsuarioDAO();

        Usuario usuarioEdicao = dao.consultarPorId(usuario);

        request.setAttribute("usuarioEdicao", usuarioEdicao);
        RequestDispatcher rd = request.getRequestDispatcher("/admin/altera_cadastro_usuario.jsp");
        rd.forward(request, response);
        
    }

    private void alteraCliente(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
      
        Usuario usuario = new Usuario();
        String nome = request.getParameter("txtNome");
        String login = request.getParameter("txtLogin");
        String senha = request.getParameter("txtSenha");
        String id = request.getParameter("txtId");
        String cnh  = request.getParameter("txtCnh");
        
        usuario.setId(Integer.parseInt(id));
       

        
       
      
        usuario.setNome(nome);
        usuario.setLogin(login);
        usuario.setSenha(senha);
        usuario.setCnh(cnh);
        
        
        UsuarioDAO dao = new UsuarioDAO();
        
        usuario = dao.alterarUsuario(usuario);
        
        this.listarTodosUsuarios(request, response);
        
         
         request.setAttribute("clienteAlterado", usuario);
         RequestDispatcher rd = request.getRequestDispatcher("/admin/manter_func.jsp");
         rd.forward(request, response);
        
    }

    private void excluirUsuario(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        
        Usuario usuario = new Usuario();

        String id = request.getParameter("idUsuario");
        usuario.setId(Integer.parseInt(id));

        UsuarioDAO dao = new UsuarioDAO();

        Usuario usuarioExcluido = dao.excluirUsuario(usuario);

       this.listarTodosUsuarios(request, response);
        
    }
    
    


    
}
