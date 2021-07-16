package org.academiadecodigo.shellmurais.chataplication.server.task;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.command.Command;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Represents our client inside the chat by it's connection to it
 */
public class ClientConnection implements Runnable{

    private String DEFAULT_NAME = "user";
    private String name;
    private Server server;
    private Socket clientSocket;
    private BufferedReader messageReader;
    private PrintWriter messageWriter;

    public ClientConnection(Socket clientSocket, Server server){
        this.name = DEFAULT_NAME;
        this.server = server;
        this.clientSocket = clientSocket;
    }

    /**
     * Starts the task (client connection): If the server can add this connection to it's list, it'll always listen to messages while the client socket is open
     *
     * @see ClientConnection#openStreams()
     * @see Server#addClient(ClientConnection)
     * @see ClientConnection#listenToMessages(BufferedReader)
     */
    public void start(){
        openStreams();

        send(Messages.WELCOME + "\n");
        send(Messages.SET_NAME + "\n");
        send(Messages.HELP + "\n");

        if(!server.addClient(this)){
            System.out.println(Messages.FULL_SERVER);
            close();
        }

        while(!clientSocket.isClosed()){

            listenToMessages(messageReader);
        }
    }

    @Override
    public void run() {
            start();
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    /**
     * Initialize the I/O streams
     */
    public void openStreams(){
        try {
            messageReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            messageWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sends the given message to the server
     *
     * @param message message to send
     */
    public void send(String message){
        messageWriter.println(message);
    }

    /**
     * closes the socket, and therefore the connection.
     */
    public void close(){
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listen to messages from the user and send's it to the Command class and it's respective handler
     *
     * @param messageReader
     */
    private void listenToMessages(BufferedReader messageReader) {
        try {
            while(!clientSocket.isClosed()) {
                String message = messageReader.readLine();

                Command.getFromString(message).getHandler().handle(server, this, message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
