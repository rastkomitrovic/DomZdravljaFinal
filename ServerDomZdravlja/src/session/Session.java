/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import server.Server;

/**
 *
 * @author Rastko
 */
public class Session {
    private static Server server;
    private static int port;
    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        Session.server = server;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Session.port = port;
    }
 
    
}
