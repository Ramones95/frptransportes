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
public class ControleCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         try {
            String acao = request.getParameter("acao");

         if (acao.equals("Consultar")) {
                listaClienteCpf(request, response);

            }
         else if (acao.equals("Cadastrar")){
             
             cadasrarCliente(request, response);
         }
         else if (acao.equals("Alterar")){
             
             alterarCliente(request, response);
             //red
         }
         else if (acao.equals("ListarClientes")){
             
             listarTodosClientes(request, response);
         }
         else if (acao.equals("Alterar2")){
             alterarCliente2(request, response);
             
         }else if (acao.equals("BuscarClienteEdicao")){
             buscarClienteEdicao(request, response);
           
         }else if (acao.equals("clienteExcluido")){
             clienteExcluido(request,response);
         }
         else if (acao.equals("buscarClienteEdicaoAdm")){
             buscarClienteEdicaoAdm(request,response);
         }

        } catch (Exception erro) {
            erro.printStackTrace();
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

    private void cadasrarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cpf = request.getParameter("txtCpf");
        String nome = request.getParameter("txtNome");
        String rg = request.getParameter("txtRg");
        String celular = request.getParameter("txtCelular");
        String endereco = request.getParameter("txtEndereco");
        String login = request.getParameter("txtLogin");
        String senha = request.getParameter("txtSenha");
        String data_nasc = request.getParameter("txtData_Nasc");

        Cliente cliente = new Cliente();

        cliente.setCpf(cpf);
        cliente.setNome(nome);
        cliente.setRg(rg);
        cliente.setCelular(celular);
        cliente.setEndereco(endereco);
        cliente.setLogin(login);
        cliente.setSenha(senha);
        cliente.setData_nasc(data_nasc);
        
        
                
        
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.cadastraNovoCliente(cliente);
        request.setAttribute("msg", "Cadastrado com sucesso");
        RequestDispatcher rd = request.getRequestDispatcher("cliente/cadastro_cliente.jsp");
        rd.forward(request, response);
    }

    private void listaClienteCpf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        Cliente cliente = new Cliente();

        String cpf = request.getParameter("txtCpf");
        cliente.setCpf(cpf);

        ClienteDAO clienteDAO = new ClienteDAO();

        List<Cliente> listarCliente= clienteDAO.consultarPorCpf(cliente);

        request.setAttribute("retornaCliente", listarCliente);
        RequestDispatcher rd = request.getRequestDispatcher("/cliente/principal_cliente.jsp");
        rd.forward(request, response);

    }
    
  

    private void alterarCliente(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        
       
        String nome = request.getParameter("txtNome");
        String data_nasc = request.getParameter("txtData_Nasc");
        String rg = request.getParameter("txtRg");
        String celular = request.getParameter("txtCelular");
        String endereco = request.getParameter("txtEndereco");
        String cpf = request.getParameter("txtCpf");

        
        Cliente cliente = new Cliente();
        
        cliente.setNome(nome);
        cliente.setData_nasc(data_nasc);
        cliente.setRg(rg);
        cliente.setCelular(celular);
        cliente.setEndereco(endereco);
        cliente.setCpf(cpf);
        
        
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente = clienteDAO.alterarCliente(cliente);
        
       this.listaClienteCpf(request, response);
        
         
        
    }

    private void listarTodosClientes(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        
        ClienteDAO dao  = new ClienteDAO();
        
        List<Cliente> listarTodos = dao.consultarTodos();
        
        request.setAttribute("retornaTodosClientes", listarTodos);
        RequestDispatcher rd = request.getRequestDispatcher("/admin/manter_cliente.jsp");
        rd.forward(request, response);
        
    }
    
    private void alterarCliente2(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        
       
        String nome = request.getParameter("txtNome");
        String data_nasc = request.getParameter("txtData_Nasc");
        String rg = request.getParameter("txtRg");
        String celular = request.getParameter("txtCelular");
        String endereco = request.getParameter("txtEndereco");
        String cpf = request.getParameter("txtCpf");

        
        Cliente cliente = new Cliente();
        
        cliente.setNome(nome);
        cliente.setData_nasc(data_nasc);
        cliente.setRg(rg);
        cliente.setCelular(celular);
        cliente.setEndereco(endereco);
        cliente.setCpf(cpf);
        
        
        ClienteDAO clienteDAO = new ClienteDAO();
        
        cliente = clienteDAO.alterarCliente(cliente);
        
        this.listarTodosClientes(request, response);
        
         request.setAttribute("msg", "Alterado com sucesso");
         request.setAttribute("clienteAlterado", cliente);
         RequestDispatcher rd = request.getRequestDispatcher("/admin/manter_cliente.jsp");
         rd.forward(request, response);
        
    }
      private void buscarClienteEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        Cliente cliente = new Cliente();

        String id = request.getParameter("idCliente");
        cliente.setId(Integer.parseInt(id));

        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente clienteEdicao = clienteDAO.consultarPorId(cliente);

        request.setAttribute("clienteEdicao", clienteEdicao);
        RequestDispatcher rd = request.getRequestDispatcher("/cliente/altera_cadastro_cliente.jsp");
        rd.forward(request, response);

    }
      private void buscarClienteEdicaoAdm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        Cliente cliente = new Cliente();

        String id = request.getParameter("idCliente");
        cliente.setId(Integer.parseInt(id));

        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente clienteEdicao = clienteDAO.consultarPorId(cliente);

        request.setAttribute("clienteEdicaoAdm", clienteEdicao);
        RequestDispatcher rd = request.getRequestDispatcher("/admin/altera_cadastro_cliente.jsp");
        rd.forward(request, response);

    }
      private void clienteExcluido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        Cliente cliente = new Cliente();

        String id = request.getParameter("idCliente");
        cliente.setId(Integer.parseInt(id));

        ClienteDAO clienteDAO = new ClienteDAO();

        Cliente clienteExcluido = clienteDAO.excluirCliente(cliente);

       this.listarTodosClientes(request, response);

    }

}


