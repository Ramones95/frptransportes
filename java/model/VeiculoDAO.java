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
 * @author pauli
 */
public class VeiculoDAO {

    private static final String CADASTRA_NOVO_VEICULO = "INSERT INTO veiculo (marca,modelo,cor,placa,dimensaoVeiculo,data_revisao,status,ano,tipo) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String EXCLUI_VEICULO_ID = "DELETE FROM veiculo WHERE id = ?";
    private static final String LISTAR_VEICULO_ID = "SELECT * from veiculo where id =?";
    private static final String LISTAR_VEICULO_TIPO = "SELECT * from veiculo where tipo =?";
    private static final String ALTERA_VEICULO = "UPDATE veiculo SET marca =?,modelo = ?, cor = ?,placa =?,dimensaoveiculo = ?, data_revisao = ?,status =?,ano =?,tipo =? "
            + " WHERE id = ?";
    private static final String BUSCA_TODOS_VEICULOS = "SELECT *FROM veiculo";

    public void cadastraNovoVeiculo(Veiculo veiculo) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRA_NOVO_VEICULO);
            pstmt.setString(1, veiculo.getMarca());
            pstmt.setString(2, veiculo.getModelo());
            pstmt.setString(3, veiculo.getCor());
            pstmt.setString(4, veiculo.getPlaca());
            pstmt.setString(5, veiculo.getDimensaoVeiculo());
            pstmt.setString(6, veiculo.getData_revisao());
            pstmt.setString(7, veiculo.getStatus());
            pstmt.setString(8, veiculo.getAno());
            pstmt.setString(9, veiculo.getTipo());

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

    public List<Veiculo> consultarTodosVeiculos() throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(BUSCA_TODOS_VEICULOS);

        ResultSet resultado = comando.executeQuery();

        List<Veiculo> todosVeiculos = new ArrayList<Veiculo>();
        while (resultado.next()) {
            Veiculo v = new Veiculo();
            v.setId(resultado.getInt("id"));
            v.setMarca(resultado.getString("marca"));
            v.setModelo(resultado.getString("modelo"));
            v.setCor(resultado.getString("cor"));
            v.setPlaca(resultado.getString("placa"));
            v.setDimensaoVeiculo(resultado.getString("dimensaoVeiculo"));
            v.setData_revisao(resultado.getString("data_revisao"));
            v.setStatus(resultado.getString("status"));
            v.setAno(resultado.getString("ano"));
            v.setTipo(resultado.getString("tipo"));

            todosVeiculos.add(v);

        }

        con.close();
        return todosVeiculos;

    }

    public Veiculo consultarPorId(Veiculo veiculo) throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(LISTAR_VEICULO_ID);
        comando.setInt(1, veiculo.getId());

        ResultSet resultado = comando.executeQuery();

        Veiculo v = new Veiculo();
        while (resultado.next()) {

            v.setId(resultado.getInt("id"));
            v.setMarca(resultado.getString("marca"));
            v.setModelo(resultado.getString("modelo"));
            v.setCor(resultado.getString("cor"));
            v.setPlaca(resultado.getString("placa"));
            v.setDimensaoVeiculo(resultado.getString("dimensaoVeiculo"));
            v.setData_revisao(resultado.getString("data_revisao"));
            v.setStatus(resultado.getString("status"));
            v.setAno(resultado.getString("ano"));
            v.setTipo(resultado.getString("tipo"));

        }

        con.close();
        return v;

    }

    public Veiculo excluirVeiculo(Veiculo veiculo) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(EXCLUI_VEICULO_ID);

            pstm.setInt(1, veiculo.getId());

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
        return veiculo;
    }

    public Veiculo alterarVeiculo(Veiculo veiculo) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(ALTERA_VEICULO);

            pstm.setString(1, veiculo.getMarca());

            pstm.setString(2, veiculo.getModelo());

            pstm.setString(3, veiculo.getCor());

            pstm.setString(4, veiculo.getPlaca());

            pstm.setString(5, veiculo.getDimensaoVeiculo());

            pstm.setString(6, veiculo.getData_revisao());

            pstm.setString(7, veiculo.getStatus());
            
            pstm.setString(8, veiculo.getAno());

            pstm.setString(9, veiculo.getTipo());
            
            pstm.setInt(10, veiculo.getId());

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
        return veiculo;
    }
    
        public Veiculo consultarPorTipo(Veiculo veiculo) throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(LISTAR_VEICULO_TIPO);
        comando.setString(1, veiculo.getTipo());

        ResultSet resultado = comando.executeQuery();

        Veiculo v = new Veiculo();
        while (resultado.next()) {

            v.setId(resultado.getInt("id"));
            v.setMarca(resultado.getString("marca"));
            v.setModelo(resultado.getString("modelo"));
            v.setCor(resultado.getString("cor"));
            v.setPlaca(resultado.getString("placa"));
            v.setDimensaoVeiculo(resultado.getString("dimensaoVeiculo"));
            v.setData_revisao(resultado.getString("data_revisao"));
            v.setStatus(resultado.getString("status"));
            v.setAno(resultado.getString("ano"));
            v.setTipo(resultado.getString("tipo"));

        }

        con.close();
        return v;

    }

}
