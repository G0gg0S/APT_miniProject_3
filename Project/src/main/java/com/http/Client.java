package com.http;

import java.net.*;
import java.io.*;
class Client{
    public static void main(String args[])throws Exception{
        Socket socket=new Socket("localhost",8080);
        DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(System.in));

        String clientMessage="";
        while(!clientMessage.equals("stop")){
            clientMessage=bufferedReader.readLine();
            dataOutputStream.writeUTF(clientMessage);
            dataOutputStream.flush();
        }

        dataOutputStream.close();
        socket.close();
    }}