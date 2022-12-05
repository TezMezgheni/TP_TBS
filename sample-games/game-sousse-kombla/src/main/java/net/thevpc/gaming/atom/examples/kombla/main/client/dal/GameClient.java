package net.thevpc.gaming.atom.examples.kombla.main.client.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameClient extends Remote {
     void modelChanged(DynamicGameModel dynamicGameModel) throws RemoteException;

     }
