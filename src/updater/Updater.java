/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rish
 */
public class Updater {

    /**
     */
    public static final String SOFTWARE_VERSION_PATH = System.getProperty("user.home") + System.getProperty("file.separator") + ".schoolchecker" + System.getProperty("file.separator");
    public static final String SOFTWARE_VERSION_PROPERTIES_FILE = "persistence.properties";
    public static final String LATEST_VERSION_URL = "http://rishshadra.me/schoolchecker/updater/version.txt";
    public static final String LATEST_PROGRAM_URL = "http://rishshadra.me/schoolchecker/SchoolChecker.jar";

    /**
     *
     * @param args
     * @throws IOException
     */
    
    public static void main(String[] args) throws IOException {
        
        InfoWindow.main(new String[0]);
        
        if (VersionChecker.checkVersion(SOFTWARE_VERSION_PATH, SOFTWARE_VERSION_PROPERTIES_FILE)) {
            FileOperations.downloadAndOverwriteFile(new URL(LATEST_PROGRAM_URL), VersionChecker.getInstallPath(SOFTWARE_VERSION_PATH, SOFTWARE_VERSION_PROPERTIES_FILE));
            Runtime.getRuntime().exec("java -jar " + VersionChecker.getInstallPath(SOFTWARE_VERSION_PATH, SOFTWARE_VERSION_PROPERTIES_FILE));
        }
        try {
            Thread.sleep(2500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Updater.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }
    
}
