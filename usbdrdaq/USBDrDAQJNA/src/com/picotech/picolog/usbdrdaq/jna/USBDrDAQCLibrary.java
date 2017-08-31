/**************************************************************************
 *
 * Filename:    USBDrDAQCLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions from the usbDrDaqApi.h C header file 
 * for the USB DrDAQ data logger using the usbdrdaq library API functions.
 * 
 * Copyright © 2016-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/
package com.picotech.picolog.usbdrdaq.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;


public interface USBDrDAQCLibrary extends Library
{
    USBDrDAQCLibrary INSTANCE = (USBDrDAQCLibrary) Native.loadLibrary
                                ( 
                                    "usbdrdaq", USBDrDAQCLibrary.class
                                );

    // Method definitions from usbDrDaqApi.h C header file
    // ===================================================
    
    // C prototype definition :	
    // PICO_STATUS UsbDrDaqOpenUnit(int16_t * handle)
    int UsbDrDaqOpenUnit(ShortByReference handle);

    // C prototype definition :
    // PICO_STATUS UsbDrDaqOpenUnit(int16_t handle);
    int UsbDrDaqCloseUnit(short handle);

    // C prototype definition :
    // PICO_STATUS UsbDrDaqGetUnitInfo(int16_t handle, int8_t * string, int16_t stringLength, int16_t * requiredSize, PICO_INFO info)
    int UsbDrDaqGetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);

    // C prototype definition :
    // PICO_STATUS  UsbDrDaqEnableRGBLED(int16_t handle, int16_t enabled)
    int UsbDrDaqEnableRGBLED(short handle, short enabled);
    
    // C prototype definition :
    // PICO_STATUS  UsbDrDaqSetRGBLED(int16_t handle, uint16_t red, uint16_t green, uint16_t blue)
    int UsbDrDaqSetRGBLED(short handle, short red, short green, short blue);
}
    
    
