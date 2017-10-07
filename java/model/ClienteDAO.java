/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConectaBanco;

/**
 *
 * @author Ramon Cordeiro
 */
public class ClienteDAO {

    private static final String CADASTRA_NOVO_CLIENTE = "INSERT INTO cliente (cpf,nome,rg,celular,endereco,login,senha,data_nasc) VALUES (?,?,?,?,?,?,?,?)";
    private static final String EXCLUI_CLIENTE_CPF = "DELETE FROM cliente WHERE cpf = ?";
    private static final String EXCLUI_CLIENTE_ID = "DELETE FROM cliente WHERE id = ?";
    private static final String ALTERA_CLIENTE_CPF = "UPDATE cliente SET nome =?,rg = ?, celular = ?, endereco = ?,data_nasc = ?"
            + " WHERE cpf = ?";
    private static final String BUSCA_CLIENTE = "SELECT *from cliente where cpf =?";
    private static final String AUTENTICA_CLIENTE = "SELECT *from cliente where login = ? and senha =?";
    private static final String BUSCA_TODOS_CLIENTES = "SELECT *FROM CLIENTE";
    private static final String BUSCA_CLIENTE_ID = "SELECT *from cliente where id =?";

    public void cadastraNovoCliente(Cliente cliente) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRA_NOVO_CLIENTE);
            pstmt.setString(1, cliente.getCpf());
            pstmt.setString(2, cliente.getNome());
            pstmt.setString(3, cliente.getRg());
            pstmt.setString(4, cliente.getCelular());
            pstmt.setString(5, cliente.getEndereco());
            pstmt.setString(6, cliente.getLogin());
            pstmt.setString(7, cliente.getSenha());
            pstmt.setString(8, cliente.getData_nasc());

            pstmt.execute();

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        }

    }

    public List<Cliente> consultarPorCpf(Cliente cliente) throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(BUSCA_CLIENTE);
        comando.setString(1, cliente.getCpf());

        ResultSet resultado = comando.executeQuery();

        List<Cliente> cpfCliente = new ArrayList<Cliente>();
        while (resultado.next()) {
            Cliente c = new Cliente();
            c.setId(resultado.getInt("id"));
            c.setCpf(resultado.getString("cpf"));
            c.setNome(resultado.getString("nome"));
            c.setRg(resultado.getString("rg"));
            c.setCelular(resultado.getString("celular"));
            c.setEndereco(resultado.getString("endereco"));
            c.setLogin(resultado.getString("login"));
            c.setSenha(resultado.getString("senha"));
            c.setData_nasc(resultado.getString("data_nasc"));
            cpfCliente.add(c);

        }

        con.close();
        return cpfCliente;

    }
     public Cliente consultarPorId(Cliente cliente) throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(BUSCA_CLIENTE_ID);
        comando.setInt(1, cliente.getId());

        ResultSet resultado = comando.executeQuery();

        Cliente idCliente = new Cliente();
        while (resultado.next()) {
            
            idCliente.setId(resultado.getInt("id"));
            idCliente.setCpf(resultado.getString("cpf"));
            idCliente.setNome(resultado.getString("nome"));
            idCliente.setRg(resultado.getString("rg"));
            idCliente.setCelular(resultado.getString("celular"));
            idCliente.setEndereco(resultado.getString("endereco"));
            idCliente.setLogin(resultado.getString("login"));
            idCliente.setSenha(resultado.getString("senha"));
            idCliente.setData_nasc(resultado.getString("data_nasc"));
            

        }

        con.close();
        return idCliente;

    }
    

    public Cliente alterarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(ALTERA_CLIENTE_CPF);

//UPDATE cliente SET nome =?,rg = ?, celular = ?, endereco = ?,email = ?, senha =?, data_nasc = ?"
            //Adiciona o valor do primeiro parâmetro da sql
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(1, cliente.getNome());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setString(2, cliente.getRg());

            pstm.setString(3, cliente.getCelular());

            pstm.setString(4, cliente.getEndereco());

            pstm.setString(5, cliente.getData_nasc());

            pstm.setString(6, cliente.getCpf());

            pstm.execute();
            
          

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return cliente;
    }
    
 public List<Cliente> consultarTodos() throws ClassNotFoundException, SQLException {

         Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(BUSCA_TODOS_CLIENTES);
        

        ResultSet resultado = comando.executeQuery();

        List<Cliente> todosClientes = new ArrayList<Cliente>();
        while (resultado.next()) {
            Cliente c = new Cliente();
            c.setId(resultado.getInt("id"));
            c.setCpf(resultado.getString("cpf"));
            c.setNome(resultado.getString("nome"));
            c.setRg(resultado.getString("rg"));
            c.setCelular(resultado.getString("celular"));
            c.setEndereco(resultado.getString("endereco"));
            c.setLogin(resultado.getString("login"));
            c.setSenha(resultado.getString("senha"));
            c.setData_nasc(resultado.getString("data_nasc"));
            todosClientes.add(c);

        }

        con.close();
        return todosClientes;

    }
    public Cliente excluirCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(EXCLUI_CLIENTE_ID);

//UPDATE cliente SET nome =?,rg = ?, celular = ?, endereco = ?,email = ?, senha =?, data_nasc = ?"
            //Adiciona o valor do primeiro parâmetro da sql
            //Adicionar o valor do segundo parâmetro da sql
                pstm.setInt(1, cliente.getId());
            //Adiciona o valor do terceiro parâmetro da sql

            pstm.execute();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            //Fecha as conexões
            try {
                if (pstm != null) {

                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return cliente;
    }

    public Cliente autenticaCliente(Cliente cliente) {
        Cliente clienteAutenticado = null;

        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsCliente = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(AUTENTICA_CLIENTE);
            pstmt.setString(1, cliente.getLogin());
            pstmt.setString(2, cliente.getSenha());
            rsCliente = pstmt.executeQuery();

            if (rsCliente.next()) {
                clienteAutenticado = new Cliente();
                clienteAutenticado.setLogin(rsCliente.getString("login"));
                clienteAutenticado.setSenha(rsCliente.getString("senha"));
                clienteAutenticado.setId(rsCliente.getInt("id"));

            }

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        }

        return clienteAutenticado;

    }

}
