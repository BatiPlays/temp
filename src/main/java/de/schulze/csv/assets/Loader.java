package de.schulze.csv.assets;

import de.schulze.csv.assets.exceptions.FileExtensionException;

import java.io.*;
import java.util.ArrayList;

public class Loader
{
    BufferedReader br;
    InputStreamReader isr;
    FileInputStream fis;


    /**
     * Method to load File Data in String Array by File
     * @param file
     * @return
     * @throws IOException
     */
    public String[] loadFile(File file) throws IOException {

        if(!file.getAbsolutePath().endsWith(".csv")) throw new FileExtensionException(file.getAbsolutePath(), ".csv");

        init(file);

        ArrayList<String> list = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) list.add(line);

        String[] out = new String[list.size()];
        for(int i = 0; i < list.size(); i++) out[i] = list.get(i);

        return out;
    }

    /**
     * Method to load File Data in String Array by String
     * @param file
     * @return
     * @throws IOException
     */
    public String[] loadFile(String file) throws IOException {

        if(!file.endsWith(".csv")) throw new FileExtensionException(file, ".csv");

        init(new File(file));

        ArrayList<String> list = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null) list.add(line);

        String[] out = new String[list.size()];
        for(int i = 0; i < list.size(); i++) out[i] = list.get(i);

        return out;
    }

    /**
     * Internal Method to Init BufferedReader
     * @param file
     * @return
     * @throws IOException
     */
    private void init(File file) throws FileNotFoundException {
        fis = new FileInputStream(file);
        isr = new InputStreamReader(fis);
        br = new BufferedReader(isr);
    }
}
