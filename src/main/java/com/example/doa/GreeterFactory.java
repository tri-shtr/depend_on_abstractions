package com.example.doa;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.example.doa.greeter.Greeter;

public class GreeterFactory {
    private static Properties properties = new Properties();
    private static final String NATIONALITY = "nationality";

    public static Greeter getGreeter(String natinality) {
        Class<Greeter> greeter = null;
        Greeter instance = null;

        try {
            String fqcn = getFQCNFromProperties();
            greeter = (Class<Greeter>)Class.forName(fqcn);
            
            instance = greeter.getConstructor().newInstance();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e);
        }

        if(instance instanceof Greeter) {
            return instance;
        } else {
            throw new IllegalArgumentException("The class " + natinality + " does not implement Greet");
        }
    }

    private static String getFQCNFromProperties() throws IOException {
        String fqcn = null;

        try (InputStream input = GreeterFactory.class.getClassLoader().getResourceAsStream("greeter.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find greeter.properties");
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }

        fqcn = properties.getProperty(NATIONALITY);
        if (fqcn == null) {
            throw new IllegalArgumentException("No FQCN found for nationality: " + NATIONALITY);
        }
        return fqcn;
    }
}
