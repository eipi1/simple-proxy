package net.funstuff.mdsarowar.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by sarowar on 8/17/16.
 */
public class Configurations extends Properties{
    private static String propertiesFile=System.getProperty("application.configfile");
    public Configurations(){
        super();
    }

    public void load() throws IOException {
        super.load(new FileInputStream(propertiesFile));
    }

    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        super.load(inStream);
    }
}
