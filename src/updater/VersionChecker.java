/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package updater;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Rish
 */
public class VersionChecker {

    static Properties p = new Properties();

    public static boolean checkVersion(String propfilepath, String propfilename) throws MalformedURLException, IOException {
        p.load(new FileInputStream(new File(propfilepath + propfilename)));
        int latestver = Integer.valueOf(new Scanner(new URL(Updater.LATEST_VERSION_URL).openStream(), "UTF-8").useDelimiter("\\A").next());
        int installedver = Integer.valueOf(p.getProperty("version"));
        // System.out.println(installedver + " installed, latest " + latestver);
        return installedver < latestver;
    }
    
    public static String getInstallPath(String propfilepath, String propfilename) throws FileNotFoundException, IOException {
        p.load(new FileInputStream(new File(propfilepath + propfilename)));
        return p.getProperty("installpath");
    }

}
