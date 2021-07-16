package org.academiadecodigo.shellmurais.chataplication;

import org.academiadecodigo.shellmurais.chataplication.server.Server;

public class ServerLauncher {

    public static void main(String[] args) {

        Server server = new Server(30, 5050);

        server.start();
    }
}
