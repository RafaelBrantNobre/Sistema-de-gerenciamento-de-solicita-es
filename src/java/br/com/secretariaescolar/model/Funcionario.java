package br.com.secretariaescolar.model;

public class Funcionario {
    private String idFuncionario;
    private String nome;
    private int idCargo;
    private String dataNascimento;
    private boolean statusConta;

    // Getters e Setters
    public String getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(String idFuncionario) { this.idFuncionario = idFuncionario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdCargo() { return idCargo; }
    public void setIdCargo(int idCargo) { this.idCargo = idCargo; }

    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }

    public boolean isStatusConta() { return statusConta; }
    public void setStatusConta(boolean statusConta) { this.statusConta = statusConta; }
}
