import connection.DBConnection;
import model.Message;
import model.Rele;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            socket = new DatagramSocket(80);

            System.out.println("Servidor escuchando");

        } catch (SocketException e) {
           servidorOk=false;
        }


        new CheckWemosStatus().start();

    while (servidorOk){

        try {
            // instancia el paquete
            receivePacket  = new DatagramPacket(new byte[17],17);
            //Aqui el servidor se queda a la escucha y si recibe algo lo mete en el paquete que le indiquemos
            socket.receive(receivePacket);



            String mensaje = new String( receivePacket.getData());

            System.out.println("Ha llegado una peticion \n");
            System.out.println("Procedente de :" +receivePacket.getAddress());
            System.out.println("El mensaje contiene: " + mensaje);
            System.out.println("Sirviendo la petición");
            updateLastMessageandState(mensaje);

            //Aqui iria la mac que envia el wemos
            Message msg =  getWemosStatus(mensaje);



          replyPacket = new DatagramPacket(msg.toString().getBytes(),msg.toString().getBytes().length,receivePacket.getAddress(),receivePacket.getPort());
           socket.send(replyPacket);





        } catch (IOException e) {
            System.out.println("Error IO");

        }

    }

    socket.close();




    }

    private static void updateLastMessageandState(String mac) {
        try {
            Connection connection =new DBConnection().getConexion();
            String query = "update wemos set lastMessage=(select now()) where mac = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString( 1 ,mac);
            preparedStatement.executeUpdate();
            query= "update wemos set state=1 where mac = ? and state=0";
             preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString( 1 ,mac);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static Message getWemosStatus(String mac) {
        Message message=new Message(mac);



        try {
            Connection connection =new DBConnection().getConexion();
            String query = "select * from rele where macWemos=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString( 1 ,mac);
            ResultSet result = preparedStatement.executeQuery();

            Rele rele;
           while (result.next()){
               rele=new Rele();
               rele.setId(result.getInt(1));
               rele.setName(result.getString(2));
               rele.setDescription(result.getString(3));
               rele.setState(result.getByte(4));
               rele.setMacWemos(result.getString(5));

               message.getReles().add(rele);

           }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    return message;
    }
}
