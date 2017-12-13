package it.universita.dao.mysql;

import it.universita.dao.interfaces.IPersonaDAO;
import it.universita.dbinterface.DbConnection;
import it.universita.model.Docente;
import it.universita.model.Persona;
import it.universita.model.Studente;

import java.util.ArrayList;

public class PersonaDAO implements IPersonaDAO {

    public static PersonaDAO getInstance() {
        if(instance == null)
            instance = new PersonaDAO();
        return instance;
    }

    private static PersonaDAO instance;



    @Override
    public Persona findUserByUsernameAndPassword(String username, byte[] password) {
        Persona p = null;

        // dobbiamo avere una query sulla tabella persona. chiamo la db connection perchè lei interagisce con il db
        String query = "SELECT * FROM persona WHERE " +
                "username = '"+username+"' AND " +  //metto gli 'apici' perchè username è un varchar
                "password = '"+new String(password)+"'"; //password è un array di byte quindi devo convertirlo in stringa perchè nel mio db un po' schifoso è una stringa
        ArrayList<String[]> ris = DbConnection.getInstance().eseguiQuery(query);

        if(ris.size() != 0) { //se nel dbms avessimo messo UQ su username sarebbe stato direttamente if(ris==1)
            //esiste un utente !!
            String[] utente = ris.get(0); //mi restituisce il codice dell'utente
            int id = Integer.parseInt(utente[0]);

            //vediamo se è un docente
            Docente d = DocenteDAO.getInstance().findById(id);
            if(d==null) {
                //allora sarà uno studente?...
                Studente s = StudenteDAO.getInstance().findById(id);
                if(s!=null) {
                    p=s;
                }
            }
            else {
                p=d;
            }
            if(p!=null) {
                p.setNome(utente[1]);
                p.setCognome(utente[2]);
            }

        }

        return p;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public ArrayList findAll() {
        return null;
    }
}
