package com.company;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    private static final int SERVER_PORT = 6789;
    private static final String SUCCESS_MESSAGE = "Check your mailbox!\n";


    public static void main(String[] args) throws Exception {
        String clientData;
        String recipient;
        String pass;
        ServerSocket welcomeSocket = new ServerSocket(SERVER_PORT);
        int i=0;

        while(true) {

            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientData = inFromClient.readLine();

            if(clientData !=null) {
                recipient = clientData.split("@{3}")[0];
                pass = clientData.split("@{3}")[1];

                System.out.println("Client " + i + " connected...");

                GoogleMail.Send(recipient, pass);
                outToClient.writeBytes(SUCCESS_MESSAGE);
                System.out.println("Client " + i + " gone...");
                i++;
            }
        }
    }
}
