package org.academiadecodigo.shellmurais.chataplication.server.command;

import org.academiadecodigo.shellmurais.chataplication.server.Server;
import org.academiadecodigo.shellmurais.chataplication.server.task.ClientConnection;

public interface CommandHandler {

   void handle(Server server, ClientConnection sender, String message);
}
