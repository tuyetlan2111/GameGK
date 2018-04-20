package com.example.lan.n14dcpt040_gamegk.Connect;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static android.content.ContentValues.TAG;

/**
 * Created by Lan on 4/19/2018.
 */

public class Client extends Thread{
    private Socket client;
    private int port;
    public static boolean serverStarted = false;
    private String ip="127.0.0.1";
    private DataInputStream dis;
    private DataOutputStream dos;


    public Client(int port) {
        this.port = port;
        try {
            client = new Socket(ip, port);
            dis = new DataInputStream(client.getInputStream());
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void run(){

        try {
            client = new Socket("127.0.0.1", port);

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
        dis.close();
        dos.close();
        client.close();
    } catch (IOException ex) {
        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    private void sendToServer(String sendMessage) {
        try {
            dos.writeUTF(sendMessage);
            dos.flush();
            System.out.println("Message sent to the server : "+sendMessage);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String receiveFromServer() {
        try {
            return dis.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
