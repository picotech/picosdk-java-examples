/**************************************************************************
 *
 * Filename:    PicoScope4000JNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps4000 library
 * for PicoScope 4000 series oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *      PicoScope 4224 & 4424
 *      PicoScope 4226 & 4227 
 *	PicoScope 4262 
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/
package com.picotech.picoscope.ps4000.jna;

import com.sun.jna.ptr.ShortByReference;
import com.picotech.picoscope.*;
import com.sun.jna.Native;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class PicoScope4000JNA
{
    protected Scanner consoleScanner;
    
    public PicoScope4000JNA()
    {
        consoleScanner = new Scanner(System.in);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        PicoScope4000JNA ps4000JNA = new PicoScope4000JNA();
        
        System.out.println("Searching for PicoScope 4000 Series devices...");
        System.out.println();
        
        ShortByReference countRef = new ShortByReference();
        byte[] serials = new byte[100];
        ShortByReference serialsLthRef = new ShortByReference();
        serialsLthRef.setValue((short) serials.length);
        
        int enumerateStatus = PS4000CLibrary.INSTANCE.ps4000EnumerateUnits(countRef, serials, serialsLthRef);
        
        System.out.println("Enumerate status: " + enumerateStatus);
        System.out.println("Count: " + countRef.getValue());
        
        String deviceSerials = "";
        
        try
        {
            deviceSerials = new String(serials, "UTF-8");
            
        }
        catch(UnsupportedEncodingException uee)
        {
            deviceSerials = "";
            
        }
        
        System.out.println("Serial number(s): " +  Native.toString(serials));
        System.out.println("Serials Lth: " + serialsLthRef.getValue());
        System.out.println();
        
        // Prompt user to start
        
        System.out.println("Press any key to begin.");
        
        ps4000JNA.getAnyKeyInput();
        
        System.out.println("Opening PicoScope 4000 Series device ...");
        System.out.println("");
        
        ShortByReference handleRef = new ShortByReference();
        handleRef.setValue((short) 0);
        
                
        //String deviceSerial = "AR290/071"; // Change to your serial number
        String deviceSerial = null; // Change to your serial number
       
        int openStatus = PicoStatus.PICO_OK;
        
        try
        {
            openStatus = PS4000CLibrary.INSTANCE.ps4000OpenUnit(handleRef);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        if (openStatus == PicoStatus.PICO_OK)
        {
            System.out.println("Found PicoScope 4000 Series device:-");
        
            short handle = handleRef.getValue();

            System.out.println("Handle: " + handle);

            byte[] infoBytes = new byte[40];
            ShortByReference reqSizeRef = new ShortByReference();

            int driverInfoStatus = PS4000CLibrary.INSTANCE.ps4000GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);

            System.out.println("Req Size: " + reqSizeRef.getValue());

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
            int variantInfoStatus = PS4000CLibrary.INSTANCE.ps4000GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

            System.out.println("Variant: " + Native.toString(infoBytes));
            System.out.println("");
            
            System.out.println("Closing connection to device.");
            PS4000CLibrary.INSTANCE.ps4000CloseUnit(handle);
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
