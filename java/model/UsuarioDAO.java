package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConectaBanco;

public class UsuarioDAO {

    private static final String CADASTRA_NOVO_USUARIO = "INSERT INTO usuario (login, senha, perfil,nome,cnh) VALUES (?,?,?,?,?)";
    private static final String AUTENTICA_USUARIO = "SELECT * FROM usuario WHERE login=? AND senha=?";
    private static final String BUSCA_TODOS_USUARIOS = "SELECT *FROM USUARIO";
    private static final String ALTERA_USUARIO_ID = "UPDATE usuario SET login =?,senha = ?, nome = ?, cnh = ?"
            + " WHERE id = ?";
    private static final String BUSCA_USUARIO_ID = "SELECT *from usuario where id =?";
    private static final String EXCLUI_USUARIO_ID = "DELETE FROM usuario WHERE id = ?";
    private static final String BUSCA_MOTORISTA="select *from usuario where perfil='MOTORISTA' and cnh=?";

    public void cadastraNovoUsuario(Usuario usuario) {
        Connection conexao = null;
        PreparedStatement pstmt = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(CADASTRA_NOVO_USUARIO);
            pstmt.setString(1, usuario.getLogin());
            pstmt.setString(2, usuario.getSenha());
            pstmt.setString(3, usuario.getPerfil().toString());
            pstmt.setString(4, usuario.getNome());
            pstmt.setString(5, usuario.getCnh());

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

    public List<Usuario> consultarTodosUsuarios() throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(BUSCA_TODOS_USUARIOS);

        ResultSet resultado = comando.executeQuery();

        List<Usuario> todosUsuarios = new ArrayList<Usuario>();
        while (resultado.next()) {
            Usuario u = new Usuario();
            u.setId(resultado.getInt("id"));
            u.setNome(resultado.getString("nome"));
            //   u.setPerfil(resultado.getString("perfil"));   <-- Verificar por que não consigo setar Perfil
            u.setLogin(resultado.getString("login"));
            u.setSenha(resultado.getString("senha"));
            u.setCnh(resultado.getString("cnh"));

            todosUsuarios.add(u);

        }

        con.close();
        return todosUsuarios;

    }

    public Usuario consultarPorId(Usuario usuario) throws ClassNotFoundException, SQLException {

        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(BUSCA_USUARIO_ID);
        comando.setInt(1, usuario.getId());

        ResultSet resultado = comando.executeQuery();

        Usuario idUsuario = new Usuario();
        while (resultado.next()) {

            idUsuario.setId(resultado.getInt("id"));
            idUsuario.setNome(resultado.getString("nome"));
            idUsuario.setLogin(resultado.getString("login"));
            idUsuario.setSenha(resultado.getString("senha"));
            idUsuario.setCnh(resultado.getString("cnh"));

            

        }

        con.close();
        return idUsuario;

    }

    public Usuario excluirUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(EXCLUI_USUARIO_ID);

//UPDATE cliente SET nome =?,rg = ?, celular = ?, endereco = ?,email = ?, senha =?, data_nasc = ?"
            //Adiciona o valor do primeiro parâmetro da sql
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setInt(1, usuario.getId());
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
        return usuario;
    }

    public Usuario alterarUsuario(Usuario usuario) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco
            conn = ConectaBanco.getConexao();

            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(ALTERA_USUARIO_ID);

//UPDATE cliente SET nome =?,rg = ?, celular = ?, endereco = ?,email = ?, senha =?, data_nasc = ?"
            //Adiciona o valor do primeiro parâmetro da sql
            //Adicionar o valor do segundo parâmetro da sql
            pstm.setString(1, usuario.getLogin());
            //Adiciona o valor do terceiro parâmetro da sql
            pstm.setString(2, usuario.getSenha());

            pstm.setString(3, usuario.getNome());
            
            pstm.setString(4, usuario.getCnh());


            pstm.setInt(5, usuario.getId());

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
        return usuario;
    }
    
    
    public Usuario buscaMotorista(Usuario motorista) throws SQLException{
        
        Connection con = ConectaBanco.getConexao();

        PreparedStatement comando = con.prepareStatement(BUSCA_MOTORISTA);
        comando.setString(1, motorista.getCnh());

        ResultSet resultado = comando.executeQuery();
        
        Usuario motorista1= new Usuario();
         while (resultado.next()) {
             
           motorista1.setId(resultado.getInt("id"));
           motorista1.setLogin(resultado.getString("login"));
           motorista1.setCnh(resultado.getString("cnh"));
           motorista1.setPerfil(PerfilDeAcesso.MOTORISTA);
          
           
         }
         
             con.close();
            return motorista1;

    }

    public Usuario autenticaUsuario(Usuario usuario) {
        Usuario usuarioAutenticado = null;

        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(AUTENTICA_USUARIO);
            pstmt.setString(1, usuario.getLogin());
            pstmt.setString(2, usuario.getSenha());
            rsUsuario = pstmt.executeQuery();

            if (rsUsuario.next()) {
                usuarioAutenticado = new Usuario();
                usuarioAutenticado.setLogin(rsUsuario.getString("login"));
                usuarioAutenticado.setSenha(rsUsuario.getString("senha"));
                usuarioAutenticado.setPerfil(PerfilDeAcesso.valueOf(rsUsuario.getString("perfil")));
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

        return usuarioAutenticado;

    }
}
