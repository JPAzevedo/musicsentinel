/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicsentinel.services;

import musicsentinel.MusicPlayerManager;
import musicsentinel.interfaces.ConsoleStatus;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaop
 */
public class TCPMusicClient {
    
    private final int M_SECONDS = 15*1000;
    
    private String ip = "";
    
    private String port = "";
    
    private Socket mSocket;
    
    private ScheduledExecutorService executor;

    private final int SOCKET_TIMEOUT = 5000;

    private DataOutputStream dataOutputStream;

    private DataInputStream dataInputStream;
    
    private String serverText;
 
    private Runnable mThread;    
   
    public TCPMusicClient(String ip, String port){
        this.ip = ip;
        this.port = port;
    }
   
    public void startMusic(){
        MusicPlayerWorker mPlayerWorker = new MusicPlayerWorker();
        mPlayerWorker.playMusic(M_SECONDS);
    }
    
    public void killService(){        
        if(mThread!=null&& executor!=null){
            executor.shutdownNow();
            closeCommunication();
        }
    }
    
    public void startService(ConsoleStatus listener){
       listener.updateconsoleMessage("Starting...", true);

        mThread = () -> {
            if(mSocket == null || (mSocket!= null && !mSocket.isConnected())){
                initClientServer(listener);
            }
            
            startCommunication(listener);
            
        };
        
       executor = Executors.newScheduledThreadPool(1);
       executor.submit(mThread);
        
    }

    private void initClientServer(ConsoleStatus listener){
        try {
            int portAddress = Integer.parseInt(port);
            
            mSocket = new Socket(); 
            mSocket.setSoTimeout(30000);
            mSocket.connect(new InetSocketAddress(ip,portAddress),SOCKET_TIMEOUT);
            
        } catch (IOException ex) {
            Logger.getLogger(TCPMusicClient.class.getName()).log(Level.SEVERE, null, ex);
            listener.updateconsoleMessage("Can't open client socket. Check your configurations. Then push the start button.", false);
            
        }
    }
    
    private void startCommunication(ConsoleStatus listener){
        if(mSocket.isConnected()){
            listener.updateconsoleMessage("Running...", true);

            try {
                dataInputStream = new DataInputStream(mSocket.getInputStream());
                dataOutputStream = new DataOutputStream(mSocket.getOutputStream());
                
                dataOutputStream.writeChars("***** Starting communnication    *****");
                long time = System.currentTimeMillis();
                        while (true) {
                            serverText = "";
                            while (dataInputStream.available() != 0) {
                                time = System.currentTimeMillis();
                                serverText = serverText.concat(String.valueOf((char) dataInputStream.read()));
                            }
                            if (serverText.length() > 0) {
                                System.out.println("Message: " + serverText + "Length: " + serverText.length());

                                if (serverText.toLowerCase().contains("moving")) {
                                    startMusic();
                                }
                            }
                            
                            if(System.currentTimeMillis()-time > 30000){
                                dataInputStream.read();
                            }
                            
                        }
                
            } catch (IOException ex) {
                Logger.getLogger(TCPMusicClient.class.getName()).log(Level.SEVERE, null, ex);
                listener.updateconsoleMessage("The socket was closed. Try it later. Then push the start button.", false);
                closeCommunication();
            }
        }
    }
    
    private void closeCommunication(){
        if(dataInputStream!=null ){
            try {
                dataInputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(TCPMusicClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(dataOutputStream!=null){
            try {
                dataOutputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(TCPMusicClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(mSocket!=null){
            try {
                mSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(TCPMusicClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
