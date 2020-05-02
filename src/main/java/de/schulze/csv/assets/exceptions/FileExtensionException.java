package de.schulze.csv.assets.exceptions;

import java.io.IOException;

public class FileExtensionException extends IOException
{
    public FileExtensionException()
    {
        super();
    }
    public FileExtensionException(String fileName, String expectedExtension)
    {
        super("The File '" + fileName + "' hadn't the expected File Extension '" + expectedExtension + "'!");
    }
}
