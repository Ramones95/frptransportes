/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pauli
 */
public class Veiculo {

    private int id;
    private String marca;
    private String modelo;
    private String cor;
    private String placa;
    private String dimensaoVeiculo;
    private String data_revisao;
    private String status;
    private String ano;
    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getDimensaoVeiculo() {
        return dimensaoVeiculo;
    }

    public void setDimensaoVeiculo(String dimensaoVeiculo) {
        this.dimensaoVeiculo = dimensaoVeiculo;
    }

    public String getData_revisao() {
        return data_revisao;
    }

    public void setData_revisao(String data_revisao) {
        this.data_revisao = data_revisao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String classificaTipoVeiculo(Pacote pacote) {
        String tP = pacote.getTipo();

        if (tP == "A" || tP == "a") {
            this.tipo = "A";

        } else if (tP == "B" || tP == "b") {
            this.tipo = "B";

        } else if (tP == "C" || tP == "c") {
            this.tipo = "C";

        } else if (tP == "D" || tP == "d") {
            this.tipo = "D";
        }

        return this.tipo;
    }

}
