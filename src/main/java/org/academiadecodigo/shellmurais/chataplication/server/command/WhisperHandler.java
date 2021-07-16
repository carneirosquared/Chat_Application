package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

/**
 *Handler for the Whisper command. It checks for the client and message on the given message and send that message to that user only.
 */
public class WhisperHandler implements CommandHandler {

    @Override
    public void handle(Server server, ClientConnection sender, String message) {

        if (!isValid(message)) {
            Command.INVALID.getHandler().handle(server, sender, Messages.WHISPER_USAGE);
            return;
        }

        ClientConnection receiver = server.getClientByName(message.split(" ")[1]);

        if (receiver == null) {
            Command.INVALID.getHandler().handle(server, sender, Messages.INVALID_USERNAME);
            return;
        }

        message = removeWords(message, 2);
        receiver.send(sender.getName() + Messages.WHISPER + ": " + message);
    }

    private boolean isValid(String message) {
        return message.split(" ").length > 2;
    }

    private String removeWords(String phrase, int wordsToRemove) {
        while (wordsToRemove > 0) {
            phrase = phrase.substring(phrase.indexOf(' ') + 1);
            wordsToRemove--;
        }

        return phrase;
    }
}
