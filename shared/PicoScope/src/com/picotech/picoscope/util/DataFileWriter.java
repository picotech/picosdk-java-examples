/*******************************************************************************
 *
 * Filename:    DataFileWriter.java
 *
 * Author:      HSM
 *
 * Description:
 *      Utility class for writing data values to a file.
 *
 * Copyright (C) 2011 - 2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ******************************************************************************/
package com.picotech.picoscope.util;

import com.picotech.picoscope.UnitModel;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *  Class to help write data to files.
 * @author hitesh
 */
public class DataFileWriter 
{
    public static final String IMMEDIATE_BLOCK = "Immediate_Block_";
    public static final String TRIGGERED_BLOCK = "Triggered_Block_";
    public static final String ADVANCED_TRIGGERED_BLOCK = "Advanced_Triggered_Block_";
    public static final String STREAMING = "Streaming_";
    public static final String FAST_STREAMING = "Fast_Streaming_";
    
    public static final String FILE_EXTENSION_TXT = ".txt";
    public static final String FILE_EXTENSION_CSV = ".csv";
    
    private BufferedWriter fileWriter;
    private Charset charset;
    private Path dataFilePath;
    
    private SimpleDateFormat simpleDateFormatter;
    private StringBuilder fileName;
    
    /**
     * Creates a new DataFileWriter object
     * 
     * @param directory_path The absolute or relative path to the file
     * @param file_stub The initial portion of the file name
     * @param file_extension The file type e.g. '.txt' or '.csv'
     */
    public DataFileWriter(String directory_path, String file_stub, String file_extension)
    {
        fileName = new StringBuilder(directory_path);
        fileName.append(file_stub);
        
        // Create a data stub
        simpleDateFormatter = new SimpleDateFormat("dd'_'MM'_'yyyy'_'H'_'mm'_'ss", Locale.ENGLISH);
        
        Date now = new Date();
        
        fileName.append(simpleDateFormatter.format(now));
        fileName.append(file_extension);
        
        dataFilePath = Paths.get(fileName.toString());
        
        try
        {
            charset = Charset.forName("US-ASCII");
        }
        catch(IllegalArgumentException iae)
        {
            System.err.println("Error in Charset selection - using JVM default.");
            charset = Charset.defaultCharset();
        }
        
        try
        {
            fileWriter = Files.newBufferedWriter(dataFilePath, charset, 
                    StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
        }
        catch(IOException ioe) 
        {
            System.err.format("IOException: %s%n", ioe);
        }
        
    }
    
    /**
     * Provides a String representation of the path to the file being written to.
     * 
     * @return String representation of the Path.
     */
    public String getPathString()
    {
        return dataFilePath.toString();
    }
    
    /**
     * Creates a file header for the output file.
     * @param unit_model UnitModel object
     * @param scale_to_mv whether data values are in mV or in ADC counts
     */
    public void writeHeaderToFile(UnitModel unit_model, boolean scale_to_mv) throws IOException
    {
        StringBuilder file_header = new StringBuilder("Channel ");
        
        for(int ch = 0; ch < unit_model.getChannelCount(); ch = ch + 1)
        {
            if(unit_model.getChannelSettings()[ch].isEnabled() == true)
            {
                file_header.append((char) ('A' + ch));
                
                if(scale_to_mv == true)
                {
                    file_header.append(" (mV)");
                }
                else
                {
                    file_header.append(" (ADC)");
                }
                
                file_header.append("\t");
            }
        }
        
        writeDataToFile(file_header.toString());
        
        writeNewLineToFile();
        writeNewLineToFile();
    }
    
    
    /**
     * Creates a file header for the output file with heading for time column.
     * 
     * @param unit_model
     * @param scale_to_mv
     * @param time_units
     * @throws IOException 
     */
    public void writeHeaderToFile(UnitModel unit_model, boolean scale_to_mv, String time_units) throws IOException
    {
        StringBuilder file_header = new StringBuilder("Time (");
        file_header.append(time_units);        
        file_header.append(") \t");
        
        for(int ch = 0; ch < unit_model.getChannelCount(); ch = ch + 1)
        {
            if(unit_model.getChannelSettings()[ch].isEnabled() == true)
            {
                file_header.append("Channel ");
                file_header.append((char) ('A' + ch));
                
                if(scale_to_mv == true)
                {
                    file_header.append(" (mV)");
                }
                else
                {
                    file_header.append(" (ADC)");
                }
                
                file_header.append("\t");
            }
        }
        
        writeDataToFile(file_header.toString());
        
        writeNewLineToFile();
        writeNewLineToFile();
    }
    
    
    /**
     * 
     * @param data_line
     * @throws IOException 
     */
    public void writeDataToFile(String data_line) throws IOException
    {
        fileWriter.write(data_line, 0, data_line.length());
        fileWriter.flush();
    }
    
    /**
     * 
     * @throws IOException 
     */
    public void writeNewLineToFile() throws IOException
    {
        fileWriter.newLine();
    }
    
    /**
     * 
     * @throws IOException 
     */
    public void closeFile() throws IOException
    {
        fileWriter.flush();
        fileWriter.close();
    }
}
