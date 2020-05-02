package de.schulze.csv.assets;

import de.schulze.csv.assets.exceptions.CSVFormatException;

import java.io.IOException;

public class Decoder
{
    Assets assets = new Assets();

    /**
     * Split CSV data into 2D-String-Array
     * @param data
     * @param deliverer
     * @return
     * @throws IOException
     */
    public String[][] decode(String[] data, boolean deliverer) throws IOException {
        if(deliverer) return decodeDeliverer(data);
        return decodeItem(data);
    }

    /**
     * Split Lieferant.csv into 2D-String-Array and test Syntax
     * @param data
     * @return
     * @throws IOException
     */
    private String[][] decodeDeliverer(String[] data) throws IOException
    {
        String[] temp;
        String[][] out = null;
        for(int i = 0; i < data.length; i++)
        {
            temp = assets.stringSplitter(data[i], ',', '"');
            if(out == null) out = new String[data.length][temp.length];

            if(temp.length != 5) throw new IOException("Parameter Count in 'Deliverer' CSV File isn't '5'");

            for(int j = 0; j < temp.length; j++)
            {
                if(i == 0 && j == 0 && !temp[j].equalsIgnoreCase("Lieferantennummer")) throw new CSVFormatException(i,j,temp[j],"Lieferantennummer");
                if(i == 0 && j == 1 && !temp[j].equalsIgnoreCase("Lieferantenname")) throw new CSVFormatException(i,j,temp[j],"Lieferantenname");
                if(i == 0 && j == 2 && !temp[j].equalsIgnoreCase("Straße")) throw new CSVFormatException(i,j,temp[j],"Straße");
                if(i == 0 && j == 3 && !temp[j].equalsIgnoreCase("PLZ")) throw new CSVFormatException(i,j,temp[j],"PLZ");
                if(i == 0 && j == 4 && !temp[j].equalsIgnoreCase("Ort")) throw new CSVFormatException(i,j,temp[j],"Ort");

                out[i][j] = temp[j];
            }
        }

        return out;
    }

    /**
     * Split Palette.csv into 2D-String-Array and test Syntax
     * @param data
     * @return
     * @throws IOException
     */
    private String[][] decodeItem(String[] data) throws IOException
    {
        String[] temp;
        String[][] out = null;

        int length = 0;
        for(int i = 0; i < data.length; i++)
        {
            temp = assets.stringSplitter(data[i], ',', '"');
            if(out == null) out = new String[data.length][temp.length];

            if(i == 0) length = temp.length;

            if(i != 0 && temp.length != length) throw new IOException("Parameter Count in 'Item' CSV File isn't '" + length + "' (like First Row) in Line '" + (i + 1) + "'!");

            for(int j = 0; j < temp.length; j++)
            {
                if(i == 0 && j == 0 && !temp[j].equalsIgnoreCase("Palette")) throw new CSVFormatException(i,j,temp[j],"Palette");
                if(i == 0 && j == 1 && !temp[j].equalsIgnoreCase("Artikelnummer")) throw new CSVFormatException(i,j,temp[j],"Artikelnummer");
                if(i == 0 && j == 2 && !temp[j].equalsIgnoreCase("Artikelbezeichnung")) throw new CSVFormatException(i,j,temp[j],"Artikelbezeichnung");

                out[i][j] = temp[j];
            }
        }

        return out;
    }
}
