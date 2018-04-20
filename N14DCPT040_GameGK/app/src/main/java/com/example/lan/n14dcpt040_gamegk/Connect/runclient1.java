package com.example.lan.n14dcpt040_gamegk.Connect;

/**
 * Created by Lan on 4/20/2018.
 */

public class runclient1 {
    public static final int port = 3000;

    public static void main(String[] args) {
        Client client = new Client(port);
        client.run();
    }
}
