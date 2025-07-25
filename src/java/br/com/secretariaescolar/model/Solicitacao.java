package br.com.secretariaescolar.model;

public class Solicitacao {
    private int idSolicitacao;
    private String cpfAluno;
    private int idTipo;
    private String dataEntrada;
    private boolean status;
    private String idFuncionario;

    // 🔥 Atributos adicionais para exibir na interface
    private String nomeTipo;
    private String nomeFuncionario;
    private String nomeAluno;
    private String nomeCurso;

    public Solicitacao() {}

    public Solicitacao(String cpfAluno, int idTipo, String dataEntrada, boolean status, String idFuncionario) {
        this.cpfAluno = cpfAluno;
        this.idTipo = idTipo;
        this.dataEntrada = dataEntrada;
        this.status = status;
        this.idFuncionario = idFuncionario;
    }

    // Getters e Setters

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNomeTipo() {
        return nomeTipo;
    }

    public void setNomeTipo(String nomeTipo) {
        this.nomeTipo = nomeTipo;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}
