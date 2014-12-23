package se.eneroth.travv75;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Rickard on 2014-12-23.
 */
public class TravV75 {
    public static void main(String args[]) throws Exception{
        System.out.println("Hello V75!");
        TravV75 t = new TravV75();
        t.generateRandomV75();
    }

    public void generateRandomV75() throws Exception {
        System.out.println("Generating V75!");
        Properties prop = new Properties();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("travv75.properties");
        prop.load(stream);
        System.out.println("Lopp1=" + prop.getProperty("Lopp1"));
        System.out.println("Lopp2=" + prop.getProperty("Lopp2"));
        System.out.println("Lopp3=" + prop.getProperty("Lopp3"));
        System.out.println("Lopp4=" + prop.getProperty("Lopp4"));
        System.out.println("Lopp5=" + prop.getProperty("Lopp5"));
        System.out.println("Lopp6=" + prop.getProperty("Lopp6"));
        System.out.println("Lopp7=" + prop.getProperty("Lopp7"));
    }
}
