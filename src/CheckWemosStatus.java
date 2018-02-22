import connection.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CheckWemosStatus extends Thread {

    private final long TIEMPODEESPERA=120;
    private Connection connection;
    private final String QUERY="update wemos set state=0 where (now()-lastMessage)>=120";
    //TO DO

    public CheckWemosStatus() {
    connection=new DBConnection().getConexion();
    }

    @Override
    public void run() {

        while(true){
            try {
                Statement statement= connection.createStatement();
                statement.executeUpdate(QUERY);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("hilo interrumpido");
            }
        }


    }

}
