/**************************************************************************
 *
 * Filename:    PS5000ACLibrary.java
 *
 * Author:      HSM
 *
 * Description: 
 *  This interface defines functions from the ps5000aApi.h C header file 
 * for the PicoScope 5000 Series oscilloscopes using the ps5000a library 
 * API functions.
 * 
 * Copyright © 2014-2017 Pico Technology Ltd. See LICENSE file for terms.
 * 
 ***************************************************************************/

package com.picotech.picoscope.ps5000a.jna;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.ptr.ShortByReference;

public interface PS5000ACLibrary extends Library
{
	
	PS5000ACLibrary INSTANCE = (PS5000ACLibrary) Native.loadLibrary
                                    ( 
                                        "ps5000a", PS5000ACLibrary.class
                                    );
	
        // Method definitions from ps5000aApi.h C header file
        // ==================================================
        
        // C prototype definition: 
	// 		uint32_t ps5000aEnumerateUnits(int16_t *count, int8_t *serials, int16_t serialLth)
        int ps5000aEnumerateUnits(ShortByReference count, byte[] serials, ShortByReference serialLth);
        
	// C prototype definition: 
	// 		uint32_t ps5000aOpenUnit( uint16_t *handle, int8_t *serial, PS5000A_DEVICE_RESOLUTION resolution);	
        
        //int ps5000aOpenUnit(ShortByReference handle, Memory serial, int resolution);
        int ps5000aOpenUnit(ShortByReference handle, String serial, int resolution);
	
	// C prototype definition
	// uint32_t ps5000aCloseUnit(int16_t handle);
	int ps5000aCloseUnit(short handle);
	
        // unit32_t ps5000aGetUnitInfo(int16_t handle, int8_t *string, int16_t stringLength,
        // int16_t *requiredSize, PICO_INFO  info)
        int ps5000aGetUnitInfo(short handle, byte[] string, short stringLength, ShortByReference requiredSize, int info);
        
        // C prototype definition
        //unit32_t ps5000aChangePowerSource(int16_t      handle, PICO_STATUS powerstate)
        int ps5000aChangePowerSource(short handle, int powerstate);
        
}
   
