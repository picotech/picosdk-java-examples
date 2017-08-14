/**************************************************************************
 *
 * Filename:    PicoScope2000AJNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps2000a library
 * for PicoScope 2000 series oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *	PicoScope 2205 MSO & 2205A MSO
 *	PicoScope 2405A
 *	PicoScope 2206, 2206A, 2206B, 2206B MSO & 2406B
 *	PicoScope 2207, 2207A, 2207B, 2207B MSO & 2407B
 *	PicoScope 2208, 2208A, 2208B, 2208B MSO & 2408B
 * 
 * Copyright © 2015-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps2000a.jna;

import com.picotech.picoscope.*;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;

import java.io.UnsupportedEncodingException;


public class PicoScope2000AJNA 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Searching for PicoScope 2000 Series devices...\n");
        
        ShortByReference countRef = new ShortByReference();
        byte[] serials = new byte[100];
        ShortByReference serialsLthRef = new ShortByReference();
        serialsLthRef.setValue((short) serials.length);
        
        int enumerateStatus = PS2000ACLibrary.INSTANCE.ps2000aEnumerateUnits(countRef, serials, serialsLthRef);
        
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
        System.out.println("Serials Lth: " + serialsLthRef.getValue());
        
        if (enumerateStatus == PicoStatus.PICO_OK && countRef.getValue() > 0)
        {
            System.out.println("\nOpening PicoScope 2000 Series device ...\n");

            ShortByReference handleRef = new ShortByReference();

            String deviceSerial = ""; // Change to your serial number or use null

            Memory serial = new Memory(deviceSerial.length() + 1);
            serial.setString(0, deviceSerial);

            int openStatus = PS2000ACLibrary.INSTANCE.ps2000aOpenUnit(handleRef, null);

            short handle = handleRef.getValue();

            if (handle > 0)
            {   
                System.out.println("Handle: " + handle);

                // Print device information

                byte[] infoBytes = new byte[40];
                ShortByReference reqSizeRef = new ShortByReference();

                int driverInfoStatus = PS2000ACLibrary.INSTANCE.ps2000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);

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
                int variantInfoStatus = PS2000ACLibrary.INSTANCE.ps2000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

                System.out.println("Variant: " + Native.toString(infoBytes));

                reqSizeRef.setValue((short) infoBytes.length);
                int serialInfoStatus = PS2000ACLibrary.INSTANCE.ps2000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_BATCH_AND_SERIAL);

                System.out.println("Serial: " + Native.toString(infoBytes));

                System.out.println();
                System.out.println("Closing connection to device...");
                int closeStatus = PS2000ACLibrary.INSTANCE.ps2000aCloseUnit(handle);
                
                if (closeStatus == PicoStatus.PICO_OK)
                {
                    System.out.println("Device connection closed successfully.");
                }
                else
                {
                    System.err.println("Error closing device connection - status code: " + closeStatus);
                }
            }
            else
            {
                System.err.println("Failed to open unit.");
            }
        }
        else
        {
            System.err.println("No devices found.");
        }
    }
    
}
