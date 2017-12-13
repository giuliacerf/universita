package it.universita.dao.mysql;

import it.universita.dao.interfaces.IBaseDAO;
import it.universita.dbinterface.DbConnection;
import it.universita.model.Esame;
import it.universita.model.Studente;

import java.util.ArrayList;
import java.util.Iterator;


//Query che riguardano la tabella studente
//Classe che permette di raccogliere informazioni dal database e tutte le loro istanze
//supponiamo di voler cambiare tutte le prese in quest'aula: abbiamo 5 prese, abbiamo l'oggetto plafoniera istanziata 5 volte
//abbiamo una classe cacciaviti: mi serve una sola istanza
//quindi anche se ho tante istanze per una classe di modello mi basta una sola classe di dao (cacciavite). singleton design pattern
public class StudenteDAO implements IBaseDAO<Studente>{

    //obiettivo: avere una e una sola istanza cacciavite

    private static StudenteDAO instance; //variabile di tipo sè stesso (unica): contiene l'unica istanza di questa classe
    public static StudenteDAO getInstance() //permette di accedere all'istanza che altrimenti sarebbe privata e di inizializzarla per la prima volta
    {
        if (instance == null) //non è stata inizializzata
            instance = new StudenteDAO();
        return instance;
    }

    //5 righe di codice ricorrenti in dao, business e view


    //dobbiamo creare un find by Id o by matricola (restituisce UNO studente, ecco perchè il tipo è Studente)
    public Studente findById (int id)
    {
        //COMPITO 1: interagire con la DBconnection
        ArrayList<String[]> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM studente WHERE persona_idpersona="+id);

        //COMPITO 2: traduzione nel nostro modello (dobbiamo restituitre uno studente)
        //al più ce ne sarà 1 ma possono essere anche 0
        if(risultato.size() == 0) return null;
        Studente s = new Studente();
        String[] riga = risultato.get(0);
        s.setIdPersona(Integer.parseInt(riga[1]));
        s.setMatricola(riga[0]);

        ArrayList<Esame> esamiSostenuti = new ArrayList<Esame>();
        s.setEsamiSostenuti(esamiSostenuti);
        return s;
    }

    public ArrayList<Studente> findAll() //per avere la lista di tutti gli studenti; restituisce oggetti di modello
    //esegue le query e traduce in un array di stringhe da passare al model -> la classe Studente va nel model
        {
            //PRIMO COMPITO: interazione con la db connection



            ArrayList<String[]> risultato = DbConnection.getInstance().eseguiQuery("SELECT * FROM studente");
            //SECONDO COMPITO: traduzione

            ArrayList<Studente> listaStudenti = new ArrayList<Studente>();
            //dobbiamo tradurre gli oggetti restituiti dalla DBconnection in oggetti del nostro dominio

            Iterator<String[]> i = risultato.iterator(); //creiamo un iteratore a oggetti
            //iterator definisce un oggetto dello stesso tipo del contenuto dell'array list
            //iteriamo su ogni risultato e lo inseriamo in coda nella lista studenti

            while(i.hasNext()) //finchè c'è un nuovo elemento in questa lista ...
            {
                String[] riga = i.next(); //... dammelo
                Studente s = new Studente();
                //s.setIdPersona(riga[1]); //IdPersona è un intero e riga[1] un carattere: si deve convertire
                s.setIdPersona(Integer.parseInt(riga[1]));
                //il dbms mi assicura che idPersona è int e not null quindi posso usare questa funzione. altrimenti dobbiamo mettere
                //qualcosa tipo trackage(?)
                s.setMatricola(riga[0]);
                listaStudenti.add(s);
            }

            return listaStudenti;
        }
}