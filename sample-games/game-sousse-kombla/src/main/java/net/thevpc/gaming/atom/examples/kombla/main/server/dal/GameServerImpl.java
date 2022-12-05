package net.thevpc.gaming.atom.examples.kombla.main.server.dal;

import net.thevpc.gaming.atom.examples.kombla.main.client.dal.GameClient;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class GameServerImpl extends UnicastRemoteObject implements GameServer{
    MainServerDAOListener listener;

    List<GameClient> clientList = new LinkedList<>();
    protected GameServerImpl(MainServerDAOListener listener) throws RemoteException {
        this.listener = listener;
    }

    @Override
    public StartGameInfo connect(String name, GameClient gameClient) throws RemoteException {
        clientList.add(gameClient);
        return listener.onReceivePlayerJoined(name);
    }

    @Override
    public void moveLeft(int playerId) throws RemoteException {
        listener.onReceiveMoveLeft(playerId);
    }

    @Override
    public void moveRight(int playerId) throws RemoteException {
        listener.onReceiveMoveRight(playerId);
    }

    @Override
    public void moveUp(int playerId) throws RemoteException {
        listener.onReceiveMoveUp(playerId);
    }

    @Override
    public void moveDown(int playerId) throws RemoteException {
        listener.onReceiveMoveDown(playerId);
    }

    @Override
    public void fire(int playerId) throws RemoteException {
        listener.onReceiveReleaseBomb(playerId);
    }
}
