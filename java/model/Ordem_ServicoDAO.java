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
public class Ordem_ServicoDAO {

    private static final String CADASTRA_ORDEM_SERVICO_PARCIAL_CLIENTE = "insert into ordem_servico (cliente,data_partida,endereco_origem,endereco_destino,dimensao_pacote,km_percorrido)values (?,?,?,?,?,?) returning id";
    private static final String ATUALIZA_VALOR_FRETE = "UPDATE ordem_servico set valor_viagem = ? where id = ? ";
    private static final String ATUALIZA_VEICULO_FRETE = "UPDATE ordem_servico set veiculo = ? where id = ? ";
    private static final String ATUALIZA_MOTORISTA_FRETE = "UPDATE ordem_servico set motorista =? where id=?";
    private static final String CONSULTA_TODAS_OS = "select Os.id, Cli.nome,Veic.marca , Veic.modelo,Os.data_partida,Os.valor_viagem\n" +
                                                    "from ordem_servico as Os, cliente as Cli, veiculo as Veic\n" +
                                                    "where Os.cliente = Cli.id and Os.veiculo = Veic.id";
    
    public void cadastrarOrdemServico(Ordem_Servico os, Endereco_Viagem endereco_origem, Endereco_Viagem endereco_destino, Pacote pacote, Cliente cliente) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRA_ORDEM_SERVICO_PARCIAL_CLIENTE);
            pstmt.setInt(1, cliente.getId());
            pstmt.setString(2, os.getData_partida());
            pstmt.setInt(3, endereco_origem.getId());
            pstmt.setInt(4, endereco_destino.getId());
            pstmt.setInt(5, pacote.getId());
            pstmt.setDouble(6, os.getKm_percorrido());

           ResultSet resultadoPk = pstmt.executeQuery(); 
           
           if(resultadoPk.next()){
                    os.setId(resultadoPk.getInt("id"));
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

    }

    public Ordem_Servico setValorViagem(Ordem_Servico os) {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(ATUALIZA_VALOR_FRETE);

            pstm.setDouble(1, os.getValor_viagem());

            pstm.setInt(2, os.getId());

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
        return os;
    }
     public Ordem_Servico setVeiculoViagem(Ordem_Servico os, Veiculo veiculo) {

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(ATUALIZA_VEICULO_FRETE);

            pstm.setInt(1, veiculo.getId());

            pstm.setInt(2, os.getId());

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
        return os;
    }
     
     public Ordem_Servico setMotoristaViagem(Usuario motorista, Ordem_Servico os){
         
     Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(ATUALIZA_MOTORISTA_FRETE);

            pstm.setInt(1, motorista.getId());

            pstm.setInt(2, os.getId());

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
        return os;
    }
       public List<Ordem_Servico> listarOrdemServico() throws ClassNotFoundException, SQLException {

         Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(CONSULTA_TODAS_OS);
        

        ResultSet resultado = comando.executeQuery();

        List<Ordem_Servico> todasOs = new ArrayList<Ordem_Servico>();
        while (resultado.next()) {
            Ordem_Servico os = new Ordem_Servico();
            
            os.setId(resultado.getInt("id"));
            os.setNome_cliente(resultado.getString("Cli.nome"));
            os.setModelo_veiculo(resultado.getString("Veic.modelo"));
            os.setMarca_veiculo(resultado.getString("Veic.marca"));
            os.setData_partida(resultado.getString("Os.data_partida"));
            os.setValor_viagem(resultado.getInt("Os.valor_viagem"));
            
            
            
            todasOs.add(os);

        }

        con.close();
        return todasOs;

    }

}


