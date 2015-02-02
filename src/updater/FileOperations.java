/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package updater;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Rish
 */
public class FileOperations {
    
    static ReadableByteChannel RBC;
    static FileOutputStream out;
    
    public static void downloadAndOverwriteFile(URL fileURL, String localfile) throws IOException {
        File destination = new File(localfile);
        if (destination.exists()) {
            if (destination.delete()) {
                downloadFile(destination.getPath(), fileURL);
            } else {
                System.out.println("Unable to delete original file.");
            }
        } else {
            System.out.println("Incomplete installation detected. Aborting.");
        }
    }
    
    private static void downloadFile(String localPath, URL fileURL) throws IOException { //Downloads a file. Pretty self-explanatory.
        RBC = Channels.newChannel(fileURL.openStream());
        File f = new File(localPath);
        f.mkdirs();
        f.createNewFile();
        Files.copy(fileURL.openStream(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
