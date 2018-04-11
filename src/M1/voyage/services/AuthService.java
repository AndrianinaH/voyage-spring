package M1.voyage.services;


import M1.voyage.models.Client;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.rmi.server.ExportException;
import java.util.List;

/**
 * Created by Andrianina_pc on 08/04/2018.
 */
@Component
public class AuthService extends BaseService {

    public Client login(Client user) throws Exception {

        List<Client> liste = (List<Client>) (Object) findAll(user);
        if(!liste.isEmpty()){
            return (Client)liste.get(0);
        }
        return null;
    }

    public boolean checkSession(HttpSession session) throws Exception
    {
        if(session.getAttribute("client_voyage") != null){
           return true;
        }
        return false;
    }

    public Client getSessionClient(HttpSession session) throws Exception
    {
        if(session.getAttribute("client_voyage") != null){
            return (Client)session.getAttribute("client_voyage");
        }
       return new Client();
    }
}
