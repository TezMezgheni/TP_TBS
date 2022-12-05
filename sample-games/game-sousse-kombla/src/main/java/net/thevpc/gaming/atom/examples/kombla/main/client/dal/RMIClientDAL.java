package net.thevpc.gaming.atom.examples.kombla.main.client.dal;

import net.thevpc.gaming.atom.examples.kombla.main.server.dal.GameServer;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClientDAL implements MainClientDAO {
    private MainClientDAOListener listener;
    private AppConfig properties;
    private GameServer rs;
    StartGameInfo gameInfo;

    private Registry ns;

    @Override
    public void start(MainClientDAOListener listener, AppConfig properties) {

        try {
            ns = LocateRegistry.getRegistry(properties.getServerAddress(), properties.getServerPort());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        this.listener = listener;
        this.properties = properties;
    }

    @Override
    public StartGameInfo connect() {
        try {

            rs = (GameServer) ns.lookup("gameServer");
            gameInfo = rs.connect(properties.getPlayerName(), new GameClientImpl(listener));
        } catch (RemoteException | NotBoundException e) {
            throw new RuntimeException(e);
        }
        return gameInfo;
    }

    @Override
    public void sendMoveLeft() {
        try {
            rs.moveLeft(gameInfo.getPlayerId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveRight() {
        try {
            rs.moveRight(gameInfo.getPlayerId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveUp() {
        try {
            rs.moveUp(gameInfo.getPlayerId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveDown() {
        try {
            rs.moveDown(gameInfo.getPlayerId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void sendFire() {
        try {
            rs.fire(gameInfo.getPlayerId());
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
