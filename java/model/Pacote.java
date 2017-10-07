/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Ramon Cordeiro
 */
public class Pacote {

    private int id;
    private double comprimento;
    private double altura;
    private double largura;
    private String tipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getComprimento() {
        return comprimento;
    }

    public void setComprimento(double comprimento) {
        this.comprimento = comprimento;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getLargura() {
        return largura;
    }

    public void setLargura(double largura) {
        this.largura = largura;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String classificaPacote(Pacote pacote) {

        double altura = pacote.getAltura();
        double largura = pacote.getLargura();
        double comprimento = pacote.getComprimento();

        double m = altura * largura * comprimento;

        if (m <= 5) {
            this.tipo = "A";
        } else if (m > 5 && m <= 10) {
            this.tipo = "B";
        } else if (m > 10 && m <= 20) {
            this.tipo = "C";
        } else if (m > 20) {   //colocar limite 
            this.tipo = "D";
        }

        return this.tipo;
    }

}
