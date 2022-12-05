package net.thevpc.gaming.atom.examples.kombla.main.client.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.dal.ProtocolConstants;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPMainClientDaoImpl implements MainClientDAO {
    private MainClientDAOListener listener;
    private AppConfig properties;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket;
    private StartGameInfo gameInfo;

    @Override
    public void start(MainClientDAOListener listener, AppConfig properties) {
        this.listener = listener;
        this.properties = properties;
    }
    @Override
    public StartGameInfo connect() {
        try {
            socket = new Socket(this.properties.getServerAddress(), this.properties.getServerPort());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.write(ProtocolConstants.CONNECT);
            objectOutputStream.writeUTF(properties.getPlayerName());
            gameInfo = (StartGameInfo) objectInputStream.readObject();
            new Thread(() -> waitForServer()).start();
            return gameInfo;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void waitForServer() {
        while (true) {
            try {
                DynamicGameModel model = (DynamicGameModel) objectInputStream.readObject();
                listener.onModelChanged(model);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void sendMoveLeft() {
        try {
            objectOutputStream.writeInt(ProtocolConstants.LEFT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveRight() {
        try {
            objectOutputStream.writeInt(ProtocolConstants.RIGHT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveUp() {

        try {
            objectOutputStream.writeInt(ProtocolConstants.UP);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendMoveDown() {

        try {
            objectOutputStream.writeInt(ProtocolConstants.DOWN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendFire() {

        try {
            objectOutputStream.writeInt(ProtocolConstants.FIRE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
