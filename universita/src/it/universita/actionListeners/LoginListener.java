package it.universita.actionListeners;

import it.universita.business.SessionManager;
import it.universita.business.UtenteBusiness;
import it.universita.model.Docente;
import it.universita.model.Persona;
import it.universita.model.Studente;
import it.universita.view.LoginFrame;
import it.universita.view.StudenteFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {

    private LoginFrame finestra;

    public LoginListener(LoginFrame finestra){
        this.finestra=finestra; //prendiamo come parametro tutta l'interfaccia grafica
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Evento catturato!");


    if("RANDOM_MENU_ITEM".equals(e.getActionCommand())) JOptionPane.showMessageDialog(null, "Hai premuto la voce di menu Random");
    if(e.getSource() instanceof JButton
            || e.getSource() instanceof JTextField
            || e.getSource() instanceof  JPasswordField) {      //è un po naive: potrebbero esserci diversi campi di testo ma non voglio che il login sia fatto da tutti!
                    //possiamo quindi definire degli Action command



        //String username = "mario.rossi";
        //byte[] password = "Passw0rd1".getBytes();

        String username= finestra.getTxtUsername().getText();
        byte[] password= new String(finestra.getTxtPassword().getPassword()).getBytes();

        System.out.println("username = "+username+".");
       // System.out.println("password = "+password+".");


        Persona p = UtenteBusiness.getInstance().login(username, password);

        if (p != null) {
            System.out.println("LOGIN OK!");

            if (p instanceof Docente) {
                //apriremo la view del docente
                System.out.println("Benvenuto docente " + p.getNome() + " " + p.getCognome() + "!");
                Docente d = (Docente) p;
                System.out.println("FASCIA: " + d.getFascia());
                JOptionPane.showMessageDialog(null,"Benvenuto docente " + p.getNome() + " " + p.getCognome() + "!");
            } else if (p instanceof Studente) {
                //apriremo la view dello studente
                System.out.println("Benvenuto studente " + p.getNome() + " " + p.getCognome() + "!");
                Studente s = (Studente) p;
                System.out.println("MATRICOLA: " + s.getMatricola());
                //JOptionPane.showMessageDialog(null,"Benvenuto studente " + p.getNome() + " " + p.getCognome() + "!");
                SessionManager.getInstance().getSession().put("studente", s);
                new StudenteFrame();
                finestra.setVisible(false);
                //se facciamo una put su una chiave che già esiste l'oggetto viene sostituito
            }

        } else {
            System.out.println("Il login non è andato a buon fine...");
            JOptionPane.showMessageDialog(null,"Il login non è andato a buon fine...");
        }




        /*
        System.out.println("Ci sono!");

        ArrayList<String[]> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM studente");

        System.out.println("Numero di righe: " + risultato.size());

        for(int i=0;i<risultato.size();i++) {
            String[] riga = risultato.get(i);
            System.out.println("ID PERSONA: "+riga[0]);
            System.out.println("MATRICOLA: "+riga[1]);
            System.out.println("-------------------");*/
        }
    }
}
