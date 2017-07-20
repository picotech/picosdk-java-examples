/**************************************************************************
 *
 * Filename:    PicoScope3000AJNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the ps3000a library
 * for PicoScope 3000 series oscilloscopes in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information]
 *      Setup channels
 *      Verify timebase index for a block/rapid block mode capture
 *      Close the connection to the unit
 * 
 * Supported PicoScope models:
 *
 *	PicoScope 3204A/B/D
 *	PicoScope 3205A/B/D
 *	PicoScope 3206A/B/D
 *	PicoScope 3207A/B
 *	PicoScope 3204 MSO & D MSO
 *	PicoScope 3205 MSO & D MSO
 *	PicoScope 3206 MSO & D MSO
 *	PicoScope 3404A/B/D/D MSO
 *	PicoScope 3405A/B/D/D MSO
 *	PicoScope 3406A/B/D/D MSO
 * 
 * Copyright © 2015-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps3000a.jna;

import com.picotech.picoscope.*;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class PicoScope3000AJNA
{
    protected Scanner consoleScanner;
    
    public PicoScope3000AJNA()
    {
        consoleScanner = new Scanner(System.in);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        PicoScope3000AJNA ps3000aJNA = new PicoScope3000AJNA();
        
        System.out.println("Searching for PicoScope 3000 Series devices...\n");
        
        ShortByReference countRef = new ShortByReference();
        byte[] serials = new byte[100];
        ShortByReference serialsLthRef = new ShortByReference();
        serialsLthRef.setValue((short) serials.length);
        
        int enumerateStatus = PS3000ACLibrary.INSTANCE.ps3000aEnumerateUnits(countRef, serials, serialsLthRef);
        
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
        System.out.println();
        
        // Prompt user to start
        
        System.out.println("Opening PicoScope 3000 Series device ...\n");
        
        ShortByReference handleRef = new ShortByReference();
        handleRef.setValue((short) 0);
              
        String deviceSerial = null; // Change to your serial number
                
        System.out.println("Press any key to begin.");
        
        ps3000aJNA.getAnyKeyInput();
       
        int openStatus = PicoStatus.PICO_OK;
        
        try
        {
            openStatus = PS3000ACLibrary.INSTANCE.ps3000aOpenUnit(handleRef, deviceSerial);
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
                int powerChangeStatus = PS3000ACLibrary.INSTANCE.ps3000aChangePowerSource(handle, openStatus);
            }

            System.out.println("Opened PicoScope 3000 Series device:-\n");
            System.out.println("Handle: " + handle);

            byte[] infoBytes = new byte[40];
            ShortByReference reqSizeRef = new ShortByReference();

            int driverInfoStatus = PS3000ACLibrary.INSTANCE.ps3000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);

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
            int variantInfoStatus = PS3000ACLibrary.INSTANCE.ps3000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

            System.out.println("Variant: " + Native.toString(infoBytes));

            int serialInfoStatus = PS3000ACLibrary.INSTANCE.ps3000aGetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_BATCH_AND_SERIAL);

            System.out.println("Serial: " + Native.toString(infoBytes));
            System.out.println();

            // Set channel A
            int channelA    = PS3000ACLibrary.PS3000AChannel.PS3000A_CHANNEL_A.ordinal();
            short enabled   = 1;
            int coupling    = PS3000ACLibrary.PS3000ACoupling.PS3000A_DC.ordinal();
            int range       = PS3000ACLibrary.PS3000ARange.PS3000A_5V.ordinal();
            float offset    = 0.0f;

            int setChannelStatus = PS3000ACLibrary.INSTANCE.ps3000aSetChannel(handle, channelA, enabled, coupling, range, offset);

            int timebase            = 0;
            int preTriggerSamples   = 0;
            int postTriggerSamples  = 1024;
            int numSamples          = preTriggerSamples + postTriggerSamples; // Set to total number of samples required

            // Query timebase index value (for block and rapid block captures)
            FloatByReference timeIntervalNsRef = new FloatByReference();
            timeIntervalNsRef.setValue((float) 0.0);

            short oversample = 1; // Do not change this value

            IntByReference maxSamplesRef = new IntByReference();
            maxSamplesRef.setValue(0);

            int segmentIndex = 0;

            int getTimebase2Status = PicoStatus.PICO_INVALID_TIMEBASE; // Initialise to PICO_INVALID_TIMEBASE

            // Loop until a valid timebase has been found

            while (getTimebase2Status != PicoStatus.PICO_OK)
            {
                getTimebase2Status = PS3000ACLibrary.INSTANCE.ps3000aGetTimebase2(handle, timebase, numSamples, timeIntervalNsRef, oversample, maxSamplesRef, segmentIndex);

                if (getTimebase2Status == 0)
                {
                    break;
                }
                else
                {
                    timebase = timebase + 1;
                }
            }

            System.out.println("Timebase information:-\n");
            System.out.println("Timebase: " + timebase + " ns");
            System.out.println("Time interval: " + timeIntervalNsRef.getValue());
            System.out.println("Max Samples: " + maxSamplesRef.getValue());

            // Set Data Buffer for Channel A

            // Allocate contiguous memory
            Pointer bufferA = new Memory(numSamples * Native.getNativeSize(Short.TYPE)); 

            // Initialise buffer

            for(int n = 0; n < numSamples; n = n + 1)
            {
                bufferA.setShort(n * Native.getNativeSize(Short.TYPE), (short) 0);
            }

            int ratioMode = PS3000ACLibrary.PS3000ARatioMode.PS3000A_RATIO_MODE_NONE.ordinal();

            int setDataBufferAStatus = PS3000ACLibrary.INSTANCE.ps3000aSetDataBuffer(handle, channelA, bufferA, numSamples, segmentIndex, ratioMode);

            System.out.println();
            System.out.println("Closing connection to device...");
            PS3000ACLibrary.INSTANCE.ps3000aCloseUnit(handle);
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
