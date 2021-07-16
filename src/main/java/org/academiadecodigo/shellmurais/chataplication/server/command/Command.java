package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

/**
 * Enumeration of all instances of Command in the chat aplication
 */
public enum Command {

    INVALID("", new InvalidHandler()),
    MESSAGE("", new MessageHandler()),
    QUIT("", new QuitHandler()),
    NAME("name", new NameHandler()),
    LIST("list", new ListHandler()),
    WHISPER("whisper", new WhisperHandler()),
    HELP ("help", new HelpHandler());



    private String command;
    private CommandHandler handler;

    Command(String command, CommandHandler handler) {
        this.command = "/" + command;
        this.handler = handler;
    }

    /**
     * Reads the client message and checks for hte presence of the command keyword.
     * If present, it's returns the command.
     *
     * @param message client's message to be read
     * @return Command present in the message
     * @return Invalid command if the command entered by the user ir's not supported by the chat application
     */
    public static Command getFromString(String message) {

        if (message == null) {
            return QUIT;
        }

        if (!message.startsWith("/")) {
            return MESSAGE;
        }

        String userCommand = message.split(" ")[0];

        for (Command command : values()) {
            if (userCommand.equals(command.command)) {
                return command;
            }
        }
        return INVALID;
    }

    public CommandHandler getHandler() {
        return handler;
    }

}
