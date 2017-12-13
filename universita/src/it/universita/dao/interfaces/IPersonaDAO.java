package it.universita.dao.interfaces;

import it.universita.model.Persona;

public interface IPersonaDAO extends IBaseDAO {
    Persona findUserByUsernameAndPassword(String username, byte[] password);
}
