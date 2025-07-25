package br.com.secretariaescolar.model;

public class Matricula {
    private String matriculaPK;
    private int idCurso;
    private String cpfAluno;

    public String getMatriculaPK() {
        return matriculaPK;
    }

    public void setMatriculaPK(String matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getCpfAluno() {
        return cpfAluno;
    }

    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }
}
