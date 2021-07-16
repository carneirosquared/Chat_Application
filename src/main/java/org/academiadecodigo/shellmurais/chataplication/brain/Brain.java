package org.academiadecodigo.shellmurais.chataplication.brain;

import org.academiadecodigo.shellmurais.chataplication.client.Client;
import org.academiadecodigo.shellmurais.chataplication.menu.ChatMenu;
import org.academiadecodigo.shellmurais.chataplication.menu.LogInMenu;
import org.academiadecodigo.shellmurais.chataplication.menu.RegisterMenu;
import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;

/**
 * The brain of the chat aplication.
 * It handlles the client menu choices and redirect's it to Log In or Register and starts the client session in the chat itself.
 */
public class Brain {

    private RegisterMenu registerMenu;
    private ChatMenu menu;
    private LogInMenu logInMenu;
    private Client client;
    private int maxClients;
    private int port;

    public Brain(int maxClients, int port){
        this.port = port;
        this.maxClients = maxClients;
        registerMenu = new RegisterMenu();
        menu = new ChatMenu();
        logInMenu = new LogInMenu();
        client = new Client(port);

    }

    /**
     * Starts the Brain: It stores the user's menu choice.
     * if it's Log in it'll log in the client into the chat aplication.
     * If it's Register, it'll start the register meu and after registering it'll start the client.
     *
     * @see ChatMenu#start()
     * @see RegisterMenu#start()
     * @see Brain#startClient()
     */
    public void start(){
        String userPath = menu.start();

        if(userPath.equals("Log In")){
            logIn();
        }
        else if(userPath.equals("Register")){
            registerMenu.start();
            System.out.println("Registered with success. Welcome to the chat!");
            startClient();
        }

    }

    /**
     * Starts the client
     *
     * @see Client#start()
     */
    public void startClient() {
        client.start();
    }

    /**
     * If the LogInMenu check that it's given username and password match with the credentials stored in the .txt file, it'll star the client.
     *
     * @see LogInMenu#start()
     * @see Brain#startClient()
     */
    public void logIn(){
        if(logInMenu.start()){
            startClient();
        }
    }
}
