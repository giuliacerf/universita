package it.universita.dao.mysql;

/*non possiamo mettere a fattor comune il corpo dei metodi ma solo le segnature. può essere una classe astratta o un'interfaccia
classi astratte: alcuni metodi possono essere definiti senza corpo e delegati ai componenti. le interfacce non sono metodi ma contengono solo le segnature
in generale non si fanno classi astratte base dao->tipicamente la base dao è un'interfaccia(ci semplificano la vita nella migrazione)
lavorare per interfacce permetterà una maggiore evolvibilità del software

*/

import it.universita.dao.interfaces.IDocenteDAO;
import it.universita.dbinterface.DbConnection;
import it.universita.model.Docente;

import java.util.ArrayList;
import java.util.Iterator;

public class DocenteDAO implements IDocenteDAO{

    private static DocenteDAO instance;

    public static DocenteDAO getInstance() {
        if(instance == null)
            instance = new DocenteDAO();
        return instance;
    }

    public boolean setFascia(Docente d, int fascia) {

        return DbConnection.getInstance().eseguiAggiornamento("UPDATE docente SET fascia = "+fascia+" " +
                "WHERE persona_idpersona = "+d.getIdPersona());
    }

    public Docente findById(int id) {
        ArrayList<String[]> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM docente WHERE persona_idpersona="+id);

        if(risultato.size() == 0) return null;

        Docente d = new Docente();

        String[] riga = risultato.get(0);
        d.setIdPersona(Integer.parseInt(riga[0]));
        d.setFascia(Integer.parseInt(riga[1]));

        return d;

    }

    public ArrayList<Docente> findAll() {
        ArrayList<String[]> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM docente");

        ArrayList<Docente> listaDocent = new ArrayList<Docente>();

        Iterator<String[]> i = risultato.iterator();

        while(i.hasNext()) {
            String[] riga = i.next();
            Docente d = new Docente();
            d.setIdPersona(Integer.parseInt(riga[0]));
            d.setFascia(Integer.parseInt(riga[1]));
            listaDocent.add(d);
        }

        return listaDocent;

    }

}


