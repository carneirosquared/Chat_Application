package org.academiadecodigo.shellmurais.chataplication;

import org.academiadecodigo.shellmurais.chataplication.brain.Brain;

public class Application {

    public static void main(String[] args) {

        Brain brain = new Brain(30,5050);

        brain.start();
    }
}
