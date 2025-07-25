package br.com.secretariaescolar.model;

public class Tramitacao {

    private int idTramitacao;
    private int idSolicitacao;
    private int idEtapa;
    private String dataHora;
    private String parecer;
    private String idFuncionario;

    // 🔥 Novos atributos adicionais para exibir no histórico
    private String nomeFuncionario;
    private String nomeEtapa;

    public Tramitacao() {}

    // 🔸 Getters e Setters padrões

    public int getIdTramitacao() {
        return idTramitacao;
    }

    public void setIdTramitacao(int idTramitacao) {
        this.idTramitacao = idTramitacao;
    }

    public int getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(int idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    // 🔸 Getters e Setters adicionais

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeEtapa() {
        return nomeEtapa;
    }

    public void setNomeEtapa(String nomeEtapa) {
        this.nomeEtapa = nomeEtapa;
    }
}
