import connection.DBConnection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        //servidor
        DatagramSocket   socket=null;
        // paquetes
        DatagramPacket receivePacket;
        DatagramPacket replyPacket;
        boolean servidorOk=true;



        try {
            //inicio del servidor en el puerto X
            socket = new DatagramSocket(5000);

            System.out.println("Servidor escuchando");

        } catch (SocketException e) {
           servidorOk=false;
        }



    while (servidorOk){

        try {
            // instancia el paquete
            receivePacket  = new DatagramPacket(new byte[1024],1024);
            //Aqui el servidor se queda a la escucha y se recibe algo lo mete en el paquete que le indiquemos
            socket.receive(receivePacket);
            String mensaje = new String( receivePacket.getData());
            System.out.println("Ha llegado una peticion \n");
            System.out.println("Procedente de :" +receivePacket.getAddress());
            System.out.println("El mensaje contiene: " + mensaje);
            System.out.println("Sirviendo la petición");


            checkWemosStatus("");

            String msg="Respondo";

           replyPacket = new DatagramPacket(msg.getBytes(),msg.getBytes().length,receivePacket.getAddress(),receivePacket.getPort());
           socket.send(replyPacket);





        } catch (IOException e) {
            System.out.println("Error IO");

        }

    }

    socket.close();




    }

    private static void checkWemosStatus(String mac) {

        try {
            Connection connection =new DBConnection().getConexion();
            String query = "select * from rele where macWemos=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString( 1 ,mac);
           ResultSet result = preparedStatement.executeQuery();

           while (result.next()){

           }

        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
}
