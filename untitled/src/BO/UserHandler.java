package BO;

import DB.DB_User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Teddy on 2016-11-21.
 */
public class UserHandler {
    public static boolean login(String user, String pass)
    {
        if(user.compareToIgnoreCase("reine") == 0 &&
                pass.compareToIgnoreCase("tjo") == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static DB_User getUsernamesByName(String name)
    {
        return DB_User.findByName(name);
    }
}
