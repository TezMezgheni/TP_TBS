package net.thevpc.gaming.atom.examples.kombla.main.server.dal;

import net.thevpc.gaming.atom.examples.kombla.main.shared.dal.ProtocolConstants;
import net.thevpc.gaming.atom.examples.kombla.main.shared.engine.AppConfig;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.DynamicGameModel;
import net.thevpc.gaming.atom.examples.kombla.main.shared.model.StartGameInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPMainServerDAOImpl implements MainServerDAO {
    private MainServerDAOListener listener;
    private AppConfig properties;

    class ClientSession {
        private int playerID;
        private Socket playerSocket;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;

//        public ClientSession(int playerID, Socket playerSocket, ObjectInputStream ois, ObjectOutputStream oos) {
//            this.playerID = playerID;
//            this.playerSocket = playerSocket;
//            this.ois = ois;
//            this.oos = oos;
//        }

    }

    private Map<Integer, ClientSession> playerToSocketMap = new HashMap<>();

    @Override
    public void start(MainServerDAOListener listener, AppConfig properties) {
        this.listener = listener;
        this.properties = properties;
        new Thread(() -> {

            try {
                ServerSocket ss = new ServerSocket(properties.getServerPort());
                while (true) {
                    Socket playerSocket = ss.accept();
                    new Thread(() -> {
                        processClient(playerSocket);
                    }).start();
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void processClient(Socket playerSocket) {
        try {
            ClientSession client = new ClientSession();
            client.playerSocket=playerSocket;
            client.ois = new ObjectInputStream(playerSocket.getInputStream());
            client.oos = new ObjectOutputStream(playerSocket.getOutputStream());
            client.ois.readInt();
            String name = client.ois.readUTF();
            StartGameInfo startGameInfo = listener.onReceivePlayerJoined(name);
            client.oos.writeObject(startGameInfo);
            client.oos.writeInt(ProtocolConstants.OK);
            this.playerToSocketMap.put(startGameInfo.getPlayerId(), client);
            while (true) {
                switch (client.ois.readInt()) {
                    case ProtocolConstants.UP:
                        listener.onReceiveMoveUp(client.playerID);
                        break;
                    case ProtocolConstants.DOWN:
                        listener.onReceiveMoveDown(client.playerID);
                        break;
                    case ProtocolConstants.LEFT:
                        listener.onReceiveMoveLeft(client.playerID);
                        break;
                    case ProtocolConstants.RIGHT:
                        listener.onReceiveMoveRight(client.playerID);
                        break;
                    case ProtocolConstants.FIRE:
                        listener.onReceiveReleaseBomb(client.playerID);
                        break;
                    default:
                        throw new AssertionError();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void sendModelChanged(DynamicGameModel dynamicGameModel) {
        for (Map.Entry<Integer, ClientSession> entry : this.playerToSocketMap.entrySet()) {
            try {
                entry.getValue().oos.writeObject(dynamicGameModel);
            } catch (IOException e) {
                Logger.getLogger(TCPMainServerDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

}
