/**************************************************************************
 *
 * Filename:    USBPT104AJNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is an example program demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the usbpt104 library
 * for the USB PT-104 Platinum Resistance Temperature data logger in order to:
 * 
 *      Open a connection to a unit
 *      Print unit information
 *      Setup up a channel
 *      Collect some data 
 *      Close the connection to the unit
 * 
 * Copyright © 2015-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/
package com.picotech.picolog.usbpt104.jna;

import com.picotech.picoscope.PicoInfo;
import com.picotech.picoscope.PicoStatus;
import com.sun.jna.Native;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;
import java.io.UnsupportedEncodingException;

public class USBPT104JNA 
{
    public static void main(String[] args)
    {
        System.out.println("Searching for USB PT-104 data logger devices...\n");
        
        byte[] details = new byte[100];
        IntByReference lengthRef = new IntByReference(details.length);
        int type = USBPT104CLibrary.CommunicationType.CT_ALL.getValue();
        
        int enumerateStatus = USBPT104CLibrary.INSTANCE.UsbPt104Enumerate(details, lengthRef, type);
        
        System.out.println("Enumerate status: " + enumerateStatus);
        System.out.println("Length of details string: " + lengthRef.getValue());
        
        String deviceSerials = "";
        
        try
        {
            deviceSerials = new String(details, "UTF-8");
        }
        catch(UnsupportedEncodingException uee)
        {
            deviceSerials = "";
            
        }
        
        System.out.println("Device(s): " + deviceSerials);
        System.out.println();
        
        // Open Unit
        // ---------
        
        ShortByReference handleRef = new ShortByReference();
        handleRef.setValue((short) 0);
              
        String deviceSerial = null; // Change to your serial number
                
        int openStatus = PicoStatus.PICO_OK;
        
        try
        {
            openStatus = USBPT104CLibrary.INSTANCE.UsbPt104OpenUnit(handleRef, deviceSerial);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        short handle = handleRef.getValue();
        
        if (handle > 0)
        {
            System.out.println("Found USB PT-104 device:");
            System.out.println();
            
            // Print Unit Info
            // ---------------
            byte[] infoBytes = new byte[40];
            ShortByReference reqSizeRef = new ShortByReference();

            int driverInfoStatus = USBPT104CLibrary.INSTANCE.UsbPt104GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_DRIVER_VERSION);

            System.out.println("Driver: " + Native.toString(infoBytes));

            reqSizeRef.setValue((short) infoBytes.length);
            int variantInfoStatus = USBPT104CLibrary.INSTANCE.UsbPt104GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_VARIANT_INFO);

            System.out.println("Variant: " + Native.toString(infoBytes));

            int serialInfoStatus = USBPT104CLibrary.INSTANCE.UsbPt104GetUnitInfo(handle, infoBytes, (short) infoBytes.length, reqSizeRef, PicoInfo.PICO_BATCH_AND_SERIAL);

            System.out.println("Serial: " + Native.toString(infoBytes));
            System.out.println();
            
            // Set mains filter
            // ----------------
            
            // Set mains rejection to 50 Hz
            int setMainsStatus = USBPT104CLibrary.INSTANCE.UsbPt104SetMains(handle, (short) 0);
            
            // Set channels
            // ------------
            
            // Setup channel 1 for temperature measurement
            
            int channel = USBPT104CLibrary.UsbPt104Channels.USBPT104_CHANNEL_1.getValue();
            int dataType = USBPT104CLibrary.UsbPt104DataType.USBPT104_PT100.ordinal();
            
            int setChannel1Status = USBPT104CLibrary.INSTANCE.UsbPt104SetChannel(handle, channel, dataType, (short) USBPT104CLibrary.USBPT104_MAX_WIRES);
            
            // Collect some data
            // -----------------
            
            // Take 1 reading at 1 second intervals. Each channel takes 720 ms to
            // convert so allow more time if enabling more than one channel.
            
            int numberOfReadings = 10;
            int getValueStatus = PicoStatus.PICO_OK;
            IntByReference valueRef = new IntByReference();
            short filtered = 1;
            float temperatureValue = 0.0f;
            
            System.out.println("Collecting " + numberOfReadings + " readings for channel 1:-");
            System.out.println();
            
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException ie)
            {
                ie.printStackTrace();
            }
            
            for (int n = 0; n < numberOfReadings; n++)
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                
                getValueStatus = USBPT104CLibrary.INSTANCE.UsbPt104GetValue(handle, channel, valueRef, filtered);
                
                temperatureValue = USBPT104JNA.convertToTemperature(valueRef.getValue());
                System.out.printf("%.3f\n", temperatureValue);
                
            }
            
            System.out.println();
            
            // Close unit
            // ----------
            
            System.out.println("Closing connection to device...");
            
            int closeStatus = USBPT104CLibrary.INSTANCE.UsbPt104CloseUnit(handle);
        }
        
        
    }
    
    /**
     * Convert a value returned from the PT-104 to temperature (degrees C)
     * @param value the requested reading
     * @return temperature
     */
    public static float convertToTemperature(int value)
    {
        return (float) (value / 1000.0);
    }
    
}
