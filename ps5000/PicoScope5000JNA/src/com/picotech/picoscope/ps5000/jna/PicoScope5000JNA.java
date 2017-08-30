/**************************************************************************
 *
 * Filename:    PicoScope5000JNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps5000 library
 * for PicoScope 5203 and 5204 oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *	PicoScope 5203
 *	PicoScope 5204
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/ 
package com.picotech.picoscope.ps5000.jna;

import com.sun.jna.ptr.ShortByReference;
import com.picotech.picoscope.*;
import com.sun.jna.Native;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;



public class PicoScope5000JNA
{
    protected Scanner consoleScanner;
    
    public PicoScope5000JNA()
    {
        consoleScanner = new Scanner(System.in);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        PicoScope5000JNA ps5000JNA = new PicoScope5000JNA();
        
        System.out.println("PicoScope 5203 and 5204 Java Example\n");
        
        // Prompt user to start
        System.out.println("Press any key to begin.");
        
        ps5000JNA.getAnyKeyInput();
        
        System.out.println("Opening PicoScope 5000 Series device ...\n");
        
        ShortByReference handleRef = new ShortByReference();
        handleRef.setValue((short) 0);
       
        int openStatus = PicoStatus.PICO_OK;
        
        try
        {
            openStatus = PS5000CLibrary.INSTANCE.ps5000OpenUnit(handleRef);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        short handle = handleRef.getValue();
        
        if (handle > 0)
        {
        
            System.out.println("Handle: " + handle);

            byte[] infoBytes = new byte[80];
            ShortByReference reqSizeRef = new ShortByReference();

            int driverInfoStatus = PS5000CLibrary.INSTANCE.ps5000GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);

            String driverInfo = new String();

            try
            {
                driverInfo = new String(infoBytes, "UTF-8");
            }
            catch(UnsupportedEncodingException uee)
            {
                driverInfo = "";

            }

            System.out.println("Driver: " + driverInfo);

            reqSizeRef.setValue((short) infoBytes.length);
            int variantInfoStatus = PS5000CLibrary.INSTANCE.ps5000GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

            System.out.println("Variant: " + Native.toString(infoBytes));

            PS5000CLibrary.INSTANCE.ps5000CloseUnit(handle);
        }
        else
        {
            System.err.println("PicoScope 5203 or 5204 not found.");
        }
    }
    
    public synchronized Scanner getConsoleScanner()
    {
        return consoleScanner;
    }
    
    public void getAnyKeyInput()
    {
        String key_input = getConsoleScanner().nextLine();
        
    }
    
}
