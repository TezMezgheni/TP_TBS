package net.thevpc.gaming.atom.examples.kombla.main.server.dal;

import net.thevpc.gaming.atom.examples.kombla.main.client.dal.GameClient;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServerDAL implements MainServerDAO {

    private Registry ns;
    private GameServerImpl gameServer;

    @Override
    public void start(MainServerDAOListener listener, AppConfig properties) {
        try {
            gameServer = new GameServerImpl(listener);
            ns = LocateRegistry.getRegistry(properties.getServerAddress(), properties.getServerPort());
            ns.bind("gameServer",gameServer);

        } catch (RemoteException | AlreadyBoundException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void sendModelChanged(DynamicGameModel dynamicGameModel) {
        try {
            for (GameClient client:gameServer.clientList) {
                System.out.println(dynamicGameModel.toString());
                client.modelChanged(dynamicGameModel);
            }
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
