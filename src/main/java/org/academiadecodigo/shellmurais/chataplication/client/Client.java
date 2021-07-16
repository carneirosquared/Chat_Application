package org.academiadecodigo.shellmurais.chataplication.client;

import org.academiadecodigo.shellmurais.chataplication.client.task.Write;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Representation of the chat client and it's function: To Read and Write messages.
 *
 * @see Write
 */
public class Client {

    private Socket socket;
    private InetAddress host;
    private int port;

    public Client(int port){
        init(port);
    }

    /**
     * Initializes the properties.
     *
     * @param port
     */
    private void init(int port) {
        this.port = port;
        try {
            this.host = InetAddress.getLocalHost();
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the Write task and gives it to a responsible Thread.
     * While the socket is bound ot the port, ill always listen for messages
     *
     * @see Client#listenToMessages(BufferedReader)
     */
    public void start(){

        ExecutorService thread = Executors.newSingleThreadExecutor();
        thread.submit(new Write(socket));

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(socket.isBound()){
                listenToMessages(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listens to messages that come from the server and writes them to the terminal for the client to see.
     *
     * @param reader
     */
    private void listenToMessages(BufferedReader reader) {

        try {
            String message = reader.readLine();

            if(message == null){
                System.out.println(Messages.NO_MESSAGE);
                System.exit(0);
            }

            System.out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
