/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicsentinel.services;

import musicsentinel.files.FileManager;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
import musicsentinel.C;

/**
 *
 * @author joaop
 */
public class MusicPlayerWorker {

    private static boolean songOn = false;
    
    public void playMusic(int m_seconds) {

        if (!songOn) {
            songOn = true;

            Runnable runable = new Runnable() {
                @Override
                public void run() {

                    try {
                        
                        FileManager mFM = new FileManager();
                        String filePath = mFM.getMusicFilePath();
                        URL resource;
                        if(filePath!=null){
                            try {
                                URI fileuri = new File(filePath).toURI();
                                resource = new URL(fileuri.toString());
                            } catch (MalformedURLException ex) {
                                String pathToMusic = C.MUSIC_DEFAULT_PATH;
                                resource = this.getClass().getResource(pathToMusic);
                                Logger.getLogger(MusicPlayerWorker.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else{
                            String pathToMusic = C.MUSIC_DEFAULT_PATH;
                            resource = this.getClass().getResource(pathToMusic);
                        }

                        System.out.println(resource.toURI().getPath());
                        System.out.println(resource.toString());

                        BasicPlayer player = new BasicPlayer();
                        try {
                            player.open(new URL(resource.toString()));
                            player.play();

                            Thread.sleep(m_seconds);

                            player.stop();

                        } catch (BasicPlayerException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException ex) {
                            Logger.getLogger(MusicPlayerWorker.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MusicPlayerWorker.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (URISyntaxException ex) {
                        Logger.getLogger(MusicPlayerWorker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    songOn = false;

                }
            };

            ScheduledExecutorService mExecutor = Executors.newScheduledThreadPool(1);
            mExecutor.submit(runable);

        }

    }
}
