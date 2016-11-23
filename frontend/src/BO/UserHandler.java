package BO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
public class UserHandler {

    public static boolean login(String user, String pass) {
        if (user.compareToIgnoreCase("carlos") == 0 &&
                pass.compareTo("carlos") == 0)
            return true;
        else
            return false;
    }
    public static Collection getUserNamesByName(String name) {
        ArrayList names = new ArrayList();
        Collection users = User.findByName(name);
        for (Iterator it = users.iterator(); it.hasNext();) {
            User user = (User) it.next();
            names.add(new String(user.getName()));
        }
        return names;
    }
}