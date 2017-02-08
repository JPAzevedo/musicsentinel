/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicsentinel.files;

import musicsentinel.C;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaop
 */
public class FileManager {
    
   
    public FileManager(){
    }
    
    private static final char dot = 0x2E;
    
    public static void copyFile(File sourceFile, File destFile) throws IOException {

        if(!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        }
        finally {
            if(source != null) {
                source.close();
            }
            if(destination != null) {
                destination.close();
            }
        }
    }
    
    public static String getFileExtension(String path){
        char[] pathchar = path.toCharArray();
        ArrayList<Character> ext_inv = new ArrayList<>();
        int stopper = 0; 
        
        for(int k=(pathchar.length-1);stopper==0;k--){
            if(pathchar[k]==dot){
                stopper=1;
                break;
            } 
            ext_inv.add(pathchar[k]);
        }
        
        String ext="";
        int x = ext_inv.size()-1;
        while(x!=-1){
            ext = ext+((char)ext_inv.get(x));
            x--;
        }
        
        return ext;        
    }
    
    public String getMusicFilePath(){
        String path = C.FILE_PATH+C.MUSIC_FILE_NAME+C.MUSIC_EXTENSION;
    
        File mFile = new File(path);
        if(mFile.exists()){
            return path;
        }
       
        return null;
    }
    
    public static void saveTextFile(String fileName,String content){
    
        File filePath = new File(C.FILE_PATH);
        if(!filePath.exists()){
            createPath(filePath);
        }
            
        String path = C.FILE_PATH+fileName+C.TEXT_EXTENSION;
        File mFile = new File(path); 
        if(mFile.exists() && content!=null){
            try {
                Files.delete(Paths.get(path));
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
     
        try {
            Files.write(Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void createPath(File filePath){
        filePath.mkdirs();
    }
    
    public static String getTextFileContent(String fileName){
        try {
            String path = C.FILE_PATH+fileName+C.TEXT_EXTENSION;
            File mTextFile = new File(path);
            
            if(!mTextFile.exists()){
                return null;
            }
            
            byte [] content = Files.readAllBytes(Paths.get(path));
            return new String(content);
            
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
