/**************************************************************************
 *
 * Filename:    PicoScope6000JNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps6000 library
 * for PicoScope 6000 series oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *	PicoScope 6402 & 6402A/B/C/D
 *	PicoScope 6403 & 6403A/B/C/D
 *	PicoScope 6404 & 6404A/B/C/D
 *      PicoScope 6407
 * 
 * Copyright © 2016-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/ 

package com.picotech.picoscope.ps6000.jna;

import com.sun.jna.ptr.ShortByReference;
import com.picotech.picoscope.*;
import com.sun.jna.Native;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;



public class PicoScope6000JNA
{
    protected Scanner consoleScanner;
    
    public PicoScope6000JNA()
    {
        consoleScanner = new Scanner(System.in);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        PicoScope6000JNA ps6000JNA = new PicoScope6000JNA();
        
        System.out.println("Searching for PicoScope 6000 Series devices...\n");
        
        ShortByReference countRef = new ShortByReference();
        byte[] serials = new byte[100];
        ShortByReference serialsLthRef = new ShortByReference();
        serialsLthRef.setValue((short) serials.length);
        
        int enumerateStatus = PS6000CLibrary.INSTANCE.ps6000EnumerateUnits(countRef, serials, serialsLthRef);
        
        System.out.println("Enumerate status: " + enumerateStatus);
        System.out.println("Count: " + countRef.getValue());
        
        System.out.println("Serial number(s): " +  Native.toString(serials));
        System.out.println("Serials Lth: " + serialsLthRef.getValue() + "\n");
        
        // Prompt user to start
        
        System.out.println("Press any key to begin.");
        
        ps6000JNA.getAnyKeyInput();
        
        System.out.println("Opening PicoScope 6000 Series device ...");
        
        ShortByReference handleRef = new ShortByReference();
        handleRef.setValue((short) 0);
        
        String deviceSerial = null; // Change to your serial number
                
        int openStatus = PicoStatus.PICO_OK;
        
        try
        {
            openStatus = PS6000CLibrary.INSTANCE.ps6000OpenUnit(handleRef, deviceSerial);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        short handle = handleRef.getValue();
        
        System.out.println("OpenUnit status: " + openStatus);
        System.out.println("Handle: " + handle + "\n");
        
        if (handle > 0)
        {
        
            byte[] infoBytes = new byte[80];
            ShortByReference reqSizeRef = new ShortByReference();

            int driverInfoStatus = PS6000CLibrary.INSTANCE.ps6000GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);

            String driverInfo = new String();

            try
            {
                driverInfo = new String(infoBytes, "UTF-8");
            }
            catch(UnsupportedEncodingException uee)
            {
                driverInfo = "";

            }

            System.out.println("Driver: " + driverInfo.substring(0, reqSizeRef.getValue() - 1));

            reqSizeRef.setValue((short) infoBytes.length);
            int variantInfoStatus = PS6000CLibrary.INSTANCE.ps6000GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

            System.out.println("Variant: " + Native.toString(infoBytes));

            int serialInfoStatus = PS6000CLibrary.INSTANCE.ps6000GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_BATCH_AND_SERIAL);
            System.out.println("Serial: " + Native.toString(infoBytes).substring(0, reqSizeRef.getValue() - 1));

            PS6000CLibrary.INSTANCE.ps6000CloseUnit(handle);
        }
        else
        {
            System.err.println("PicoScope 6000 Series not found.");
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
