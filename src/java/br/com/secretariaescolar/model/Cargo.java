package br.com.secretariaescolar.model;

public class Cargo {
    private int idCargo;
    private String nomeCargo;

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    // Métodos adicionais para compatibilidade
    public int getId() {
        return idCargo;
    }

    public String getNome() {
        return nomeCargo;
    }
}
