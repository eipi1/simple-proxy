package net.funstuff.mdsarowar.proxy;


import net.funstuff.mdsarowar.common.Configurations;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by sarowar on 8/17/16.
 */
public class Server {

    private volatile boolean isServerUp;
    private Object lock = new Object();
    private ServerSocket serverSocket;
    private Configurations config;
    private ExecutorService connectionThreadPool;
    private Class<? extends Runnable> connectionHandler;

    private Server() {
        isServerUp = false;
    }

    public Server(Configurations config,ExecutorService threadPool,Class<? extends Runnable> connectionHandler) {
        this();
        this.config = config;
    }

    public void start() {
        if (!isServerUp) {
            synchronized (lock) {
                if (!isServerUp) {

                    try {
                        serverSocket = new ServerSocket(Integer.valueOf(config.getProperty("server.port")));
                        serverSocket.setReuseAddress(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return;
                    }
                    while (true) {
                        try {
                            Socket socket = serverSocket.accept();
                            Runnable connectionHandlerThread = connectionHandler.getDeclaredConstructor(Socket.class).newInstance(socket);
                            connectionThreadPool.submit(connectionHandlerThread);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        Server server = new Server();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//        threadPoolExecutor.
    }

}
