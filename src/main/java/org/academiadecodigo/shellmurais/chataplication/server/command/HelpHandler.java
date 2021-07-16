package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

/**
 *Handler for the help command. Sends all the commands list to the chat users.
 */
public class HelpHandler implements CommandHandler {
    @Override
    public void handle(Server server, ClientConnection sender, String message) {

        sender.send(Messages.COMMAND_LIST);

    }
}
