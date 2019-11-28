package authentication;

import java.util.HashMap;
import java.util.Map;

public class mainCredentials implements mainAuth{

    Map<String, String> credentials = new HashMap<>(); //TODO change to database input model

    public mainCredentials(String uid, String pwd) {
                 credentials.put(uid, pwd); //TODO Implement database update model
    }

    @Override
    public boolean check(String uid, String pwd) {
        return credentials.containsKey(uid) && credentials.get(uid).equals(pwd); //TODO Implement database check model
    }

}