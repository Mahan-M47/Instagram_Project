package Client.Controller;

import Client.Utils;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NetworkManager
{
    public static ClientJsonHandler CJH;

    private BlockingQueue<Response> queue;
    private Thread getThread, processThread;

    public NetworkManager(Socket socket) {
        queue = new ArrayBlockingQueue<>(Utils.BLOCKING_QUEUE_CAPACITY);
        CJH = new ClientJsonHandler(socket);
    }

    public void startClient()
    {
        Get get = new Get(queue);
        getThread = new Thread(get);

        ResponseProcessor reqProcessor = new ResponseProcessor(queue);
        processThread = new Thread(reqProcessor);

        getThread.start();
        processThread.start();
    }

    public void stopClient() {
        CJH.close();
        getThread.interrupt();
        processThread.interrupt();
    }
}
