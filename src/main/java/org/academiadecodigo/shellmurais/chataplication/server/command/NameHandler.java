package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

/**
 * Handler for the name command. It checks for the name passed after the command in the given message and set's the client name with it.
 */
public class NameHandler implements CommandHandler {

    @Override
    public void handle(Server server, ClientConnection sender, String message) {

        if (!isValid(message)) {
            Command.INVALID.getHandler().handle(server, sender, Messages.NAME_USAGE);
            return;
        }

        String newName = message.split(" ")[1];

        if (server.getClientByName(newName) != null) {
            Command.INVALID.getHandler().handle(server, sender, Messages.NAME_IN_USE);
            return;
        }

        server.broadcastAll(sender.getName() +  Messages.RENAME + newName);
        sender.setName(newName);
    }

    private boolean isValid(String message) {
        return message.split(" ").length == 2;
    }
}
