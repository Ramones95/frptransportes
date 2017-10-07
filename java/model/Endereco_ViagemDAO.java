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
import util.ConectaBanco;

/**
 *
 * @author Ramon Cordeiro
 */
public class Endereco_ViagemDAO {
    
    private static final String CADASTRA_ENDERECO_VIAGEM = "INSERT INTO endereco_viagem  (cep,numero,rua,bairro,cidade) VALUES (?,?,?,?,?) returning id";
    private static final String SELECIONA_ID_ENDERECO = "SELECT ID FROM ENDERECO_VIAGEM WHERE ID=?";
    
    public void cadastraNovoEndereco(Endereco_Viagem endereco) { 
        Connection conexao = null; 
        PreparedStatement pstmt = null;     
        try { 
            conexao = ConectaBanco.getConexao(); 
            pstmt = conexao.prepareStatement(CADASTRA_ENDERECO_VIAGEM);            
            pstmt.setString(1, endereco.getCep());            
            pstmt.setInt(2, endereco.getNumero()); 
            pstmt.setString(3, endereco.getRua());      
            pstmt.setString(4, endereco.getBairro()); 
            pstmt.setString(5, endereco.getCidade());   
            ResultSet resultadoPK = pstmt.executeQuery();  

                if(resultadoPK.next()){
                    endereco.setId(resultadoPK.getInt("id"));
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
    
    
    }
    
    
    

