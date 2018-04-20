package com.example.lan.n14dcpt040_gamegk.Connect;

/**
 * Created by Lan on 4/20/2018.
 */

public class serverrun {
    public static final int port = 3000;

    public static void main(String[] args) {
        Server server = new Server(port);
        server.run();

    }
}
