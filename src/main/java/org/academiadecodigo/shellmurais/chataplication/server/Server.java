package org.academiadecodigo.shellmurais.chataplication.server;

import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server aplication for the chat. The server handles all communications from the users as well as the commands given by them
 */
public class Server {

    private int port;
    private ServerSocket serverSocket;
    private BufferedReader scanner;
    private CopyOnWriteArrayList<ClientConnection> clientsList;
    private int maxClients;
    private Socket clientSocket;

    public Server(int maxClients, int port){
        init(maxClients, port);
    }

    /**
     * Initializes the server socket and the maxClients and port properties with the given maxClients and port
     *
     * @see Server#init(int, int)
     * @param maxClients
     * @param port
     */
    public void init(int maxClients, int port){
        try {
            openStreams();
            //getPort();
            clientsList = new CopyOnWriteArrayList<>();
            this.maxClients = maxClients;
            this.port = port;
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the scanner
     */
    public void openStreams(){
        scanner = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Starts the server. While the socket is bound to the port it'll wait for user connections.
     *
     * @see Server#waitForConnections()
     */
    public void start(){

        while (serverSocket.isBound()){

            waitForConnections();
        }
    }

    /**
     *Accepts any connection attempted by the users, creates a client connection and handles it to a Thread.
     *
     * @return client connection and it's responsible Thread
     */
    public ClientConnection waitForConnections() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println(Messages.CLIENT_ACCEPTED);
            ClientConnection clientConnection = new ClientConnection(clientSocket, this);

            ExecutorService threadPool = Executors.newCachedThreadPool();
            threadPool.submit(clientConnection);
            return clientConnection;

        } catch (IOException e) {
            System.out.println(Messages.CONNECTION_ERROR + e.getMessage());
        }
        return null;
    }

    /**
     *Adds the client connection (user) to the server's client list.
     *
     * @param client
     * @return true if user is added to the server list successfully and false if it's already full.
     */
    public synchronized boolean addClient(ClientConnection client){

        if(clientsList.size() == maxClients){
            return false;
        }
        broadcastAll(client.getName() + Messages.JOIN);
        clientsList.add(client);
        return true;
    }

    /**
     * Sends the given message to all the clients except the one who sent it.
     *
     * @param message
     * @param clientConnection
     */
    public void broadcast(String message, ClientConnection clientConnection) {
        for(ClientConnection client : clientsList){
            if(!client.equals(clientConnection))
            client.send(message);
        }
    }

    /**
     * Sends the given message to all clients without exception.
     *
     * @param message
     */
    public void broadcastAll(String message){
        for(ClientConnection client : clientsList){
            client.send(message);
        }
    }

    /**
     * Removes the given client from the server's client list.
     *
     * @param client
     */
    public void removeClient(ClientConnection client){
        clientsList.remove(client);
    }

    /**
     * Searches the server's client list for the client with the given name and returns it.
     *
     * @param name
     * @return client with the given name
     */
    public ClientConnection getClientByName(String name) {
        for( ClientConnection client : clientsList){
            if(client.getName().equals(name)){
                return client;
            }
        }
        return null;
    }

    /**
     * Lists all the current server users.
     *
     * @return user details in a string.
     */
    public String listClients(){
        StringBuilder builder = new StringBuilder("Clients connected:\n");

        for(ClientConnection client : clientsList){
            builder.append("-" + client.getName() + "\n");
        }

       return builder.toString();
    }

}


