package net.thevpc.gaming.atom.examples.kombla.main.client.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameClientImpl extends UnicastRemoteObject implements GameClient {
    MainClientDAOListener listener;
    public GameClientImpl(MainClientDAOListener listener) throws RemoteException {
        this.listener=listener;
    }

    @Override
    public void modelChanged(DynamicGameModel dynamicGameModel) throws RemoteException {
        listener.onModelChanged(dynamicGameModel);
    }
}
