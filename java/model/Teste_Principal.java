/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon Cordeiro
 */
public class Teste_Principal {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
      /*
        //teste de cadastro
        Cliente novoCliente = new Cliente();
        novoCliente.setCpf("426.103.028-43");
        novoCliente.setNome("Eduardo");
        novoCliente.setCelular("474747474");
        novoCliente.setEndereco("av bento do sacramento, 531");
        novoCliente.setLogin("ramon.cordeiro");
        novoCliente.setSenha("1234567");
        novoCliente.setData_nasc("22/2/2000");
        novoCliente.setRg("333333333333");
        
        ClienteDAO cadastrarCli  = new ClienteDAO();
        
        cadastrarCli.cadastraNovoCliente(novoCliente);
       
    */
       /*
       //teste de consulta por cpf
       Cliente cliente = new Cliente();
       ClienteDAO cDAO = new ClienteDAO();
       
      cliente.setCpf("426.103.028-43");
       
      cliente =  cDAO.consultarPorCpf(cliente);
       
         

        System.out.println(cliente.getNome()); 
        
     */
       /*
       
       //teste de update 
       Cliente cliente = new Cliente ();
       Cliente cliente2 = new Cliente ();
       ClienteDAO clienteDAO  = new ClienteDAO();
       
       cliente.setCpf("123.456.789-10");
       
       cliente.setNome("Rafaela");
       cliente.setRg("1122334455");
       cliente.setCelular("4356765");
       cliente.setEndereco("Av ezelino da Cunha Gloria, 531");
       cliente.setData_nasc("1990-01-02");
       
       cliente = clienteDAO.alterarCliente(cliente);
       
       cliente2.setCpf("123.456.789-10");
       
       cliente2 =  clienteDAO.consultarPorCpf(cliente);
       
        System.out.println(cliente.getNome());
          


*/
       /*
       //teste exluir cliente por cpf
       Cliente cliente = new Cliente ();
       ClienteDAO clienteDAO  = new ClienteDAO();
       
        cliente.setCpf("123.456.789-10");
        
       cliente =  clienteDAO.excluirCliente(cliente);

    */
      
       /*
       
       ClienteDAO dao = new ClienteDAO();
       List<Cliente> listaClientes = dao.consultarTodos();
      
       
        System.out.println(listaClientes);  */
       
     /*  
       
    Pacote pacote = new Pacote();
    PacoteDAO dao = new PacoteDAO();
       
        pacote.setComprimento(10);
        pacote.setAltura(3);
        pacote.setLargura(4);
        pacote.setTipo("C");
       
       dao.cadastraNovoPacote(pacote);
       
       */
     
    // (cliente,data_partida,endereco_origem,endereco_destino,dimensao_pacote,km_percorrido)
    
    
   
    
    /*
    
    
    //Cadastro de Frete
    
    Cliente cliente  = new Cliente();
    Ordem_Servico ordem_servico = new Ordem_Servico();
    Pacote pacote = new Pacote();
    Endereco_Viagem e1 = new Endereco_Viagem();
    Endereco_Viagem e2 = new Endereco_Viagem();
    
    Endereco_ViagemDAO daoE1 = new Endereco_ViagemDAO();
    Endereco_ViagemDAO daoE2 = new Endereco_ViagemDAO();
    Ordem_ServicoDAO daoOs = new Ordem_ServicoDAO();
    PacoteDAO daoP = new PacoteDAO();
    
    
    //insere Endereco Origem 
    e1.setCep("0800-900");
    e1.setRua("Rua 3");
    e1.setNumero(400);
    e1.setBairro("centro");
    e1.setCidade("Mogi das Cruzes");
    
    //insere Endereco Destino
    
    e2.setCep("0800-000");
    e2.setRua("Rua 10");
    e2.setNumero(23);
    e2.setBairro("Cesar de Souza");
    e2.setCidade("Mogi das Cruzes");
    
    //insere Pacote
    
    pacote.setAltura(3);
    pacote.setComprimento(2);
    pacote.setLargura(1);
    
    //Insere dados da Ordem_Servico
     ordem_servico.setData_partida("10/10/2017");
     ordem_servico.setKm_percorrido(9);
    
    //Insere ID cliente
    
    cliente.setId(14);
    
  //Cadastra as tabelas, passando as foregin Key  
   daoE1.cadastraNovoEndereco(e1);
   daoE2.cadastraNovoEndereco(e2);
   daoP.cadastraNovoPacote(pacote);
   daoOs.cadastrarOrdemServico(ordem_servico,e1, e2, pacote, cliente);
   
   
   //Calcula a viagem 
   ordem_servico.calculaViagem(ordem_servico);
     daoOs.setValorViagem(cliente, ordem_servico);
   
     //Classifica Pacote
     
     pacote.classificaPacote(pacote);
     daoP.setTipoPacote(pacote);
  

    
    */
    
    
    }
    
    
   
   
    
    
    
}
