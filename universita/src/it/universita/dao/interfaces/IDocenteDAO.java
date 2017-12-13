package it.universita.dao.interfaces;
import it.universita.model.Docente;

public interface IDocenteDAO extends IBaseDAO<Docente> {
    boolean setFascia(Docente d, int fascia);
}
