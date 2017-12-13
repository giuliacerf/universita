package it.universita.model;

public class Docente extends Persona{
    private int idPersona;
    private int fascia;

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getFascia() {
        return fascia;
    }

    public void setFascia(int fascia) {
        this.fascia = fascia;
    }
}
