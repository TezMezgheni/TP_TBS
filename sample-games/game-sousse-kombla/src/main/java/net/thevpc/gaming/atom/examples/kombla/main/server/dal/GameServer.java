package net.thevpc.gaming.atom.examples.kombla.main.server.dal;
import net.thevpc.gaming.atom.examples.kombla.main.client.dal.GameClient;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameServer extends Remote {
    StartGameInfo connect(String name, GameClient gameClient) throws RemoteException;
    void moveLeft(int playerId) throws RemoteException;
    void moveRight(int playerId)throws RemoteException;
    void moveUp(int playerId)throws RemoteException;
    void moveDown(int playerId)throws RemoteException;
    void fire(int playerId)throws RemoteException;

    }