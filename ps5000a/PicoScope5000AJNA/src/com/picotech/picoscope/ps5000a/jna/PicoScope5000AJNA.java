/**************************************************************************
 *
 * Filename:    PicoScope5000AJNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps5000a library
 * for PicoScope 5000 Series oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *	PicoScope 5242A/B & 5442A/B
 *	PicoScope 5243A/B & 5443A/B
 *	PicoScope 5244A/B & 5444A/B
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps5000a.jna;

import com.sun.jna.ptr.ShortByReference;
import com.picotech.picoscope.*;
import com.sun.jna.Native;
import java.io.UnsupportedEncodingException;

public class PicoScope5000AJNA 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Searching for PicoScope 5000 Series devices...\n");
        
        ShortByReference countRef = new ShortByReference();
        byte[] serials = new byte[100];
        ShortByReference serialsLthRef = new ShortByReference();
        serialsLthRef.setValue((short) serials.length);
        
        int enumerateStatus = PS5000ACLibrary.INSTANCE.ps5000aEnumerateUnits(countRef, serials, serialsLthRef);
        
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
        
        System.out.println("Opening PicoScope 5000 Series device ...\n");
        
        ShortByReference handleRef = new ShortByReference();
        handleRef.setValue((short) 0);
        
        String deviceSerial = null; // Change to your serial number

        int resolution = 0; // 8-bit
        
        int openStatus = PS5000ACLibrary.INSTANCE.ps5000aOpenUnit(handleRef, deviceSerial, resolution);
        
        short handle = handleRef.getValue();
        
        if (openStatus == PicoStatus.PICO_POWER_SUPPLY_NOT_CONNECTED)
        {
            int powerChangeStatus = PS5000ACLibrary.INSTANCE.ps5000aChangePowerSource(handle, openStatus);
        }
        
        System.out.println("Handle: " + handle);
        
        byte[] infoBytes = new byte[80];
        ShortByReference reqSizeRef = new ShortByReference();
        
        int driverInfoStatus = PS5000ACLibrary.INSTANCE.ps5000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);
		
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
        int variantInfoStatus = PS5000ACLibrary.INSTANCE.ps5000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

        System.out.println("Variant: " + Native.toString(infoBytes));

        int serialInfoStatus = PS5000ACLibrary.INSTANCE.ps5000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_BATCH_AND_SERIAL);

        System.out.println("Serial: " + Native.toString(infoBytes));
        System.out.println();
        
	PS5000ACLibrary.INSTANCE.ps5000aCloseUnit(handle);
    }
    
}
