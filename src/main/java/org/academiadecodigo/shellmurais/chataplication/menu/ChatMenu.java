package org.academiadecodigo.shellmurais.chataplication.menu;

import com.sun.xml.internal.ws.addressing.v200408.MemberSubmissionWsaServerTube;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

/**
 * Prompt menu that redirect user to Log In Menu or Register Menu
 * 
 * @see LogInMenu 
 * @see RegisterMenu
 */
public class ChatMenu {

    private Prompt prompt;
    String[] options;
    private MenuInputScanner scanner;

    /**
     * Reads user input and returns it's choice
     * 
     * @return user choice
     */
    public String start(){
        init();
        scanner.setMessage(Messages.WELCOME);
        int answerIndex = prompt.getUserInput(scanner);
        return options[answerIndex - 1];
    }

    /**
     * Open streams and adds options for the user to choose
     * 
     * @see ChatMenu#addOptions(String, String)
     */
    public void init(){
            prompt = new Prompt(System.in, System.out);
            options = new String[2];
            addOptions("Log In","Register");
            scanner = new MenuInputScanner(options);
    }

    public void addOptions(String option1 , String option2){
        options[0] = option1;
        options[1] = option2;
    }

}
