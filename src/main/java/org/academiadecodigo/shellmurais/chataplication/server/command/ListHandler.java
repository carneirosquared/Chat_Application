package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;

/**
 * Handler for the List command. Sends a list of all the current chat clients for all clients
 */
public class ListHandler implements CommandHandler {

    @Override
    public void handle(Server server, ClientConnection sender, String message) {
        sender.send(server.listClients());
    }
}
