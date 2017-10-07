/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioDAO;
import model.Veiculo;
import model.VeiculoDAO;

/**
 *
 * @author pauli
 */
public class ControleVeiculo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String acao = request.getParameter("acao");

        if (acao.equals("Cadastrar")) {
            cadastraVeiculo(request, response);

        } else if (acao.equals("ConsultarTodos")) {
            listarTodosVeiculos(request, response);
        } else if (acao.equals("buscarVeiculoEdicao")) {
            buscarVeiculoEdicao(request, response);
        } else if (acao.equals("veiculoExcluido")) {
            excluirVeiculo(request, response);
        }else if (acao.equals("Alterar")){
            alteraVeiculo(request, response);
        }

    }

    private void cadastraVeiculo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String marca = request.getParameter("txtMarca");
        String modelo = request.getParameter("txtModelo");
        String cor = request.getParameter("txtCor");
        String placa = request.getParameter("txtPlaca");
        String dimensaoVeiculo = request.getParameter("txtDimensaoVeiculo");
        String data_revisao = request.getParameter("txtData_revisao");
        String status = request.getParameter("txtStatus");
        String ano = request.getParameter("txtAno");
        String tipo = request.getParameter("txtTipo");

        Veiculo veiculo = new Veiculo();

        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setCor(cor);
        veiculo.setPlaca(placa);
        veiculo.setDimensaoVeiculo(dimensaoVeiculo);
        veiculo.setData_revisao(data_revisao);
        veiculo.setStatus(status);
        veiculo.setAno(ano);
        veiculo.setTipo(tipo);

        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.cadastraNovoVeiculo(veiculo);
        request.setAttribute("msg", "Cadastrado com sucesso");
        RequestDispatcher rd = request.getRequestDispatcher("logistica/cadastro_veiculo.jsp");
        rd.forward(request, response);

    }

    private void listarTodosVeiculos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {

        VeiculoDAO dao = new VeiculoDAO();

        List<Veiculo> listarTodos = dao.consultarTodosVeiculos();

        request.setAttribute("retornaTodosVeiculos", listarTodos);
        RequestDispatcher rd = request.getRequestDispatcher("/logistica/manter_veiculo.jsp");
        rd.forward(request, response);

    }

    private void buscarVeiculoEdicao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
        Veiculo veiculo = new Veiculo();

        String id = request.getParameter("idVeiculo");
        veiculo.setId(Integer.parseInt(id));

        VeiculoDAO dao = new VeiculoDAO();

        Veiculo veiculoEdicao = dao.consultarPorId(veiculo);

        request.setAttribute("veiculoEdicao", veiculoEdicao);
        RequestDispatcher rd = request.getRequestDispatcher("/logistica/altera_cadastro_veiculo.jsp");
        rd.forward(request, response);

    }

    private void excluirVeiculo(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {

        Veiculo veiculo = new Veiculo();

        String id = request.getParameter("idVeiculo");
        veiculo.setId(Integer.parseInt(id));

        VeiculoDAO dao = new VeiculoDAO();

        dao.excluirVeiculo(veiculo);

        this.listarTodosVeiculos(request, response);
   


    }

    private void alteraVeiculo(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {

        Veiculo veiculo = new Veiculo();
        String marca = request.getParameter("txtMarca");
        String modelo = request.getParameter("txtModelo");
        String cor = request.getParameter("txtCor");
        String placa = request.getParameter("txtPlaca");
        String dimensao = request.getParameter("txtDimensaoVeiculo");
        String data_revisao = request.getParameter("txtData_revisao");
        String status = request.getParameter("txtStatus");
        String ano = request.getParameter("txtAno");
        String tipo = request.getParameter("txtTipo");
        
        String id = request.getParameter("txtId");

        veiculo.setId(Integer.parseInt(id));

        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setCor(cor);
        veiculo.setPlaca(placa);
        veiculo.setDimensaoVeiculo(dimensao);
        veiculo.setData_revisao(data_revisao);
        veiculo.setStatus(status);
        veiculo.setAno(ano);
        veiculo.setTipo(tipo);

        VeiculoDAO dao = new VeiculoDAO();

        veiculo = dao.alterarVeiculo(veiculo);

        

        request.setAttribute("veiculoAlterado", veiculo);
        RequestDispatcher rd = request.getRequestDispatcher("/logistica/manter_veiculo.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControleVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControleVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControleVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControleVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
