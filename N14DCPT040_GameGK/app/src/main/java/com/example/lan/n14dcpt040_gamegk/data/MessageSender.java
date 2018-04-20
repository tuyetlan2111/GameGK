package com.example.lan.n14dcpt040_gamegk.data;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Lan on 4/20/2018.
 */

public class MessageSender extends AsyncTask<Void, Void, Void> {
    Socket socket;
    public static int SERVER_PORT = 3000;
    private String ip="192.168.1.101";
    DataOutputStream dataOutputStream;
    PrintWriter printWriter;
    @Override
    protected Void doInBackground(Void... voids) {

        try {
            socket = new Socket(ip,SERVER_PORT);
            printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.flush();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
}
