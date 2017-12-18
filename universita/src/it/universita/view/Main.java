package it.universita.view;

import it.universita.business.SessionManager;

/**
 * Created by roberto on 28/11/2017.
 */
public class Main {

    public static void main(String args[]) {

       LoginFrame loginFrame= new LoginFrame();SessionManager.getInstance().getSession().put("finestra_login", loginFrame);

        /*
        System.out.println("Ci sono!");

        ArrayList<String[]> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM studente");

        System.out.println("Numero di righe: " + risultato.size());

        for(int i=0;i<risultato.size();i++) {
            String[] riga = risultato.get(i);
            System.out.println("ID PERSONA: "+riga[0]);
            System.out.println("MATRICOLA: "+riga[1]);
            System.out.println("-------------------");
        }
        taglia tutto e lo mette nel loginlistener
        */

    }
}
