package org.academiadecodigo.shellmurais.chataplication.menu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.PasswordInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.shellmurais.chataplication.utils.Messages;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

/**
 * A prompt menu that authenticate and redirects the user
 */
public class LogInMenu {

    Prompt prompt;
    HashMap<String, String> userDetails;
    Set<String> usernameChoices;
    StringSetInputScanner username;
    PasswordInputScanner password;


    public LogInMenu() {
        prompt = new Prompt(System.in, System.out);
        userDetails = new HashMap<>();
        addUserDetails(userDetails);
        usernameChoices = userDetails.keySet();
    }

    /**
     * Verifies if the given username is stored in the .txt file and if hte given password matches it's stored password
     *
     * @see LogInMenu#checkIfPasswordMatch(String, String)
     *
     * @return true if credentials are correct
     */
    public boolean start() {
        int tries = 0;
        openUserInputStreams();

        String userInputName = prompt.getUserInput(username);
        String userInputPassword = prompt.getUserInput(password);

        if (!userDetails.containsKey(userInputName)) {
            System.out.println(Messages.REGISTER);
            tries++;
        }
        if(tries > 3){
            return false;
        }

        return checkIfPasswordMatch(userInputName, userInputPassword);
    }

    /**
     * Adds the user details (credentials) to the .txt file for future access
     *
     * @param userDetails
     */
    public void addUserDetails(HashMap<String, String> userDetails) {
        try {
            BufferedReader credentialsReader = new BufferedReader(new FileReader("credentials.txt"));
            String line;
            String result = "";

            while (((line = credentialsReader.readLine()) != null)) {
                result += line + ": ";
            }

            String[] words = result.split(": ");

            String[] names = new String[words.length / 2];
            String[] passwords = new String[words.length / 2];
            int j = 0;

            for (int i = 0; i < names.length; i++) {
                names[i] = words[j];
                j += 2;
            }

            j = 1;

            for (int i = 0; i < passwords.length; i++) {
                passwords[i] = words[j];
                j+=2;
            }

            for (int i = 0; i < names.length; i++) {
                userDetails.put(names[i], passwords[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the scanners
     */
    public void openUserInputStreams() {
        username = new StringSetInputScanner(usernameChoices);
        username.setMessage("Username: ");
        username.setError(Messages.NOT_REGISTERED);

        password = new PasswordInputScanner();
        password.setMessage("Password: ");
    }

    /**
     * Verifies the given password for the given username
     *
     * @param name
     * @param credential
     * @return true if password matches the given username's stored password
     */
    public boolean checkIfPasswordMatch(String name, String credential) {
        int tries = 3;

        while (!userDetails.get(name).equals(credential) && tries > 0) {

            System.out.println("Invalid Password. You have " + tries + " more tries.");
            credential = prompt.getUserInput(password);
            tries--;

            if (tries == 0) {
                System.out.println(Messages.OUT_OF_TRIES);
                return false;
            }
        }

        System.out.println("Login Successful! Welcome " + name + ".");
        return true;

    }
}
