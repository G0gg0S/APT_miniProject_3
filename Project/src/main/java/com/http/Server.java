package com.http;

import com.mqtt.MqttPublisher;
import java.net.*;
import java.io.*;

class Server {
    public static void main(String args[]) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        String clientMessage = "";
        while (!clientMessage.equals("stop")) {
            clientMessage = dataInputStream.readUTF();
            System.out.println("client says: " + clientMessage);
            MqttPublisher.publishMessage(clientMessage);
        }
        dataInputStream.close();
        socket.close();
        serverSocket.close();
    }
}
