package Model.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//TODO: funzione che crea la tabella Contacts (contatti)
//TODO: funzione di iserimento di un contatto
//TODO: funzione di stampa dei contatti
//TODO: funzione di eliminzaione contatto
//TODO: funzione di modifica contatto

public class ContactDao
{
    //============================== FUN SeachAndCreateDb ==============================//
    //funzione che cerca se essite un determinato DB, se non lo trova lo crea, se lo trova non fa nulla
    public static void SeachAndCreateDb(String databaseName)
    {
        //Dati per accedere al DB, la stringa è più corta per poter vedere se sono presenti dei DB alla radice
        String urlDb = "jdbc:mysql://localhost:3306/";

        //Credenziali di accesso al DB
        String userDb = "root";
        String passwordDb = "1234";

        //variabile da controllare se esiste o meno il db
        Boolean databaseExist = false;

        //si fa una try della connessione al DB
        try (Connection con = DriverManager.getConnection(urlDb, userDb, passwordDb))
        {
            //controllo se esiste il db e metto dentro variabile databaseExist
            String qryVerifyDb = "SHOW DATABASES LIKE ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(qryVerifyDb))
            {
                preparedStatement.setString(1, databaseName);
                ResultSet res = preparedStatement.executeQuery();

                //se lo trova = true, se non trova = false
                databaseExist = res.next();
            }
            catch (Exception e)
            {
                System.out.println("ERRORE: nel cercare il DB");
            }

            // Verifica se il database esiste già
            if (databaseExist==false)
            {
                // Creazione del database se non esiste
                String qryCreateDb = "CREATE DATABASE " + databaseName;
                try (Statement statement = con.createStatement())
                {
                    statement.executeUpdate(qryCreateDb);
                }
                catch (Exception e)
                {
                    System.out.println("ERRORE: nel creare DB");
                }
            }
            //se esiste già non fa nulla
        }
        catch (SQLException e)
        {
            System.out.println("ERRORE: SeachAndCreateDB");
        }
    }
}
