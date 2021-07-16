package org.academiadecodigo.shellmurais.chataplication.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.PasswordInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

import java.io.*;

/**
 * Prompt menu that asks the user for credentials to use and saves then in the .txt file
 */
public class RegisterMenu {

    BufferedWriter writer;
    StringInputScanner usernameScanner;
    PasswordInputScanner passwordScanner;
    Prompt prompt;

    public RegisterMenu() {
        openFileStreams();
        openUserInputStreams();
    }

    /**
     * Initializes the user scanners
     */
    public void openUserInputStreams() {
        prompt = new Prompt(System.in, System.out);
        usernameScanner = new StringInputScanner();
        usernameScanner.setMessage("Username: ");
        passwordScanner = new PasswordInputScanner();
        passwordScanner.setMessage(("Password: "));
    }

    /**
     * Initializes the file/app scanners
     */
    public void openFileStreams() {
        try {
            writer = new BufferedWriter(new FileWriter("credentials.txt", true));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads user input and saves it to the .txt file
     */
    public void start() {
        System.out.println(Messages.LINE_DIVIDER);
        System.out.println(Messages.WELCOME_NEW_USER);
        String username = prompt.getUserInput(usernameScanner);
        String password = prompt.getUserInput(passwordScanner);

        String user = username + ": " + password;

        try {
            writer.write(user, 0, user.length());
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
