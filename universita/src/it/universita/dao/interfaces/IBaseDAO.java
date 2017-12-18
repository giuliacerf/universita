package it.universita.dao.interfaces;
import java.util.ArrayList;

//metodi che tutte le classi dao avranno: li mettiamo a fattor comune

public interface IBaseDAO<T> { //T rappresenta qualsiasi entità del dominio (docente, studente, esame, corso, ...).T sta per Type
    T findById(int id);
    ArrayList<T> findAll();
}