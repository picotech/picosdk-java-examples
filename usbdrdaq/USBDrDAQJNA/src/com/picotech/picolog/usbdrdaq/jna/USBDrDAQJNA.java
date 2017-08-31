/**************************************************************************
 *
 * Filename:    USBDrDAQJNA.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This is a class demonstrating how to use Java and Java
 * Native Access (JNA) in order to call functions from the usbdrdaq library
 * for the USB DrDAQ data logger in order to:
 * 
 *      Open a connection to a unit
 *      Control the LED
 *      Close the connection to the unit
 * 
 * Copyright © 2016-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/ 
package com.picotech.picolog.usbdrdaq.jna;

import com.picotech.picoscope.PicoStatus;
import com.sun.jna.ptr.ShortByReference;

/**
 *
 * @author hitesh.mistry
 */
public class USBDrDAQJNA 
{
    private short handle;
    private boolean connected;
    
    public USBDrDAQJNA()
    {
        handle = 0;
        connected = false;
        
        connect();
    }
    
    public boolean isConnected() 
    {
        return connected;
    }
    
    private void connect()
    {
        ShortByReference handlePtr = new ShortByReference();
        handlePtr.setValue((short) 0);
        
        int status = USBDrDAQCLibrary.INSTANCE.UsbDrDaqOpenUnit(handlePtr);
        
        if (status == PicoStatus.PICO_OK)
        {
            handle = handlePtr.getValue();
            connected = true;
        }
        else
        {
            connected = false;
        }
    }
    
    public int disconnect()
    {
        int status = USBDrDAQCLibrary.INSTANCE.UsbDrDaqCloseUnit(handle);
                
        if (status == PicoStatus.PICO_OK)
        {
            connected = false;
        }
        
        return status;
    }
    
    public int enableLED(int enabled)
    {
        if (enabled == 1)
        {
            return USBDrDAQCLibrary.INSTANCE.UsbDrDaqEnableRGBLED(handle, (short) 1);
        }
        else
        {
            return USBDrDAQCLibrary.INSTANCE.UsbDrDaqEnableRGBLED(handle, (short) 0);
        }
        
    }
    
    public int setLED(short red, short green, short blue)
    {
        return USBDrDAQCLibrary.INSTANCE.UsbDrDaqSetRGBLED(handle, red, green, blue);
    }
    
    
}
