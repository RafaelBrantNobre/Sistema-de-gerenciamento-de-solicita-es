package br.com.secretariaescolar.model;

public class Usuario {
    private String id;           // CPF (se aluno) ou ID_Funcionario (se funcionário)
    private String nome;         // Nome do aluno ou do funcionário
    private String tipo;         // "aluno" ou "funcionario"
    private int nivelAcesso;     // 0 = aluno, 1 = estagiário, 2 = diploma/recepção, 3 = secretaria geral

    public Usuario() {
    }

    public Usuario(String id, String nome, String tipo, int nivelAcesso) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.nivelAcesso = nivelAcesso;
    }

    // 🔥 Métodos padrão (getters e setters)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    // 🔥 Método auxiliar para retornar nome do cargo
    public String getNomeCargo() {
        switch (nivelAcesso) {
            case 0:
                return "Aluno";
            case 1:
                return "Estagiário";
            case 2:
                return "Diploma/Recepção";
            case 3:
                return "Secretaria Geral";
            default:
                return "Desconhecido";
        }
    }
}
