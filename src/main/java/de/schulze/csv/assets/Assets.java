package de.schulze.csv.assets;

import java.util.ArrayList;

public class Assets
{

    /**
     * Asset Method to Split String at splitterChar by prioritize stringChar
     * @param dataString
     * @param splitterChar
     * @param stringChar
     * @return
     */
    public String[] stringSplitter(String dataString, char splitterChar, char stringChar)
    {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();

        char[] chars = dataString.toCharArray();
        boolean string = false;

        for( char item : chars)
        {
            if(!string && item == splitterChar)
            {
                list.add(sb.toString());
                sb = new StringBuilder();
            }
            else if(!string && item == stringChar) string = true;
            else if(string && item == stringChar) string = false;
            else if(item != stringChar && item != splitterChar && item != ' ' || (item == ' ' && string)) sb.append(item);
        }
        list.add(sb.toString());

        String[] out = new String[list.size()];
        for(int i = 0; i < list.size(); i++) out[i] = list.get(i);

        return out;
    }
}
