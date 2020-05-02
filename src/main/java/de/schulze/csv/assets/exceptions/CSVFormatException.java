package de.schulze.csv.assets.exceptions;

import java.io.IOException;

public class CSVFormatException extends IOException
{
    public CSVFormatException()
    {
        super();
    }
    public CSVFormatException(int row, int column, String mismatch, String expectation )
    {
        super("The CSV File had at row '" + row + "' & column '" + column + "' the unexpected entry '" + mismatch + "' instead of '" + expectation + "'!");
    }
}
