/**************************************************************************
 *
 * Filename:    PicoScope2000JNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the usbtc08 library
 * for the USB TC-08 Thermocouple Data Logger in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Setup channels
 *      Collect some readings
 *      Close the connection to the unit
 * 
 * Copyright © 2016-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picolog.usbtc08.jna;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.ShortByReference;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;


public class USBTC08JNA 
{
    public static void main(String args[])
    {
        short handle = 0;
        short status = 0;
        
        System.out.println("Opening USB TC-08 device...");
        System.out.println();
        
        // Open Unit
        // ---------
        handle = USBTC08CLibrary.INSTANCE.usb_tc08_open_unit();
        
        if(handle > 0)
        {
            System.out.println("Found USB TC-08 device:");
            System.out.println();
            
            // Print Unit Info
            
            String[] description = {"Driver Version",
                                "Kernel Version",
                                "Hardware Version",
                                "Variant Info",
                                "Serial",
                                "Cal Date"};
            
            byte[] infoBytes = new byte[512];

            status = USBTC08CLibrary.INSTANCE.usb_tc08_get_formatted_info(handle, infoBytes, (short) infoBytes.length);

            String deviceInfo = new String();

            try
            {
                deviceInfo = new String(infoBytes, "UTF-8");
            }
            catch(UnsupportedEncodingException uee)
            {
                deviceInfo = "";

            }

            System.out.println(deviceInfo);
            System.out.println();
            
            // Set Mains
            // ---------
            status = USBTC08CLibrary.INSTANCE.usb_tc08_set_mains(handle, (short) 0);
            
            // Channel Setup
            // -------------
            
            // Set Channel - CJC
            status = USBTC08CLibrary.INSTANCE.usb_tc08_set_channel(handle, (short) 0, USBTC08CLibrary.USB_TC08_THERMOCOUPLE_TYPE_K);
            
            // Set Channel - Channel 1
            status = USBTC08CLibrary.INSTANCE.usb_tc08_set_channel(handle, (short) 1, USBTC08CLibrary.USB_TC08_THERMOCOUPLE_TYPE_K);
            
            // Find Minimum Sampling Interval
            // ------------------------------
            
            int minimumInterval = USBTC08CLibrary.INSTANCE.usb_tc08_get_minimum_interval_ms(handle);
            
            System.out.println("Minimum Interval: " + minimumInterval + " ms");
            System.out.println();
            
            // Get temperature readings
            
            ShortByReference overflowFlags = new ShortByReference();
            overflowFlags.setValue((short) 0);

            Memory tempPointer = new Memory(9 * Native.getNativeSize(Float.TYPE));
            
            float[] temperatures;
            
            System.out.println("Collecting data...\n");
            
            System.out.println("CJC \t\tCh1");
            System.out.println();
            
            for(int j = 0; j < 20; j++)
            {
                status = USBTC08CLibrary.INSTANCE.usb_tc08_get_single(handle, tempPointer, overflowFlags, (short) 0);
            
                temperatures = tempPointer.getFloatArray(0, 9);
                
                System.out.format("%f\t%f", temperatures[0], temperatures[1]);
                System.out.println();
                
                try
                {
                    Thread.sleep(minimumInterval);
                }
                catch(InterruptedException ie)
                {
                    
                }
            }
            
            System.out.println();
            
            // Close Unit
            status = USBTC08CLibrary.INSTANCE.usb_tc08_close_unit(handle);
            
            if(status == 1)
            {
                System.out.println("Device closed successfully.");
            }
        }
        else
        {
            System.err.println("No device found");
        }
        
        
    }
    
}
