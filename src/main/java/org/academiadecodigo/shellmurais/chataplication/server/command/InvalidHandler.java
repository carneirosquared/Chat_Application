package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

/**
 * Handler for an invalid command entered by the user
 */
public class InvalidHandler implements CommandHandler {

    public void handle(Server server, ClientConnection sender, String message) {
        sender.send(Messages.ERROR + ": " + (message.startsWith("/") ? Messages.INVALID_COMMAND : message));
    }
}
