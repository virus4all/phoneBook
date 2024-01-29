import Model.Dao.ContactDao;
import Model.Entity.Contact;

//si necessita della libreria: mysql.connector
//si necessita della libreria: lombok

public class Main
{
    public static void main(String[] args)
    {
        ContactDao.SeachAndCreateDb("phoneBook");
    }
}