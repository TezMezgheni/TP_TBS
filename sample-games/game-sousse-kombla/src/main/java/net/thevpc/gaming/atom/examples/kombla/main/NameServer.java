package net.thevpc.gaming.atom.examples.kombla.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class NameServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1234);
            final Object verrou = new Object();
            synchronized (verrou){
                verrou.wait();
            }

        } catch (RemoteException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
