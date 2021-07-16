package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;

/**
 * Message handler. If the message doesn't contain any command it send it to all users except the sender.
 */
public class MessageHandler implements CommandHandler{

    @Override
    public void handle(Server server, ClientConnection sender, String message) {

        if (!isValid(message)) {
            return;
        }

        server.broadcast(sender.getName() + ": " + message, sender);
    }

    /**
     * Check is the message isn't empty
     *
     * @param message
     * @return true if the message isn't empyty
     */
    private boolean isValid(String message) {
        return !message.trim().isEmpty();
    }
}
