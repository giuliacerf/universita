package it.universita.business;

import java.util.HashMap;

public class SessionManager {
    //singleton
    private static SessionManager instance;

    private static HashMap<String, Object> session =new HashMap <String, Object>();

    public HashMap<String, Object> getSession() {
        return session;
    }

    public static SessionManager getInstance(){
        if(instance==null)
            instance = new SessionManager();
        return instance;
    }

}
