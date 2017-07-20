/**************************************************************************
 *
 * Filename:    PicoScope4000AJNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps4000a library
 * for PicoScope 4000 series oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *	PicoScope 4444
 *	PicoScope 4824
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/ 

package com.picotech.picoscope.ps4000a.jna;

import com.picotech.picoscope.*;
 
import com.sun.jna.Native;
 
import com.sun.jna.ptr.ShortByReference;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class PicoScope4000AJNA
{
    protected Scanner consoleScanner;
    
    public PicoScope4000AJNA()
    {
        consoleScanner = new Scanner(System.in);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        PicoScope4000AJNA ps4000aJNA = new PicoScope4000AJNA();
        
        System.out.println("Searching for PicoScope 4000 Series devices...\n");
        
        ShortByReference countRef = new ShortByReference();
        byte[] serials = new byte[100];
        ShortByReference serialsLthRef = new ShortByReference();
        serialsLthRef.setValue((short) serials.length);
        
        int enumerateStatus = PS4000ACLibrary.INSTANCE.ps4000aEnumerateUnits(countRef, serials, serialsLthRef);
        
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
        
        System.out.println("Serial number(s): " + deviceSerials);
        System.out.println("Serials Lth: " + serialsLthRef.getValue() + "\n");
        
        // Prompt user to start
        
        System.out.println("Opening PicoScope 4000 Series device ...");
        
        ShortByReference handleRef = new ShortByReference();
        handleRef.setValue((short) 0);
        
                
        //String deviceSerial = "AO483/050"; // Change to your serial number
        String deviceSerial = null; // Change to your serial number
                
        System.out.println("Press any key to begin.");
        
        ps4000aJNA.getAnyKeyInput();
       
        int openStatus = PicoStatus.PICO_OK;
        
        try
        {
            openStatus = PS4000ACLibrary.INSTANCE.ps4000aOpenUnit(handleRef, deviceSerial);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        short handle = handleRef.getValue();
        
        if (handle > 0)
        {   
            if (openStatus == PicoStatus.PICO_POWER_SUPPLY_NOT_CONNECTED || 
                    openStatus == PicoStatus.PICO_USB3_0_DEVICE_NON_USB3_0_PORT)
            {
                int powerChangeStatus = PS4000ACLibrary.INSTANCE.ps4000aChangePowerSource(handle, openStatus);
            }
            
            System.out.println("Opened PicoScope 4000 Series device:-\n");

            System.out.println("Handle: " + handle);

            byte[] infoBytes = new byte[40];
            ShortByReference reqSizeRef = new ShortByReference();

            int driverInfoStatus = PS4000ACLibrary.INSTANCE.ps4000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);

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
            int variantInfoStatus = PS4000ACLibrary.INSTANCE.ps4000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

            System.out.println("Variant: " + Native.toString(infoBytes));

            reqSizeRef.setValue((short) infoBytes.length);
            int serialInfoStatus = PS4000ACLibrary.INSTANCE.ps4000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_BATCH_AND_SERIAL);
            
            System.out.println("Serial " + Native.toString(infoBytes));
            
            System.out.println();

            System.out.println("Closing connection to device...");
            PS4000ACLibrary.INSTANCE.ps4000aCloseUnit(handle);
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
