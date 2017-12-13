package it.universita.view;

import it.universita.business.SessionManager;
import it.universita.model.Studente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudenteFrame extends JFrame {

    //immaginiamo di voler mostrare in questa tabella tutti gli esami che lo studente ha sostenuto
    public StudenteFrame(){
        super("Finestra studente");
        getContentPane().setLayout(new BorderLayout());

        Studente s = (Studente)SessionManager.getInstance().getSession().get("studente");
        //getContentPane().add(new JLabel("area di lavoro"), BorderLayout.CENTER);

        //abbiamo praticamente una matrice 3x3 in cui dobbiamo inserire i dati
        //1 fase mettere i dati finti
        String[][] data = new String[3][3];
        data[0][0] = "a";
        data[0][1] = "b";
        data[0][2] = "c";
        data[1][0] = "d";
        data[1][1] = "e";
        data[1][2] = "f";
        data[2][0] = "g";
        data[2][1] = "h";
        data[2][2] = "i";

        //così però è brutto perchè non è a oggetti


        //2 fase mettere i nomi delle colonne
        String[] columnNames = new String[]{"nome", "email", "newsletter"};

        //3 fase definire il tablemodel. (gestisce il contenuto della tabell)
        //la gestione è affidata a tablemodel: può essere di diversi tipi, per ora utilizziamo quello di default
        //tm prende 2 parametri: i dati e l'array con il nome delle colonne
        //quando instanziamo la jtable passiamo il tm come argomento del costruttore. in questo modo Jtable saprà cosa disegnare

        TableModel tm = new DefaultTableModel(data, columnNames);
        JTable esamiSostenuti=new JTable();
        getContentPane().add(esamiSostenuti, BorderLayout.CENTER);


        getContentPane().add(new JLabel("Benvenuto "+s.getNome()+" "+s.getCognome()+" (matricola: "+s.getMatricola()+ ")"), BorderLayout.NORTH);
        JButton btnLogout = new JButton("Logout");
        getContentPane().add(btnLogout, BorderLayout.SOUTH);

        final StudenteFrame _this = this;
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SessionManager.getInstance().getSession().put("studente", null);
                _this.setVisible(false);
                LoginFrame finestraLogin = (LoginFrame)SessionManager.getInstance().getSession().get("finestra login");
                finestraLogin.setVisible(true);
            }
        });




        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024,768);
        setVisible(true);
    }
}
