package it.universita.business;

import it.universita.model.Persona;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class UtenteBusinessTest {

    //FIXTURE( insieme di variabili funzionali ai test. va resettata ad ogni esecuzione di test perchè i test potrebbero modificarli
    //per questo motivo esistono setup e teardown che vengono eseguiti rispettivamente prima e dopo
    String username;
    String password;
    String fakepassword;


    @Before
    public void setUp() throws Exception {
        username="puzzi.puzzino";
        password="puzzi";
        fakepassword="uihdoj";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getInstance() throws Exception {
        UtenteBusiness instance = UtenteBusiness.getInstance();
        assertNotNull(instance);
    }

    @Test
    public void login() throws Exception {
        Persona p1=UtenteBusiness.getInstance().login(username, password.getBytes());
        assertNotNull(p1);

        Persona p2=UtenteBusiness.getInstance().login(username, "isleghu".getBytes());
        assertNotNull(p2);

        assertTrue(p1!=p2);

    }

}