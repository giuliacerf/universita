package it.universita.model;

import java.util.ArrayList;

public class Studente extends Persona{
    private int idPersona;
    private String matricola;
    private ArrayList<Esame> esamiSostenuti;

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public ArrayList<Esame> getEsamiSostenuti() {
        return esamiSostenuti;
    }

    public void setEsamiSostenuti(ArrayList<Esame> esamiSostenuti) {
        this.esamiSostenuti = esamiSostenuti;
    }

}
