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
public class Usuario {

    private int id;
    private String nome;
    private String login;
    private String senha;
    private PerfilDeAcesso perfil;
    private String cnh;

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilDeAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilDeAcesso perfil) {
        this.perfil = perfil;
    }

    public String classificaTipoMotorista(Veiculo veiculo) {

        String tpVeic = veiculo.getTipo();

        if (tpVeic == "A") {
            this.cnh = "A";

        } else if (tpVeic == "B" || tpVeic == "b") {
            this.cnh = "B";

        } else if (tpVeic == "C" || tpVeic == "c") {
            this.cnh = "C";

        } else if (tpVeic == "D" || tpVeic == "d") {
            this.cnh = "D";
        } else if (tpVeic == "E" || tpVeic == "e") {
            this.cnh = "E";

        }
        return this.cnh;
    }

}
