package org.academiadecodigo.shellmurais.chataplication.client.task;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Client's task to perform simultaneously with listening ot messages
 */
public class Write implements Runnable{

    private final String QUIT = "/quit";
    private Socket socket;
    private Server server;
    private BufferedReader userInputScanner;
    private PrintWriter messagePrinter;

    public Write(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        listen();
    }

    /**
     * Listens to the terminal and writes to the server the read messages.
     *
     * @see Write#openStreams()
     */
    public void listen(){
        try {
            openStreams();

            while(socket.isBound()){
                String input = userInputScanner.readLine();

                if(input.equals(QUIT)){
                    messagePrinter.println(Messages.LOGOUT);
                    System.exit(0);
                }
                messagePrinter.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the I/O streams
     *
     * @throws IOException
     */
    public void openStreams() throws IOException {
        userInputScanner = new BufferedReader(new InputStreamReader(System.in));
        messagePrinter = new PrintWriter(socket.getOutputStream(),true);
    }

}
