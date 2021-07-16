package org.academiadecodigo.shellmurais.chataplication.utils;

public class Messages {

    public static final String NO_MESSAGE = "Couldn't get the message. Closing connection.";
    public static final String LOGOUT = "Left the chat.";
    public static final String CONNECTION_ERROR = "Couldn't establish connection.";
    public static final String JOIN = " joined the chat. Welcome!";
    public static final String WELCOME = "Welcome to the chat room!";
    public static final String FULL_SERVER = "There is no more room for new clients. Closing connection.";
    public static final String INVALID_COMMAND = "Command Invalid. Request ignored.";
    public static final String ERROR = "Error";
    public static final String RENAME = " changed it's name to: ";
    public static final String NAME_IN_USE = "Name already taken.";
    public static final String NAME_USAGE = "/name <new_username>";
    public static final String INVALID_USERNAME = "There is no user with that username in the chat";
    public static final String WHISPER_USAGE = "/whisper <destination_username> <message>";
    public static final String WHISPER = " (whisper) ";
    public static final String SET_NAME = "Please use the /name command to set your username.";
    public static final String LIST_USAGE = "/list (lists are the chat users usernames.";
    public static final String QUIT_USAGE = "/quit (logs you off the chat. closes the connection)";
    public static final String LINE_DIVIDER = "*******************************";
    public static final String REGISTER = " User credentials not registered. Please register before logging in.";
    public static final String COMMAND_LIST =  LINE_DIVIDER + "\n" + "Commands available:" + "\n" + NAME_USAGE + "\n"
            + WHISPER_USAGE + "\n" + LIST_USAGE + "\n" + QUIT_USAGE + "\n" + LINE_DIVIDER;
    public static final String HELP = "You can use the /help command to see all commands available.";
    public static final String WELCOME_NEW_USER = "Welcome! Please tell us what credentials to save.";
    public static final String NOT_REGISTERED = "Username not registered. Enter a registered username.";
    public static final String OUT_OF_TRIES = "Out of tries, See you later.";
    public static final String CLIENT_ACCEPTED = "Client connected to the server.";
}
