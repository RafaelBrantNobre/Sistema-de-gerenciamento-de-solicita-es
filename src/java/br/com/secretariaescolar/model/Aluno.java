package br.com.secretariaescolar.model;

public class Aluno {
    private String cpf;
    private String nome;
    private String situacao;
    private boolean ativacaoConta;
    private String dataNascimento;

    // 🔧 Construtor vazio
    public Aluno() {}

    // 🔧 Construtor completo
    public Aluno(String cpf, String nome, String situacao, boolean ativacaoConta, String dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.situacao = situacao;
        this.ativacaoConta = ativacaoConta;
        this.dataNascimento = dataNascimento;
    }

    // ✅ Getters e Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public boolean isAtivacaoConta() {
        return ativacaoConta;
    }

    public void setAtivacaoConta(boolean ativacaoConta) {
        this.ativacaoConta = ativacaoConta;
    }


    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
