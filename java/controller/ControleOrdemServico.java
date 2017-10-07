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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Cliente;
import model.Endereco_Viagem;
import model.Endereco_ViagemDAO;
import model.Ordem_Servico;
import model.Ordem_ServicoDAO;
import model.Pacote;
import model.PacoteDAO;
import model.Usuario;
import model.UsuarioDAO;
import model.Veiculo;
import model.VeiculoDAO;

/**
 *
 * @author Ramon Cordeiro
 */
public class ControleOrdemServico extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            String acao = request.getParameter("acao");

            if (acao.equals("Cadastrar")) {
                cadastraOrdemServico(request, response);
            }
            else if(acao.equals("ListarTodasOs")){
                listarTodasOs(request, response);
            }
            

        } catch (Exception e) {
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

    private void cadastraOrdemServico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        //Recupera dados Endereço Origem
        String cepOrigem = request.getParameter("txtCep_O");
        String ruaOrigem = request.getParameter("txtRua_O");
        int numeroOrigem = Integer.parseInt(request.getParameter("txtNumero_O"));
        String cidadeOrigem = request.getParameter("txtCidade_O");
        String bairroOrigem = request.getParameter("txtBairro_O");

        //Recupera dados Endereco Deestino 
        String cepDestino = request.getParameter("txtCep_D");
        String ruaDestino = request.getParameter("txtRua_D");
        String numeroDestino = request.getParameter("txtNumero_D");
        String cidadeDestino = request.getParameter("txtCidade_D");
        String bairroDestino = request.getParameter("txtBairro_D");

        //Recupera dados Pacote
        String altura = request.getParameter("txtAltura");
        String comprimento = request.getParameter("txtComprimento");
        String largura = request.getParameter("txtComprimento");

        //Recupera dados Ordem Serviço
        String dataPartida = request.getParameter("txtDataPartida");
        String dataKmPercorrido = request.getParameter("txtKmPercorrido");

        //Recuepra o Id cliente
        String idCliente = request.getParameter("txtIdCliente");

        //Instâncio todos os objetos
        Cliente cliente = new Cliente();
        Ordem_Servico os = new Ordem_Servico();
        Usuario usuario = new Usuario();
        Pacote pacote = new Pacote();
        Endereco_Viagem eO = new Endereco_Viagem();
        Endereco_Viagem eD = new Endereco_Viagem();
        Veiculo veiculo =new Veiculo();

        Endereco_ViagemDAO daoEo = new Endereco_ViagemDAO();
        Endereco_ViagemDAO daoED = new Endereco_ViagemDAO();
        Ordem_ServicoDAO daoOs = new Ordem_ServicoDAO();
        PacoteDAO daoP = new PacoteDAO();
        VeiculoDAO veicDAO = new VeiculoDAO();
        UsuarioDAO usuDAO = new UsuarioDAO();

       //Set em todos os objetos
        eO.setCep(cepOrigem);
        eO.setRua(ruaOrigem);
        eO.setNumero(numeroOrigem);
        eO.setBairro(bairroOrigem);
        eO.setCidade(cidadeOrigem);

        eD.setCep(cepDestino);
        eD.setRua(ruaOrigem);
        eD.setNumero(Integer.parseInt(numeroDestino));
        eD.setBairro(bairroDestino);
        eD.setCidade(cidadeDestino);

        pacote.setAltura(Integer.parseInt(altura));
        pacote.setComprimento(Integer.parseInt(comprimento));
        pacote.setLargura(Integer.parseInt(largura));

        os.setData_partida(dataPartida);
        os.setKm_percorrido(Integer.parseInt(dataKmPercorrido));

        cliente.setId(Integer.parseInt(idCliente));

        daoEo.cadastraNovoEndereco(eO);
        daoED.cadastraNovoEndereco(eD);
        daoP.cadastraNovoPacote(pacote);
        
        //cadastra Ordem Servico passando todos obj na assinatura
        
       daoOs.cadastrarOrdemServico(os, eO, eD, pacote, cliente);

        
          //Calcula a viagem 
          
         os.calculaViagem(os);
        daoOs.setValorViagem(os);
   
     //Classifica Pacote
     
     pacote.classificaPacote(pacote);
     daoP.setTipoPacote(pacote);
     
     //Aloca Veiculo
     
    veiculo.classificaTipoVeiculo(pacote);
    veiculo = veicDAO.consultarPorTipo(veiculo);
    
    daoOs.setVeiculoViagem(os,veiculo);
    
    
     //Aloca Motorista
     
     usuario.classificaTipoMotorista(veiculo);
     usuario = usuDAO.buscaMotorista(usuario);
     
     daoOs.setMotoristaViagem(usuario, os);


        RequestDispatcher rd = request.getRequestDispatcher("cliente/testeOS.jsp");
        rd.forward(request, response);
    }

    private void listarTodasOs(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        
        Ordem_ServicoDAO dao = new Ordem_ServicoDAO();
        
        List<Ordem_Servico> todasOs  = dao.listarOrdemServico();
        
        request.setAttribute("retornaTodasOs", todasOs);
        RequestDispatcher rd = request.getRequestDispatcher("/logistica/manter_ordem_servico.jsp");
        rd.forward(request, response);
       
    }

}
