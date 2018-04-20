package com.example.lan.n14dcpt040_gamegk.Connect;

import android.app.Application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.in;

/**
 * Created by Lan on 4/19/2018.
 */

public class Server extends Thread{
    private ServerSocket serverSocket;
    private ArrayList<DataInputStream> disList;
    private ArrayList<DataOutputStream> dosList;
    public static int SERVER_PORT = 3000;
    private Thread thread;
    private ArrayList<Socket> listClient;


    public Server(int port) {
        this.SERVER_PORT = port;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        listClient = new ArrayList<Socket>();
        disList = new ArrayList<>();
        dosList = new ArrayList<>();

    }

    public void run() {
        String message;
        Socket socket = null;

            try {

                System.out.println("Starting socket thread...");
                socket = serverSocket.accept();
                socket = new Socket();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


                listClient.add(socket);
                disList.add(dis);
                dosList.add(dos);
                System.out.println("*" + dis.readUTF());



            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    private void sendToClient(int i, String msg) {
        try {
            dosList.get(i).writeUTF(msg);
            dosList.get(i).flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String receiveFromClient(int i) {
        try {
            String receive = disList.get(i).readUTF();
            return receive == null ? "" : receive;
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
