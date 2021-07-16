package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

/**
 * Handler for the Quit command. It closes the chat connection for it's user.
 */
public class QuitHandler implements CommandHandler {

    @Override
    public void handle(Server server, ClientConnection sender, String message) {
        server.removeClient(sender);
        server.broadcastAll(sender.getName() + " " + Messages.LOGOUT);
        sender.close();
    }
}
